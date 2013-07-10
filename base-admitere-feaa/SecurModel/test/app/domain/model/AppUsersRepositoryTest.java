/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author catalin
 */
public class AppUsersRepositoryTest {
    private static Logger logger = Logger.getLogger(AppUsersRepositoryTest.class .getName());
    public AppUsersRepositoryTest() {
    }

    //private static EntityManager em;
    private static AppUsersRepository repository;

    @BeforeClass
    public static void setUpClass() throws Exception {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecurPU");
       //em = emf.createEntityManager();
       RepositoryFactory.setEntityManagerFactory(emf);
       repository = RepositoryFactory.getRepositoryOf(AppUser.class);
       logger.info("setUpClass runned");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //em.close();
        logger.info("tearDownClass runned");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // @Test
    public void toCollection(){
        assertNotNull("ProduseRepository not initialized", repository);
        Collection<AppUser> users = repository.toCollection();
        assertNotNull("Produs not found as collection: getAll", users);
        assertTrue("Produs not returned as collection: getAll", users.size() > 0);
        for (AppUser user : users){
            logger.info("USER: " + user.getDbUser());
            for (AppRole role : repository.getEnabledRoleOf(user)){
                logger.info("--- has role active: " + role.getRoleName());
            }
        }
    }

    @Test
    public void get(){
        Collection<AppUser> users = repository.get(new AppUser(null, "CATALIN"));
        assertNotNull("Produs not found as collection: getAll", users);
        for(AppUser user: users)
            logger.info("USER: " + user.getDbUser());
    }
}