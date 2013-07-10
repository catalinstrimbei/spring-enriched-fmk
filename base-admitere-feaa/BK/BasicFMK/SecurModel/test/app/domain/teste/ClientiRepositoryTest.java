/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.teste;

import app.domain.model.AdresaFactory;
import app.domain.model.Client;
import app.domain.model.ClientiRepository;
import app.domain.patterns.IRepository;
import java.util.Collection;
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
public class ClientiRepositoryTest {
    private static EntityManager em;
    private static ClientiRepository repository;

    public ClientiRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BOMPU");
        em = emf.createEntityManager();
        repository = new ClientiRepository(em);
        System.out.println("setUpClass runned");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        em.close();
        System.out.println("tearDownClass runned");
    }

    @Before
    public void setUp() {
        assertTrue(repository instanceof IRepository);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testAddingClienti() {
//         Client c1 = new Client(1, "Client Test 1", new Adresa("Pacurari", "22", "700200"), "CF1", null);
//         Client c2 = new Client(2, "Client Test 2", new Adresa("Pacurari", "11", "700300"), "CF2", null);
//         Client c3 = new Client(3, "Client Test 3", new Adresa("Pacurari", "33", "700300"), "CF3", null);
         Client c1 = new Client(1, "Client Test 1",
                 AdresaFactory.getValue("Pacurari", "22", "700200"), "CF1", null);
         Client c2 = new Client(2, "Client Test 2",
                 AdresaFactory.getValue("Pacurari", "11", "700300"), "CF2", null);
         Client c3 = new Client(3, "Client Test 3",
                 AdresaFactory.getValue("Pacurari", "22", "700200"), "CF3", null);

         assertNotNull(c1.getAdresa());
         assertNotNull(c1.getAdresa().getCodpost());

         repository.add(c1);
         assertTrue(repository.size() == 1);
         repository.add(c2);
         repository.add(c3);
         assertTrue(repository.size() == 3);
//
//         List<Client> clientiCollection = new ArrayList<Client>();
//         clientiCollection.add(c2);
//         clientiCollection.add(c3);
//
//         repository.addAll(clientiCollection);
//         assertTrue(repository.size() == 3);
     }
     //@Test
     public void testGettingClienti() {
        Client c1 = repository.getById(1);
        assertNotNull(c1);

        Collection<Client> l1 = repository.get(new Client(null, "Client Test 2", null, null, null));
        assertTrue(l1.iterator().hasNext());

        Collection<Client> l2 = repository.get(new Client(null, null, null, "CF3", null));
        assertTrue(l2.iterator().hasNext());

        assertTrue(repository.toArray().length == 3);
        assertTrue(repository.toCollection().size() == 3);

        Collection<Client> l3 = repository.getByDencl("'Client Test 2%'");
        //assertTrue(l3.iterator().hasNext());
        assertTrue(l3.size() > 0);

        for (Object c : repository.toCollection()){
            System.out.println(c);
        }
     }
     //@Test
     public void testRemovingClienti() {
         Client c1 = (Client) repository.getById(1);
         repository.remove(c1);
         assertTrue(repository.size() == 2);

         Collection clienti = repository.toCollection();
         repository.removeAll(clienti);

         assertTrue(repository.size() == 0);
     }
}