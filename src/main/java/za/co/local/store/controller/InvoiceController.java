/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.controller;

import za.co.local.store.entity.Invoice;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.local.store.constants.InvoiceConstants;
import za.co.local.store.model.*;
import za.co.local.store.service.InvoiceDaoService;

/**
 *
 * @author SupremeBeing
 */
@RestController
public class InvoiceController
{

    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceDaoService invoiceDaoService;

    @RequestMapping(value = InvoiceConstants.CREATE_INVOICE, method = RequestMethod.POST, produces = "application/json")
    public PostResponse addInvoice(@Valid @RequestBody InvoicePayload invoice)
    {
        Invoice invoiceReturned = invoiceDaoService.insertInvoice(invoice);
        
        PostResponse postResponse = new PostResponse();
        postResponse.setIdSaved(invoiceReturned.getId());
        postResponse.setInvoiceTotal(invoiceReturned.getInvoiceTotal());
        postResponse.setTotalLineItems(invoiceReturned.getLineItems().size());
        
        return postResponse;
    }

    @RequestMapping(value = InvoiceConstants.GET_INVOICES, method = RequestMethod.GET, produces = "application/json")
    public InvoiceLineItemWrapper viewAllInvoices()
    {
        return invoiceDaoService.findAll();
    }

    @RequestMapping(value = InvoiceConstants.GET_INVOICE, method = RequestMethod.GET, produces = "application/json")
    public InvoicePayload viewInvoice(@PathVariable("invoiceId") long id)
    {
        return invoiceDaoService.find(id);
    }
}
