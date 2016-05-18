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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SS_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsUser.findAll", query = "SELECT s FROM SsUser s"),
    @NamedQuery(name = "SsUser.findByUserId", query = "SELECT s FROM SsUser s WHERE s.userId = :userId"),
    @NamedQuery(name = "SsUser.findByUserName", query = "SELECT s FROM SsUser s WHERE s.userName = :userName"),
    @NamedQuery(name = "SsUser.findByLastName", query = "SELECT s FROM SsUser s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "SsUser.findByFirstName", query = "SELECT s FROM SsUser s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "SsUser.findByPswd", query = "SELECT s FROM SsUser s WHERE s.pswd = :pswd")})
public class SsUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 65)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 65)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 65)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 15)
    @Column(name = "PSWD")
    private String pswd;
    @JoinColumn(name = "SS_UTYPE_ID", referencedColumnName = "USER_TYPE_ID")
    @ManyToOne
    private SsUserType ssUtypeId;

    public SsUser() {
    }

    public SsUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public SsUserType getSsUtypeId() {
        return ssUtypeId;
    }

    public void setSsUtypeId(SsUserType ssUtypeId) {
        this.ssUtypeId = ssUtypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsUser)) {
            return false;
        }
        SsUser other = (SsUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.SsUser[ userId=" + userId + " ]";
    }
    
}
