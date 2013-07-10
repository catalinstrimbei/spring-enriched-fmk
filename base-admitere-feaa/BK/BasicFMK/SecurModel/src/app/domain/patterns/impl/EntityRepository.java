/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.patterns.impl;

import app.domain.patterns.IRepository;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author catalin
 */
public abstract class EntityRepository implements IRepository{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    protected EntityManager em;
    protected Class repositoryType;
    protected String genericSQL;
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityRepository(EntityManager em, Class T) {
        this.em = em;
        repositoryType = T;
        genericSQL = "SELECT o FROM "
                + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.')+1)
                + " o";
        logger.info("generic JPAQL: " + genericSQL);

    }

    // Repository query implementation
    public <T> T getById(Object id) {
        return (T) em.find(repositoryType, id);
    }

    // QBExample
    public <T> Collection<T> get(T entitySample) {
        
        Map<String, Object> sqlCriterias = new HashMap<String, Object>();
        try {
            // get all properties and transform them into sqlCriterias
            PropertyDescriptor[] properties = Introspector.getBeanInfo(repositoryType).getPropertyDescriptors();
            Object propertyValue;
            for (PropertyDescriptor property : properties){
                propertyValue = property.getReadMethod().invoke(entitySample);
                if (propertyValue != null && ! property.getName().equals("class"))
                    sqlCriterias.put(property.getName(), propertyValue);
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EntityRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EntityRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EntityRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(EntityRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (sqlCriterias.isEmpty())
            return null;

        String queryString = genericSQL + " WHERE ";
        for(String criteria : sqlCriterias.keySet()){
            queryString += "o." + criteria + " = :" + criteria + " AND ";
        }
        queryString += " 1 = 1";

        logger.info("JPAQL: " + queryString);

        Query query = em.createQuery(queryString);
        for(String criteria : sqlCriterias.keySet()){
            query = query.setParameter(criteria, sqlCriterias.get(criteria));
        }
        return query.getResultList();

    }

    public <T> Collection<T> toCollection() {
        logger.info("JPAQL: " + genericSQL);

        return em.createQuery(genericSQL).getResultList();
    }

    public <T> T[] toArray() {
        logger.info("JPAQL: " + genericSQL);
        
        List<T> entities = em.createQuery(genericSQL).getResultList();
        if (entities == null)
            return null;
        
        return (T[]) entities.toArray();
    }

    // Repository transaction implementation
    public <T> boolean add(T entity) {
        em.getTransaction().begin();
        try {
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            // em.close();
        }
    }

    public <T> boolean addAll(Collection<T> entities) {
        em.getTransaction().begin();
        try {
            for (Object c : entities){
                em.merge(c);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public <T> boolean remove(T entity) {
        em.getTransaction().begin();
        try {
            entity = em.merge(entity);
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            // em.close();
        }
    }

    public <T> boolean removeAll(Collection<T> entities) {
        em.getTransaction().begin();
        try {
            for (Object c : entities){
                em.remove(c);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    // Others
    public int size() {
        String sqlCount = "SELECT count(o) FROM "
                + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.')+1)
                + " o";

        logger.info("JPAQL: " + sqlCount);

        Long size = (Long) em.
                createQuery(sqlCount).getSingleResult();
        return size.intValue();
    }
}
