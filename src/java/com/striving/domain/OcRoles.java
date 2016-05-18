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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcRoles.findAll", query = "SELECT o FROM OcRoles o"),
    @NamedQuery(name = "OcRoles.findByIdRole", query = "SELECT o FROM OcRoles o WHERE o.idRole = :idRole"),
    @NamedQuery(name = "OcRoles.findByName", query = "SELECT o FROM OcRoles o WHERE o.name = :name"),
    @NamedQuery(name = "OcRoles.findByDescription", query = "SELECT o FROM OcRoles o WHERE o.description = :description"),
    @NamedQuery(name = "OcRoles.findByDateCreated", query = "SELECT o FROM OcRoles o WHERE o.dateCreated = :dateCreated")})
public class OcRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_role")
    private Integer idRole;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 245)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public OcRoles() {
    }

    public OcRoles(Integer idRole) {
        this.idRole = idRole;
    }

    public OcRoles(Integer idRole, Date dateCreated) {
        this.idRole = idRole;
        this.dateCreated = dateCreated;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcRoles)) {
            return false;
        }
        OcRoles other = (OcRoles) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcRoles[ idRole=" + idRole + " ]";
    }
    
}
