/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author SupremeBeing
 */
@Entity
@Table(name = "INVOICE")
@NamedQuery(query = "select i from Invoice i", name = "query_find_all_invoices")
public class Invoice
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Client", nullable = false, length = 150)
    private String client;

    @Column(name = "Vat_Rate", nullable = false)
    private long vatRate;

    @Column(name = "Invoice_Total", nullable = false)
    private BigDecimal invoiceTotal;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "Invoice_Date", nullable = false)
    private Date invoiceDate;

    @OneToMany(mappedBy = "invoiceId", orphanRemoval = true)
    List<LineItem> lineItems;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getClient()
    {
        return client;
    }

    public void setClient(String client)
    {
        this.client = client;
    }

    public long getVatRate()
    {
        return vatRate;
    }

    public BigDecimal getInvoiceTotal()
    {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal)
    {
        this.invoiceTotal = invoiceTotal;
    }

    public void setVatRate(long vatRate)
    {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems)
    {
        this.lineItems = lineItems;
    }
}
