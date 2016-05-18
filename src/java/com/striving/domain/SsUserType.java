/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "SS_USER_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsUserType.findAll", query = "SELECT s FROM SsUserType s"),
    @NamedQuery(name = "SsUserType.findByUserTypeId", query = "SELECT s FROM SsUserType s WHERE s.userTypeId = :userTypeId"),
    @NamedQuery(name = "SsUserType.findBySsUserTypeDesc", query = "SELECT s FROM SsUserType s WHERE s.ssUserTypeDesc = :ssUserTypeDesc")})
public class SsUserType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_TYPE_ID")
    private Integer userTypeId;
    @Size(max = 45)
    @Column(name = "SS_USER_TYPE_DESC")
    private String ssUserTypeDesc;
    @OneToMany(mappedBy = "ssUtypeId")
    private Collection<SsUser> ssUserCollection;

    public SsUserType() {
    }

    public SsUserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getSsUserTypeDesc() {
        return ssUserTypeDesc;
    }

    public void setSsUserTypeDesc(String ssUserTypeDesc) {
        this.ssUserTypeDesc = ssUserTypeDesc;
    }

    @XmlTransient
    public Collection<SsUser> getSsUserCollection() {
        return ssUserCollection;
    }

    public void setSsUserCollection(Collection<SsUser> ssUserCollection) {
        this.ssUserCollection = ssUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTypeId != null ? userTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsUserType)) {
            return false;
        }
        SsUserType other = (SsUserType) object;
        if ((this.userTypeId == null && other.userTypeId != null) || (this.userTypeId != null && !this.userTypeId.equals(other.userTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.SsUserType[ userTypeId=" + userTypeId + " ]";
    }
    
}
