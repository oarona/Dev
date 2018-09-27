/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.local.store.entity.Invoice;
import za.co.local.store.entity.LineItem;

/**
 *
 * @author SupremeBeing
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class InvoiceIntegrationTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    public InvoiceIntegrationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertInvoice method, of class InvoiceDaoService.
     */
    @Test
    public void testInsertInvoice() {
        System.out.println("insertInvoice");
        Invoice invoice = new Invoice();
        invoice.setClient("Clientelle");
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(14);
        invoice.setInvoiceTotal(new BigDecimal(500L));
        long expResult = 0L;
        entityManager.persist(invoice);
        entityManager.flush();
        assertTrue(expResult < invoice.getId());
        
        LineItem lineItem = new LineItem();
    }

    /**
     * Test of find method, of class InvoiceDaoService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        long id = 1L;
//        Invoice result = instance.find(id);
//        assertEquals(id, result.getId());
    }

    /**
     * Test of findAll method, of class InvoiceDaoService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List expResult = null;
//        List result = instance.findAll();
//        assertTrue(result.size() > 0);
    }
    
}
