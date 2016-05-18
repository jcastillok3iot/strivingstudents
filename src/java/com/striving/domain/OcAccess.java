/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcAccess.findAll", query = "SELECT o FROM OcAccess o"),
    @NamedQuery(name = "OcAccess.findByIdAccess", query = "SELECT o FROM OcAccess o WHERE o.idAccess = :idAccess"),
    @NamedQuery(name = "OcAccess.findByIdRole", query = "SELECT o FROM OcAccess o WHERE o.idRole = :idRole"),
    @NamedQuery(name = "OcAccess.findByAccess", query = "SELECT o FROM OcAccess o WHERE o.access = :access")})
public class OcAccess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access")
    private Integer idAccess;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_role")
    private int idRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "access")
    private String access;

    public OcAccess() {
    }

    public OcAccess(Integer idAccess) {
        this.idAccess = idAccess;
    }

    public OcAccess(Integer idAccess, int idRole, String access) {
        this.idAccess = idAccess;
        this.idRole = idRole;
        this.access = access;
    }

    public Integer getIdAccess() {
        return idAccess;
    }

    public void setIdAccess(Integer idAccess) {
        this.idAccess = idAccess;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccess != null ? idAccess.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcAccess)) {
            return false;
        }
        OcAccess other = (OcAccess) object;
        if ((this.idAccess == null && other.idAccess != null) || (this.idAccess != null && !this.idAccess.equals(other.idAccess))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcAccess[ idAccess=" + idAccess + " ]";
    }
    
}
