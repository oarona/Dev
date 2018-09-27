/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author SupremeBeing
 */
@Entity
@Table(name = "LINE_ITEM")
public class LineItem
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private long id;

    @Column(name = "Quantity", nullable = false)
    private long quantity;

    @Column(name = "Description", nullable = false, length = 500)
    private String description;

    @Column(name = "Unit_Price", nullable = false)
    private BigDecimal unitPrice;

    @JoinColumn(name = "Invoice_Id")
    private long invoiceId;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public long getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId)
    {
        this.invoiceId = invoiceId;
    }
}
