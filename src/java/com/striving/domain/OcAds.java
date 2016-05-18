/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "oc_ads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcAds.findAll", query = "SELECT o FROM OcAds o"),
    @NamedQuery(name = "OcAds.findByIdAd", query = "SELECT o FROM OcAds o WHERE o.idAd = :idAd"),
    @NamedQuery(name = "OcAds.findByIdLocation", query = "SELECT o FROM OcAds o WHERE o.idLocation = :idLocation"),
    @NamedQuery(name = "OcAds.findByTitle", query = "SELECT o FROM OcAds o WHERE o.title = :title"),
    @NamedQuery(name = "OcAds.findBySeotitle", query = "SELECT o FROM OcAds o WHERE o.seotitle = :seotitle"),
    @NamedQuery(name = "OcAds.findByAddress", query = "SELECT o FROM OcAds o WHERE o.address = :address"),
    @NamedQuery(name = "OcAds.findByLatitude", query = "SELECT o FROM OcAds o WHERE o.latitude = :latitude"),
    @NamedQuery(name = "OcAds.findByLongitude", query = "SELECT o FROM OcAds o WHERE o.longitude = :longitude"),
    @NamedQuery(name = "OcAds.findByPrice", query = "SELECT o FROM OcAds o WHERE o.price = :price"),
    @NamedQuery(name = "OcAds.findByPhone", query = "SELECT o FROM OcAds o WHERE o.phone = :phone"),
    @NamedQuery(name = "OcAds.findByWebsite", query = "SELECT o FROM OcAds o WHERE o.website = :website"),
    @NamedQuery(name = "OcAds.findByIpAddress", query = "SELECT o FROM OcAds o WHERE o.ipAddress = :ipAddress"),
    @NamedQuery(name = "OcAds.findByCreated", query = "SELECT o FROM OcAds o WHERE o.created = :created"),
    @NamedQuery(name = "OcAds.findByPublished", query = "SELECT o FROM OcAds o WHERE o.published = :published"),
    @NamedQuery(name = "OcAds.findByFeatured", query = "SELECT o FROM OcAds o WHERE o.featured = :featured"),
    @NamedQuery(name = "OcAds.findByLastModified", query = "SELECT o FROM OcAds o WHERE o.lastModified = :lastModified"),
    @NamedQuery(name = "OcAds.findByStatus", query = "SELECT o FROM OcAds o WHERE o.status = :status"),
    @NamedQuery(name = "OcAds.findByHasImages", query = "SELECT o FROM OcAds o WHERE o.hasImages = :hasImages"),
    @NamedQuery(name = "OcAds.findByStock", query = "SELECT o FROM OcAds o WHERE o.stock = :stock"),
    @NamedQuery(name = "OcAds.findByRate", query = "SELECT o FROM OcAds o WHERE o.rate = :rate"),
    @NamedQuery(name = "OcAds.findByCfCollege", query = "SELECT o FROM OcAds o WHERE o.cfCollege = :cfCollege")})
public class OcAds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ad")
    private Integer idAd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_location")
    private int idLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "seotitle")
    private String seotitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 145)
    @Column(name = "address")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "phone")
    private String phone;
    @Size(max = 200)
    @Column(name = "website")
    private String website;
    @Column(name = "ip_address")
    private BigInteger ipAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "published")
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;
    @Column(name = "featured")
    @Temporal(TemporalType.TIMESTAMP)
    private Date featured;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_images")
    private boolean hasImages;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "rate")
    private Float rate;
    @Size(max = 256)
    @Column(name = "cf_college")
    private String cfCollege;
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne(optional = false)
    private OcCategories idCategory;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private OcUsers idUser;

    public OcAds() {
    }

    public OcAds(Integer idAd) {
        this.idAd = idAd;
    }

    public OcAds(Integer idAd, int idLocation, String title, String seotitle, String description, BigDecimal price, Date created, boolean status, boolean hasImages) {
        this.idAd = idAd;
        this.idLocation = idLocation;
        this.title = title;
        this.seotitle = seotitle;
        this.description = description;
        this.price = price;
        this.created = created;
        this.status = status;
        this.hasImages = hasImages;
    }

    public Integer getIdAd() {
        return idAd;
    }

    public void setIdAd(Integer idAd) {
        this.idAd = idAd;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeotitle() {
        return seotitle;
    }

    public void setSeotitle(String seotitle) {
        this.seotitle = seotitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BigInteger getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(BigInteger ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getFeatured() {
        return featured;
    }

    public void setFeatured(Date featured) {
        this.featured = featured;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getHasImages() {
        return hasImages;
    }

    public void setHasImages(boolean hasImages) {
        this.hasImages = hasImages;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getCfCollege() {
        return cfCollege;
    }

    public void setCfCollege(String cfCollege) {
        this.cfCollege = cfCollege;
    }

    public OcCategories getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(OcCategories idCategory) {
        this.idCategory = idCategory;
    }

    public OcUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(OcUsers idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAd != null ? idAd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcAds)) {
            return false;
        }
        OcAds other = (OcAds) object;
        if ((this.idAd == null && other.idAd != null) || (this.idAd != null && !this.idAd.equals(other.idAd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcAds[ idAd=" + idAd + " ]";
    }
    
}
