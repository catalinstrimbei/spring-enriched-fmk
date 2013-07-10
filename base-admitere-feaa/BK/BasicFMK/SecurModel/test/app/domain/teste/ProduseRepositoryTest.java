/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.teste;

import app.domain.model.Produs;
import app.domain.model.ProduseRepository;
import app.domain.model.RepositoryFactory;
import app.domain.model.UnitateMasura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
public class ProduseRepositoryTest {
    private static Logger logger = Logger.getLogger(ProduseRepositoryTest.class .getName());

    private static EntityManager em;
    private static ProduseRepository repository;
    
    public ProduseRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BOMPU");
        em = emf.createEntityManager();
        repository = RepositoryFactory.getRepositoryOf(Produs.class);
        logger.info("setUpClass runned");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        em.close();
        logger.info("tearDownClass runned");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    @Test
    public void test_getById() {
        assertNotNull("ProduseRepository not initialized", repository);
        Produs produs = repository.getById(new Integer(1));
        assertNotNull("Produs not found: getById", produs);
        logger.info("Found produs: " + produs + ", UM:" + produs.getUm());
    }

//    @Test
    public void test_get(){
        assertNotNull("ProduseRepository not initialized", repository);
        Produs produs = new Produs(null, null);
        //produs.setDenpr("Produs 2");
        //produs.setCodpr(1);
        produs.setUm(UnitateMasura.KILOGRAM);
        List<Produs> produse = (List<Produs>) repository.get(produs);
        assertNotNull("Produs not found: get", produse);
        assertTrue("Produs not returned: get", produse.size() > 0);
        for (Produs p : produse){
            logger.info(p.toString());
        }
    }

//    @Test
    public void test_getAll(){
        assertNotNull("ProduseRepository not initialized", repository);
        Collection<Produs> produse = repository.toCollection();
        assertNotNull("Produs not found as collection: getAll", produse);
        assertTrue("Produs not returned as collection: getAll", produse.size() > 0);
        Object[] produseArray = repository.toArray();
        assertNotNull("Produs not found as array: getAll", produseArray);
        assertTrue("Produs not returned as array: getAll", produseArray.length > 0);
    }

//    @Test
    public void test_add_remove(){
        assertNotNull("ProduseRepository not initialized", repository);
        Produs pNew = new Produs(901, "P901", UnitateMasura.METRU, "Teste", .19);
        assertTrue(repository.add(pNew));

        Produs pTest = repository.getById(901);
        pTest.setDenpr("Produs 901");
        assertTrue(repository.add(pTest));

        List<Produs> pReTest = (List<Produs>) repository.get(new Produs(null, "Produs 901"));
        assertTrue(pReTest != null && pReTest.size()>0);

        assertTrue(repository.remove(pTest));
        pReTest = (List<Produs>) repository.get(new Produs(null, "Produs 901"));
        assertTrue(pReTest == null || pReTest.size()==0);
    }

    @Test
    public void test_add_remove_all(){
        assertNotNull("ProduseRepository not initialized", repository);
        List<Produs> produseNew = new ArrayList<Produs>();
        produseNew.add(new Produs(901, "P901", UnitateMasura.METRU, "Teste", .19));
        produseNew.add(new Produs(902, "P902", UnitateMasura.METRU, "Teste", .19));
        assertTrue(repository.addAll(produseNew));

        List<Produs> produseTest = (List<Produs>) repository.get(new Produs(null, null, null,
                "Teste", null));
        produseTest.get(0).setDenpr("Produs 901");
        produseTest.get(1).setDenpr("Produs 902");
        assertTrue(repository.addAll(produseTest));

        List<Produs> pReTest = (List<Produs>) repository.get(new Produs(null, null, null,
                "Teste", null));
        assertTrue(pReTest != null && pReTest.size()>0);

//        Integer countBefore = repository.size();
//        assertTrue(repository.removeAll(pReTest));
////        pReTest = (List<Produs>) repository.get(new Produs(null, null, null,
////                "Teste", null));
////        assertTrue(pReTest == null || pReTest.size()==0);
//        Integer countAfter = repository.size();
//        assertTrue(countBefore > countAfter);
    }
}