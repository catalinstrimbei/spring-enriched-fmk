/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import app.domain.patterns.impl.EntityRepository;
import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author catalin
 */
public class AppRolesRepository extends EntityRepository{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public AppRolesRepository(EntityManager em) {
        super(em, app.domain.model.AppRole.class);
    }

    @Override
    public <T> Collection<T> toCollection() {
        logger.info("JPAQL: " + genericSQL);
        return em.createQuery(genericSQL + " ORDER BY o.roleName").getResultList();
    }
}
