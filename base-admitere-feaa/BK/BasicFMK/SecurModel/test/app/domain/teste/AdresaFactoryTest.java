/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.teste;

import app.domain.model.Adresa;
import app.domain.model.AdresaFactory;
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
public class AdresaFactoryTest {

    public AdresaFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
     public void testGetAdresa() {
         Adresa a1 = AdresaFactory.getValue("Pacurari", "12", "700200");
         Adresa a2 = AdresaFactory.getValue("Pacurari", "12", "700200");
         Adresa a3 = AdresaFactory.getValue("Pacurari", "14", "700201");
         
         assertTrue(a1 == a2);
         assertFalse(a1 == a3);
     }

}