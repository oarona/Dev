/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.model;

import java.math.BigDecimal;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author SupremeBeing
 */
public class InvoiceLineItem {
    private long id;
    @Positive(message = "quantity should be more than 0")
    private long quantity;
    @Size(min = 5, message = "please enter at least 5 characters for the description of the item")
    private String description;
    @Positive(message = "unitPrice should be more than 0")
    private BigDecimal unitPrice;
    private BigDecimal lineVatTotal;
    private BigDecimal lineTotalExclVat;
    private BigDecimal lineTotalInclVat;
    
    private long invoiceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getVatLineTotal() {
        return lineVatTotal;
    }

    public void setVatLineTotal(BigDecimal vatLineTotal) {
        this.lineVatTotal = vatLineTotal;
    }

    public BigDecimal getLineTotalExclVat() {
        return lineTotalExclVat;
    }

    public void setLineTotalExclVat(BigDecimal lineTotalExclVat) {
        this.lineTotalExclVat = lineTotalExclVat;
    }

    public BigDecimal getLineTotalInclVat() {
        return lineTotalInclVat;
    }

    public void setLineTotalInclVat(BigDecimal lineTotalInclVat) {
        this.lineTotalInclVat = lineTotalInclVat;
    }

    @Override
    public String toString() {
        return "InvoiceLineItem{" + "id=" + id + ", quantity=" + quantity + ", description=" + description + ", unitPrice=" + unitPrice + ", invoiceId=" + invoiceId + '}';
    }
}
