/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author SupremeBeing
 */
public class InvoiceLineItemWrapper {

    private BigDecimal allVatTotal;
    private BigDecimal allTotalExclVat;
    private BigDecimal allTotalInclVat;

    private List<InvoicePayload> invoicePayload;

    public List<InvoicePayload> getInvoiceLineItems() {
        return invoicePayload;
    }

    public void setInvoiceLineItems(List<InvoicePayload> invoiceLineItems) {
        this.invoicePayload = invoiceLineItems;
    }

    public BigDecimal getAllVatTotal() {
        return allVatTotal;
    }

    public void setAllVatTotal(BigDecimal allVatTotal) {
        this.allVatTotal = allVatTotal;
    }

    public BigDecimal getAllTotalExclVat() {
        return allTotalExclVat;
    }

    public void setAllTotalExclVat(BigDecimal allTotalExclVat) {
        this.allTotalExclVat = allTotalExclVat;
    }

    public BigDecimal getAllTotalInclVat() {
        return allTotalInclVat;
    }

    public void setAllTotalInclVat(BigDecimal allTotalInclVat) {
        this.allTotalInclVat = allTotalInclVat;
    }
}
