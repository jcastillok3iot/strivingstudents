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
import javax.persistence.Lob;
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
@Table(name = "oc_locations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcLocations.findAll", query = "SELECT o FROM OcLocations o"),
    @NamedQuery(name = "OcLocations.findByIdLocation", query = "SELECT o FROM OcLocations o WHERE o.idLocation = :idLocation"),
    @NamedQuery(name = "OcLocations.findByName", query = "SELECT o FROM OcLocations o WHERE o.name = :name"),
    @NamedQuery(name = "OcLocations.findByOrder", query = "SELECT o FROM OcLocations o WHERE o.order = :order"),
    @NamedQuery(name = "OcLocations.findByIdLocationParent", query = "SELECT o FROM OcLocations o WHERE o.idLocationParent = :idLocationParent"),
    @NamedQuery(name = "OcLocations.findByParentDeep", query = "SELECT o FROM OcLocations o WHERE o.parentDeep = :parentDeep"),
    @NamedQuery(name = "OcLocations.findBySeoname", query = "SELECT o FROM OcLocations o WHERE o.seoname = :seoname"),
    @NamedQuery(name = "OcLocations.findByLastModified", query = "SELECT o FROM OcLocations o WHERE o.lastModified = :lastModified"),
    @NamedQuery(name = "OcLocations.findByHasImage", query = "SELECT o FROM OcLocations o WHERE o.hasImage = :hasImage"),
    @NamedQuery(name = "OcLocations.findByLatitude", query = "SELECT o FROM OcLocations o WHERE o.latitude = :latitude"),
    @NamedQuery(name = "OcLocations.findByLongitude", query = "SELECT o FROM OcLocations o WHERE o.longitude = :longitude")})
public class OcLocations implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "website")
    private String website;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "logo")
    private String logo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_location")
    private Integer idLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order")
    private int order;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_location_parent")
    private int idLocationParent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent_deep")
    private int parentDeep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "seoname")
    private String seoname;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_image")
    private boolean hasImage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;

    public OcLocations() {
    }

    public OcLocations(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public OcLocations(Integer idLocation, String name, int order, int idLocationParent, int parentDeep, String seoname, boolean hasImage) {
        this.idLocation = idLocation;
        this.name = name;
        this.order = order;
        this.idLocationParent = idLocationParent;
        this.parentDeep = parentDeep;
        this.seoname = seoname;
        this.hasImage = hasImage;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIdLocationParent() {
        return idLocationParent;
    }

    public void setIdLocationParent(int idLocationParent) {
        this.idLocationParent = idLocationParent;
    }

    public int getParentDeep() {
        return parentDeep;
    }

    public void setParentDeep(int parentDeep) {
        this.parentDeep = parentDeep;
    }

    public String getSeoname() {
        return seoname;
    }

    public void setSeoname(String seoname) {
        this.seoname = seoname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocation != null ? idLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcLocations)) {
            return false;
        }
        OcLocations other = (OcLocations) object;
        if ((this.idLocation == null && other.idLocation != null) || (this.idLocation != null && !this.idLocation.equals(other.idLocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcLocations[ idLocation=" + idLocation + " ]";
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
