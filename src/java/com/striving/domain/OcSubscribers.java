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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_subscribers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcSubscribers.findAll", query = "SELECT o FROM OcSubscribers o"),
    @NamedQuery(name = "OcSubscribers.findByIdSubscribe", query = "SELECT o FROM OcSubscribers o WHERE o.idSubscribe = :idSubscribe"),
    @NamedQuery(name = "OcSubscribers.findByIdUser", query = "SELECT o FROM OcSubscribers o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcSubscribers.findByIdCategory", query = "SELECT o FROM OcSubscribers o WHERE o.idCategory = :idCategory"),
    @NamedQuery(name = "OcSubscribers.findByIdLocation", query = "SELECT o FROM OcSubscribers o WHERE o.idLocation = :idLocation"),
    @NamedQuery(name = "OcSubscribers.findByMinPrice", query = "SELECT o FROM OcSubscribers o WHERE o.minPrice = :minPrice"),
    @NamedQuery(name = "OcSubscribers.findByMaxPrice", query = "SELECT o FROM OcSubscribers o WHERE o.maxPrice = :maxPrice"),
    @NamedQuery(name = "OcSubscribers.findByCreated", query = "SELECT o FROM OcSubscribers o WHERE o.created = :created")})
public class OcSubscribers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subscribe")
    private Integer idSubscribe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_category")
    private int idCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_location")
    private int idLocation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_price")
    private BigDecimal minPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_price")
    private BigDecimal maxPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public OcSubscribers() {
    }

    public OcSubscribers(Integer idSubscribe) {
        this.idSubscribe = idSubscribe;
    }

    public OcSubscribers(Integer idSubscribe, int idUser, int idCategory, int idLocation, BigDecimal minPrice, BigDecimal maxPrice, Date created) {
        this.idSubscribe = idSubscribe;
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.idLocation = idLocation;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.created = created;
    }

    public Integer getIdSubscribe() {
        return idSubscribe;
    }

    public void setIdSubscribe(Integer idSubscribe) {
        this.idSubscribe = idSubscribe;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubscribe != null ? idSubscribe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcSubscribers)) {
            return false;
        }
        OcSubscribers other = (OcSubscribers) object;
        if ((this.idSubscribe == null && other.idSubscribe != null) || (this.idSubscribe != null && !this.idSubscribe.equals(other.idSubscribe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcSubscribers[ idSubscribe=" + idSubscribe + " ]";
    }
    
}
