/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "oc_reviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcReviews.findAll", query = "SELECT o FROM OcReviews o"),
    @NamedQuery(name = "OcReviews.findByIdReview", query = "SELECT o FROM OcReviews o WHERE o.idReview = :idReview"),
    @NamedQuery(name = "OcReviews.findByIdUser", query = "SELECT o FROM OcReviews o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcReviews.findByIdAd", query = "SELECT o FROM OcReviews o WHERE o.idAd = :idAd"),
    @NamedQuery(name = "OcReviews.findByRate", query = "SELECT o FROM OcReviews o WHERE o.rate = :rate"),
    @NamedQuery(name = "OcReviews.findByDescription", query = "SELECT o FROM OcReviews o WHERE o.description = :description"),
    @NamedQuery(name = "OcReviews.findByCreated", query = "SELECT o FROM OcReviews o WHERE o.created = :created"),
    @NamedQuery(name = "OcReviews.findByIpAddress", query = "SELECT o FROM OcReviews o WHERE o.ipAddress = :ipAddress"),
    @NamedQuery(name = "OcReviews.findByStatus", query = "SELECT o FROM OcReviews o WHERE o.status = :status")})
public class OcReviews implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_review")
    private Integer idReview;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ad")
    private int idAd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private int rate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "ip_address")
    private BigInteger ipAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public OcReviews() {
    }

    public OcReviews(Integer idReview) {
        this.idReview = idReview;
    }

    public OcReviews(Integer idReview, int idUser, int idAd, int rate, String description, Date created, boolean status) {
        this.idReview = idReview;
        this.idUser = idUser;
        this.idAd = idAd;
        this.rate = rate;
        this.description = description;
        this.created = created;
        this.status = status;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAd() {
        return idAd;
    }

    public void setIdAd(int idAd) {
        this.idAd = idAd;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigInteger getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(BigInteger ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReview != null ? idReview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcReviews)) {
            return false;
        }
        OcReviews other = (OcReviews) object;
        if ((this.idReview == null && other.idReview != null) || (this.idReview != null && !this.idReview.equals(other.idReview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcReviews[ idReview=" + idReview + " ]";
    }
    
}
