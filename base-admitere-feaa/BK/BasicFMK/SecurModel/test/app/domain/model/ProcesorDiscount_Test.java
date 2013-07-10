/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

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
public class ProcesorDiscount_Test {

    public ProcesorDiscount_Test() {
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

    /**
     * Test of getDiscountFactura method, of class ProcesorDiscountImpl.
     */
    @Test
    public void testGetDiscountFactura() {
        System.out.println("getDiscountFactura");
        Factura factura = null;
        ProcesorDiscountImpl instance = new ProcesorDiscountImpl();
        Double expResult = null;
        Double result = instance.getDiscountFactura(factura);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}