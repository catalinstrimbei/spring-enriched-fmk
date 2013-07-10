/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.domain.model;

import app.domain.patterns.IRepository;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author catalin
 */
public class RepositoryFactory {

    private static EntityManagerFactory emf;
            //= Persistence.createEntityManagerFactory("SecurPU");
    private static EntityManager em;
            //= emf.createEntityManager();
    public static void setEntityManagerFactory(EntityManagerFactory newEmf){
        emf = newEmf;
        em = emf.createEntityManager();
    }

    private static Map<Class, IRepository> repositories =
            new HashMap<Class, IRepository>();

    public static <T> T getRepositoryOf(Class entityClass) {
        IRepository repository = repositories.get(entityClass);

        if (repository == null) {
            if (entityClass.equals(AppUser.class)) {
                repository = new AppUsersRepository(em);
                repositories.put(AppUser.class, repository);
            }
            if (entityClass.equals(AppRole.class)) {
                repository = new AppRolesRepository(em);
                repositories.put(AppRole.class, repository);
            }
        }
        return (T) repository;
    }

    public static void addRepository(){

    }
}
