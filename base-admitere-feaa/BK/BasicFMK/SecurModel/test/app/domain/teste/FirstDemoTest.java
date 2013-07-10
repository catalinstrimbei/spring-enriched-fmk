/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.domain.teste;

import app.domain.model.Adresa;
import app.domain.model.Client;
import app.domain.model.Produs;
import app.domain.model.UnitateMasura;
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
public class FirstDemoTest {

    private static EntityManager em;

    public FirstDemoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BOMPU");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        em.close();
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
    @Test
    public void testJPAClienti() {
        Client client = new Client(1001, "Client 1 SRL",
                new Adresa("Pacurari", "700325", "12A"), "R1001", null);
        persist(client);
        Client c = (Client) em.createQuery("SELECT c FROM Client c WHERE c.codcl = 1001").getSingleResult();

        assertNotNull(c);
        assertSame(client.getAdresa(), c.getAdresa());
    }

    @Test
    public void testJPAProduse() {
        Produs produs = new Produs(1, "Produs 1",
                new UnitateMasura("Kg", "Kilograme"), null, .19);
        persist(produs);

        Produs produsPersistent = (Produs) em.
                createQuery("SELECT p FROM Produs p WHERE p.codpr = 1").getSingleResult();

        assertNotNull(produsPersistent);

        assertSame(produs.getUm(), produsPersistent.getUm());
    }
    @Test
    public void testClientiRepository(){
        
    }


    // support/utilities operations
    public void persist(Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            //em.close();
        }
    }
}