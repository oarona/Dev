/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import za.co.local.store.entity.Invoice;
import za.co.local.store.entity.LineItem;
import za.co.local.store.model.InvoiceLineItem;
import za.co.local.store.model.InvoiceLineItemWrapper;
import za.co.local.store.model.InvoicePayload;

/**
 *
 * @author SupremeBeing
 */
@Repository
@Transactional
public class InvoiceDaoService {

    @PersistenceContext
    private EntityManager entityManager;

    public Invoice insertInvoice(InvoicePayload invoicePayload) {

        Invoice invoice = new Invoice();

        invoice.setClient(invoicePayload.getClient());
        invoice.setInvoiceDate(invoicePayload.getInvoiceDate());
        invoice.setVatRate(invoicePayload.getVatRate());

        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem;

        BigDecimal invoiceTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        if (invoicePayload.getLineItems() != null) {
            for (InvoiceLineItem invoiceLineItem : invoicePayload.getLineItems()) {
                lineItem = new LineItem();
                lineItem.setDescription(invoiceLineItem.getDescription());
                lineItem.setInvoiceId(invoicePayload.getId());
                lineItem.setQuantity(invoiceLineItem.getQuantity());
                lineItem.setUnitPrice(invoiceLineItem.getUnitPrice());
                lineItems.add(lineItem);

                // add multiplication of unitprice and quantity
                invoiceTotal = invoiceTotal.add(invoiceLineItem.getUnitPrice()
                        .multiply(new BigDecimal(invoiceLineItem.getQuantity()))).multiply(invoiceTotal);
            }
        }

        // add vat to the total of all multiplications of quantity and unit price
        invoiceTotal = invoiceTotal.add(invoiceTotal
                .multiply(new BigDecimal(String.valueOf(invoice.getVatRate())))
                .divide(new BigDecimal("100")));
        invoice.setInvoiceTotal(invoiceTotal);
        entityManager.persist(invoice);
        entityManager.flush();

        invoice.setLineItems(lineItems);
        for (LineItem lineItem1 : lineItems) {
            lineItem1.setInvoiceId(invoice.getId());
            entityManager.persist(lineItem1);
        }

        return invoice;
    }

    public InvoicePayload find(long id) {

        Invoice invoice = entityManager.find(Invoice.class, id);
        InvoicePayload invoicePayload = new InvoicePayload();
        List<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
        InvoiceLineItem invoiceLineItem;
        if (invoice != null) {

            invoicePayload.setClient(invoice.getClient());
            invoicePayload.setId(invoice.getId());
            invoicePayload.setInvoiceDate(invoice.getInvoiceDate());
            invoicePayload.setVatRate(invoice.getVatRate());

            if (invoice.getLineItems() != null && invoice.getLineItems().size() > 0) {

                BigDecimal invoiceTotalExclVat = BigDecimal.ZERO;
                BigDecimal invoiceVatTotal = BigDecimal.ZERO;
                BigDecimal invoiceTotalInclVat = BigDecimal.ZERO;

                for (LineItem lineItem : invoice.getLineItems()) {
                    invoiceLineItem = new InvoiceLineItem();
                    invoiceLineItem.setDescription(lineItem.getDescription());
                    invoiceLineItem.setId(lineItem.getId());
                    invoiceLineItem.setInvoiceId(lineItem.getInvoiceId());
                    invoiceLineItem.setQuantity(lineItem.getQuantity());
                    invoiceLineItem.setUnitPrice(lineItem.getUnitPrice());

                    BigDecimal lineTotalExclVat = lineItem.getUnitPrice()
                            .multiply(new BigDecimal(String.valueOf(lineItem.getQuantity()))).setScale(2, RoundingMode.HALF_UP);
                    invoiceLineItem.setLineTotalExclVat(lineTotalExclVat);

                    BigDecimal lineVatTotal = lineTotalExclVat.multiply(new BigDecimal(String.valueOf(invoice.getVatRate()))
                            .divide(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP);
                    invoiceLineItem.setVatLineTotal(lineVatTotal);

                    BigDecimal lineTotalInclVat = lineTotalExclVat.add(lineVatTotal).setScale(2, RoundingMode.HALF_UP);
                    invoiceLineItem.setLineTotalInclVat(lineTotalInclVat);

                    // add totals to invoice 
                    invoiceTotalExclVat = invoiceTotalExclVat.add(lineTotalExclVat).setScale(2, RoundingMode.HALF_UP);
                    invoiceVatTotal = invoiceVatTotal.add(lineVatTotal).setScale(2, RoundingMode.HALF_UP);
                    invoiceTotalInclVat = invoiceTotalInclVat.add(lineTotalInclVat).setScale(2, RoundingMode.HALF_UP);

                    invoiceLineItems.add(invoiceLineItem);
                }

                invoicePayload.setLineItems(invoiceLineItems);

                invoicePayload.setInvoiceTotalExclVat(invoiceTotalExclVat);
                invoicePayload.setInvoiceTotalInclVat(invoiceTotalInclVat);
                invoicePayload.setVatTotal(invoiceVatTotal);
            }
        }

        return invoicePayload;
    }

    public InvoiceLineItemWrapper findAll() {

        Query query = entityManager.createNamedQuery("query_find_all_invoices", Invoice.class);

        List<Invoice> invoices = query.getResultList();

        InvoiceLineItemWrapper invoiceLineItemWrapper = new InvoiceLineItemWrapper();
        List<InvoicePayload> invoicePayloads = new ArrayList<>();
        InvoicePayload invoicePayload;

        List<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
        InvoiceLineItem invoiceLineItem;

        if (invoices != null && invoices.size() > 0) {

            BigDecimal allTotalExclVat = BigDecimal.ZERO;
            BigDecimal allVatTotal = BigDecimal.ZERO;
            BigDecimal allTotalInclVat = BigDecimal.ZERO;

            for (Invoice invoice : invoices) {
                invoicePayload = new InvoicePayload();
                invoicePayload.setClient(invoice.getClient());
                invoicePayload.setId(invoice.getId());
                invoicePayload.setInvoiceDate(invoice.getInvoiceDate());
                invoicePayload.setVatRate(invoice.getVatRate());

                if (invoice.getLineItems() != null && invoice.getLineItems().size() > 0) {

                    BigDecimal invoiceTotalExclVat = BigDecimal.ZERO;
                    BigDecimal invoiceVatTotal = BigDecimal.ZERO;
                    BigDecimal invoiceTotalInclVat = BigDecimal.ZERO;

                    for (LineItem lineItem : invoice.getLineItems()) {
                        invoiceLineItem = new InvoiceLineItem();
                        invoiceLineItem.setDescription(lineItem.getDescription());
                        invoiceLineItem.setId(lineItem.getId());
                        invoiceLineItem.setInvoiceId(lineItem.getInvoiceId());
                        invoiceLineItem.setQuantity(lineItem.getQuantity());
                        invoiceLineItem.setUnitPrice(lineItem.getUnitPrice());

                        BigDecimal lineTotalExclVat = lineItem.getUnitPrice()
                                .multiply(new BigDecimal(String.valueOf(lineItem.getQuantity()))).setScale(2, RoundingMode.HALF_UP);
                        invoiceLineItem.setLineTotalExclVat(lineTotalExclVat);

                        BigDecimal lineVatTotal = lineTotalExclVat.multiply(new BigDecimal(String.valueOf(invoice.getVatRate()))
                                .divide(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP);
                        invoiceLineItem.setVatLineTotal(lineVatTotal);

                        BigDecimal lineTotalInclVat = lineTotalExclVat.add(lineVatTotal).setScale(2, RoundingMode.HALF_UP);
                        invoiceLineItem.setLineTotalInclVat(lineTotalInclVat);

                        // add totals to invoice 
                        invoiceTotalExclVat = invoiceTotalExclVat.add(lineTotalExclVat).setScale(2, RoundingMode.HALF_UP);
                        invoiceVatTotal = invoiceVatTotal.add(lineVatTotal).setScale(2, RoundingMode.HALF_UP);
                        invoiceTotalInclVat = invoiceTotalInclVat.add(lineTotalInclVat).setScale(2, RoundingMode.HALF_UP);

                        invoiceLineItems.add(invoiceLineItem);
                    }

                    invoicePayload.setLineItems(invoiceLineItems);

                    invoicePayload.setInvoiceTotalExclVat(invoiceTotalExclVat);
                    invoicePayload.setInvoiceTotalInclVat(invoiceTotalInclVat);
                    invoicePayload.setVatTotal(invoiceVatTotal);

                    // add totals to invoice wrapper
                    allTotalExclVat = allTotalExclVat.add(invoiceTotalExclVat).setScale(2, RoundingMode.HALF_UP);
                    allVatTotal = allVatTotal.add(invoiceVatTotal).setScale(2, RoundingMode.HALF_UP);
                    allTotalInclVat = allTotalInclVat.add(invoiceTotalInclVat).setScale(2, RoundingMode.HALF_UP);
                }

                invoicePayloads.add(invoicePayload);
            }
            invoiceLineItemWrapper.setAllTotalExclVat(allTotalExclVat);
            invoiceLineItemWrapper.setAllTotalInclVat(allTotalInclVat);
            invoiceLineItemWrapper.setAllVatTotal(allVatTotal);
        }

        invoiceLineItemWrapper.setInvoiceLineItems(invoicePayloads);
        return invoiceLineItemWrapper;
    }
}
