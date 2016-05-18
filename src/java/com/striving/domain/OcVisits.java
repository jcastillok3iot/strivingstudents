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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_visits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcVisits.findAll", query = "SELECT o FROM OcVisits o"),
    @NamedQuery(name = "OcVisits.findByIdVisit", query = "SELECT o FROM OcVisits o WHERE o.idVisit = :idVisit"),
    @NamedQuery(name = "OcVisits.findByIdAd", query = "SELECT o FROM OcVisits o WHERE o.idAd = :idAd"),
    @NamedQuery(name = "OcVisits.findByIdUser", query = "SELECT o FROM OcVisits o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcVisits.findByContacted", query = "SELECT o FROM OcVisits o WHERE o.contacted = :contacted"),
    @NamedQuery(name = "OcVisits.findByCreated", query = "SELECT o FROM OcVisits o WHERE o.created = :created"),
    @NamedQuery(name = "OcVisits.findByIpAddress", query = "SELECT o FROM OcVisits o WHERE o.ipAddress = :ipAddress")})
public class OcVisits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_visit")
    private Integer idVisit;
    @Column(name = "id_ad")
    private Integer idAd;
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contacted")
    private boolean contacted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "ip_address")
    private BigInteger ipAddress;

    public OcVisits() {
    }

    public OcVisits(Integer idVisit) {
        this.idVisit = idVisit;
    }

    public OcVisits(Integer idVisit, boolean contacted, Date created) {
        this.idVisit = idVisit;
        this.contacted = contacted;
        this.created = created;
    }

    public Integer getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(Integer idVisit) {
        this.idVisit = idVisit;
    }

    public Integer getIdAd() {
        return idAd;
    }

    public void setIdAd(Integer idAd) {
        this.idAd = idAd;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public boolean getContacted() {
        return contacted;
    }

    public void setContacted(boolean contacted) {
        this.contacted = contacted;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisit != null ? idVisit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcVisits)) {
            return false;
        }
        OcVisits other = (OcVisits) object;
        if ((this.idVisit == null && other.idVisit != null) || (this.idVisit != null && !this.idVisit.equals(other.idVisit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcVisits[ idVisit=" + idVisit + " ]";
    }
    
}
