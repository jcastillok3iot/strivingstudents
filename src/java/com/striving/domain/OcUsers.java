/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcUsers.findAll", query = "SELECT o FROM OcUsers o"),
    @NamedQuery(name = "OcUsers.findByIdUser", query = "SELECT o FROM OcUsers o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcUsers.findByName", query = "SELECT o FROM OcUsers o WHERE o.name = :name"),
    @NamedQuery(name = "OcUsers.findBySeoname", query = "SELECT o FROM OcUsers o WHERE o.seoname = :seoname"),
    @NamedQuery(name = "OcUsers.findByEmail", query = "SELECT o FROM OcUsers o WHERE o.email = :email"),
    @NamedQuery(name = "OcUsers.findByPassword", query = "SELECT o FROM OcUsers o WHERE o.password = :password"),
    @NamedQuery(name = "OcUsers.findByStatus", query = "SELECT o FROM OcUsers o WHERE o.status = :status"),
    @NamedQuery(name = "OcUsers.findByIdRole", query = "SELECT o FROM OcUsers o WHERE o.idRole = :idRole"),
    @NamedQuery(name = "OcUsers.findByIdLocation", query = "SELECT o FROM OcUsers o WHERE o.idLocation = :idLocation"),
    @NamedQuery(name = "OcUsers.findByCreated", query = "SELECT o FROM OcUsers o WHERE o.created = :created"),
    @NamedQuery(name = "OcUsers.findByLastModified", query = "SELECT o FROM OcUsers o WHERE o.lastModified = :lastModified"),
    @NamedQuery(name = "OcUsers.findByLogins", query = "SELECT o FROM OcUsers o WHERE o.logins = :logins"),
    @NamedQuery(name = "OcUsers.findByLastLogin", query = "SELECT o FROM OcUsers o WHERE o.lastLogin = :lastLogin"),
    @NamedQuery(name = "OcUsers.findByLastIp", query = "SELECT o FROM OcUsers o WHERE o.lastIp = :lastIp"),
    @NamedQuery(name = "OcUsers.findByUserAgent", query = "SELECT o FROM OcUsers o WHERE o.userAgent = :userAgent"),
    @NamedQuery(name = "OcUsers.findByToken", query = "SELECT o FROM OcUsers o WHERE o.token = :token"),
    @NamedQuery(name = "OcUsers.findByTokenCreated", query = "SELECT o FROM OcUsers o WHERE o.tokenCreated = :tokenCreated"),
    @NamedQuery(name = "OcUsers.findByTokenExpires", query = "SELECT o FROM OcUsers o WHERE o.tokenExpires = :tokenExpires"),
    @NamedQuery(name = "OcUsers.findByHybridauthProviderName", query = "SELECT o FROM OcUsers o WHERE o.hybridauthProviderName = :hybridauthProviderName"),
    @NamedQuery(name = "OcUsers.findByHybridauthProviderUid", query = "SELECT o FROM OcUsers o WHERE o.hybridauthProviderUid = :hybridauthProviderUid"),
    @NamedQuery(name = "OcUsers.findBySubscriber", query = "SELECT o FROM OcUsers o WHERE o.subscriber = :subscriber"),
    @NamedQuery(name = "OcUsers.findByRate", query = "SELECT o FROM OcUsers o WHERE o.rate = :rate"),
    @NamedQuery(name = "OcUsers.findByHasImage", query = "SELECT o FROM OcUsers o WHERE o.hasImage = :hasImage"),
    @NamedQuery(name = "OcUsers.findByFailedAttempts", query = "SELECT o FROM OcUsers o WHERE o.failedAttempts = :failedAttempts"),
    @NamedQuery(name = "OcUsers.findByLastFailed", query = "SELECT o FROM OcUsers o WHERE o.lastFailed = :lastFailed")})
public class OcUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Size(max = 145)
    @Column(name = "name")
    private String name;
    @Size(max = 145)
    @Column(name = "seoname")
    private String seoname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Column(name = "id_role")
    private Integer idRole;
    @Column(name = "id_location")
    private Integer idLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "logins")
    private int logins;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "last_ip")
    private BigInteger lastIp;
    @Size(max = 40)
    @Column(name = "user_agent")
    private String userAgent;
    @Size(max = 40)
    @Column(name = "token")
    private String token;
    @Column(name = "token_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenCreated;
    @Column(name = "token_expires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpires;
    @Size(max = 40)
    @Column(name = "hybridauth_provider_name")
    private String hybridauthProviderName;
    @Size(max = 245)
    @Column(name = "hybridauth_provider_uid")
    private String hybridauthProviderUid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subscriber")
    private boolean subscriber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate")
    private Float rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_image")
    private boolean hasImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "failed_attempts")
    private int failedAttempts;
    @Column(name = "last_failed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFailed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<OcAds> ocAdsCollection;

    public OcUsers() {
    }

    public OcUsers(Integer idUser) {
        this.idUser = idUser;
    }

    public OcUsers(Integer idUser, String email, String password, int status, Date created, int logins, boolean subscriber, boolean hasImage, int failedAttempts) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.status = status;
        this.created = created;
        this.logins = logins;
        this.subscriber = subscriber;
        this.hasImage = hasImage;
        this.failedAttempts = failedAttempts;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeoname() {
        return seoname;
    }

    public void setSeoname(String seoname) {
        this.seoname = seoname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins = logins;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigInteger getLastIp() {
        return lastIp;
    }

    public void setLastIp(BigInteger lastIp) {
        this.lastIp = lastIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenCreated() {
        return tokenCreated;
    }

    public void setTokenCreated(Date tokenCreated) {
        this.tokenCreated = tokenCreated;
    }

    public Date getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(Date tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    public String getHybridauthProviderName() {
        return hybridauthProviderName;
    }

    public void setHybridauthProviderName(String hybridauthProviderName) {
        this.hybridauthProviderName = hybridauthProviderName;
    }

    public String getHybridauthProviderUid() {
        return hybridauthProviderUid;
    }

    public void setHybridauthProviderUid(String hybridauthProviderUid) {
        this.hybridauthProviderUid = hybridauthProviderUid;
    }

    public boolean getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Date getLastFailed() {
        return lastFailed;
    }

    public void setLastFailed(Date lastFailed) {
        this.lastFailed = lastFailed;
    }

    @XmlTransient
    public Collection<OcAds> getOcAdsCollection() {
        return ocAdsCollection;
    }

    public void setOcAdsCollection(Collection<OcAds> ocAdsCollection) {
        this.ocAdsCollection = ocAdsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcUsers)) {
            return false;
        }
        OcUsers other = (OcUsers) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcUsers[ idUser=" + idUser + " ]";
    }
    
}
