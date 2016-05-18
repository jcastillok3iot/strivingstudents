/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcOrders.findAll", query = "SELECT o FROM OcOrders o"),
    @NamedQuery(name = "OcOrders.findByIdOrder", query = "SELECT o FROM OcOrders o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "OcOrders.findByIdUser", query = "SELECT o FROM OcOrders o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcOrders.findByIdAd", query = "SELECT o FROM OcOrders o WHERE o.idAd = :idAd"),
    @NamedQuery(name = "OcOrders.findByIdProduct", query = "SELECT o FROM OcOrders o WHERE o.idProduct = :idProduct"),
    @NamedQuery(name = "OcOrders.findByPaymethod", query = "SELECT o FROM OcOrders o WHERE o.paymethod = :paymethod"),
    @NamedQuery(name = "OcOrders.findByCreated", query = "SELECT o FROM OcOrders o WHERE o.created = :created"),
    @NamedQuery(name = "OcOrders.findByPayDate", query = "SELECT o FROM OcOrders o WHERE o.payDate = :payDate"),
    @NamedQuery(name = "OcOrders.findByCurrency", query = "SELECT o FROM OcOrders o WHERE o.currency = :currency"),
    @NamedQuery(name = "OcOrders.findByAmount", query = "SELECT o FROM OcOrders o WHERE o.amount = :amount"),
    @NamedQuery(name = "OcOrders.findByStatus", query = "SELECT o FROM OcOrders o WHERE o.status = :status"),
    @NamedQuery(name = "OcOrders.findByDescription", query = "SELECT o FROM OcOrders o WHERE o.description = :description"),
    @NamedQuery(name = "OcOrders.findByTxnId", query = "SELECT o FROM OcOrders o WHERE o.txnId = :txnId"),
    @NamedQuery(name = "OcOrders.findByFeaturedDays", query = "SELECT o FROM OcOrders o WHERE o.featuredDays = :featuredDays")})
public class OcOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_order")
    private Integer idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_ad")
    private Integer idAd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_product")
    private String idProduct;
    @Size(max = 20)
    @Column(name = "paymethod")
    private String paymethod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "pay_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Size(max = 145)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "txn_id")
    private String txnId;
    @Column(name = "featured_days")
    private Integer featuredDays;

    public OcOrders() {
    }

    public OcOrders(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public OcOrders(Integer idOrder, int idUser, String idProduct, Date created, String currency, BigDecimal amount, boolean status) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.created = created;
        this.currency = currency;
        this.amount = amount;
        this.status = status;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Integer getIdAd() {
        return idAd;
    }

    public void setIdAd(Integer idAd) {
        this.idAd = idAd;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getFeaturedDays() {
        return featuredDays;
    }

    public void setFeaturedDays(Integer featuredDays) {
        this.featuredDays = featuredDays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcOrders)) {
            return false;
        }
        OcOrders other = (OcOrders) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcOrders[ idOrder=" + idOrder + " ]";
    }
    
}
