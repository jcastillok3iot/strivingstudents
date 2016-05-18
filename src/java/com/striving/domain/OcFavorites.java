/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
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
@Table(name = "oc_favorites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcFavorites.findAll", query = "SELECT o FROM OcFavorites o"),
    @NamedQuery(name = "OcFavorites.findByIdFavorite", query = "SELECT o FROM OcFavorites o WHERE o.idFavorite = :idFavorite"),
    @NamedQuery(name = "OcFavorites.findByIdUser", query = "SELECT o FROM OcFavorites o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcFavorites.findByIdAd", query = "SELECT o FROM OcFavorites o WHERE o.idAd = :idAd"),
    @NamedQuery(name = "OcFavorites.findByCreated", query = "SELECT o FROM OcFavorites o WHERE o.created = :created")})
public class OcFavorites implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_favorite")
    private Integer idFavorite;
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
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public OcFavorites() {
    }

    public OcFavorites(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public OcFavorites(Integer idFavorite, int idUser, int idAd, Date created) {
        this.idFavorite = idFavorite;
        this.idUser = idUser;
        this.idAd = idAd;
        this.created = created;
    }

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavorite != null ? idFavorite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcFavorites)) {
            return false;
        }
        OcFavorites other = (OcFavorites) object;
        if ((this.idFavorite == null && other.idFavorite != null) || (this.idFavorite != null && !this.idFavorite.equals(other.idFavorite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcFavorites[ idFavorite=" + idFavorite + " ]";
    }
    
}
