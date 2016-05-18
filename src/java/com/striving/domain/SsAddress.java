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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "SS_ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsAddress.findAll", query = "SELECT s FROM SsAddress s"),
    @NamedQuery(name = "SsAddress.findByAddressId", query = "SELECT s FROM SsAddress s WHERE s.addressId = :addressId"),
    @NamedQuery(name = "SsAddress.findByStreet", query = "SELECT s FROM SsAddress s WHERE s.street = :street"),
    @NamedQuery(name = "SsAddress.findByZipCode", query = "SELECT s FROM SsAddress s WHERE s.zipCode = :zipCode"),
    @NamedQuery(name = "SsAddress.findByCity", query = "SELECT s FROM SsAddress s WHERE s.city = :city"),
    @NamedQuery(name = "SsAddress.findByState", query = "SELECT s FROM SsAddress s WHERE s.state = :state"),
    @NamedQuery(name = "SsAddress.findBySsTaskId", query = "SELECT s FROM SsAddress s WHERE s.ssTaskId = :ssTaskId")})
public class SsAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADDRESS_ID")
    private Integer addressId;
    @Size(max = 45)
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIP_CODE")
    private Integer zipCode;
    @Size(max = 45)
    @Column(name = "CITY")
    private String city;
    @Size(max = 45)
    @Column(name = "STATE")
    private String state;
    @Column(name = "SS_TASK_ID")
    private Integer ssTaskId;

    public SsAddress() {
    }

    public SsAddress(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSsTaskId() {
        return ssTaskId;
    }

    public void setSsTaskId(Integer ssTaskId) {
        this.ssTaskId = ssTaskId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsAddress)) {
            return false;
        }
        SsAddress other = (SsAddress) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.SsAddress[ addressId=" + addressId + " ]";
    }
    
}
