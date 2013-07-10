/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.teste;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author catalin
 * Test agregat si repositoryAgregat
 * 
 */
public class FacturiAggregateTest {

    public FacturiAggregateTest() {
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
     public void testAccessComponenteInterne() {
         // identitate agregat
         // dereferintiere componente interne
         // return componente interne clone
     }

     @Test
     public void testTranzactieUpdateAgregat() {
         // verifica reguli de integritate agregat:
         // - valoare limita a facturii
     }

     @Test
     public void testTranzactieDeleteAgregat() {
         // sterge agregat intreg
         // - urmarire relatii delimitare agregat pentru propagare in cascada stergere
     }
}