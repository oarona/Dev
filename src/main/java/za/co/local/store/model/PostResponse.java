/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.local.store.model;

import java.math.BigDecimal;

/**
 *
 * @author SupremeBeing
 */
public class PostResponse
{
    private int totalLineItems;
    private BigDecimal invoiceTotal;
    private long idSaved;

    public int getTotalLineItems()
    {
        return totalLineItems;
    }

    public void setTotalLineItems(int totalLineItems)
    {
        this.totalLineItems = totalLineItems;
    }

    public BigDecimal getInvoiceTotal()
    {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal)
    {
        this.invoiceTotal = invoiceTotal;
    }

    public long getIdSaved()
    {
        return idSaved;
    }

    public void setIdSaved(long idSaved)
    {
        this.idSaved = idSaved;
    }
    
    
}
