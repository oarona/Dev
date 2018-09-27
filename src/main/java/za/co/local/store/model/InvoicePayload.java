/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author SupremeBeing
 */
public class InvoicePayload
{

    private long id;
    @Size(min = 2, message = "At least 2 characters should be entered for the client")
    private String client;
    @Positive(message = "The vat rate should be more than 0 in value")
    private long vatRate;
    @NotNull
    private Date invoiceDate;
    private BigDecimal invoiceTotalExclVat;
    private BigDecimal vatTotal;
    private BigDecimal invoiceTotalInclVat;
    @Valid
    private List<InvoiceLineItem> lineItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public long getVatRate() {
        return vatRate;
    }

    public void setVatRate(long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceTotalExclVat() {
        return invoiceTotalExclVat;
    }

    public void setInvoiceTotalExclVat(BigDecimal invoiceTotalExclVat) {
        this.invoiceTotalExclVat = invoiceTotalExclVat;
    }

    public BigDecimal getVatTotal() {
        return vatTotal;
    }

    public void setVatTotal(BigDecimal vatTotal) {
        this.vatTotal = vatTotal;
    }

    public BigDecimal getInvoiceTotalInclVat() {
        return invoiceTotalInclVat;
    }

    public void setInvoiceTotalInclVat(BigDecimal invoiceTotalInclVat) {
        this.invoiceTotalInclVat = invoiceTotalInclVat;
    }

    public List<InvoiceLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<InvoiceLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    
    
    @Override
    public String toString()
    {
        return "InvoicePayload{" + "id=" + id + ", client=" + client + ", vatRate=" + vatRate + ", invoiceDate=" + invoiceDate + ", lineItems=" + lineItems + '}';
    }
}
