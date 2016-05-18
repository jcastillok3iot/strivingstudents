/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "oc_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcCategories.findAll", query = "SELECT o FROM OcCategories o"),
    @NamedQuery(name = "OcCategories.findByIdCategory", query = "SELECT o FROM OcCategories o WHERE o.idCategory = :idCategory ORDER BY o.order"),
    @NamedQuery(name = "OcCategories.findByName", query = "SELECT o FROM OcCategories o WHERE o.name = :name"),
    @NamedQuery(name = "OcCategories.findByOrder", query = "SELECT o FROM OcCategories o WHERE o.order = :order"),
    @NamedQuery(name = "OcCategories.findByCreated", query = "SELECT o FROM OcCategories o WHERE o.created = :created"),
    @NamedQuery(name = "OcCategories.findByIdCategoryParent", query = "SELECT o FROM OcCategories o WHERE o.idCategoryParent = :idCategoryParent ORDER BY o.order"),
    @NamedQuery(name = "OcCategories.findByParentDeep", query = "SELECT o FROM OcCategories o WHERE o.parentDeep = :parentDeep"),
    @NamedQuery(name = "OcCategories.findBySeoname", query = "SELECT o FROM OcCategories o WHERE o.seoname = :seoname"),
    @NamedQuery(name = "OcCategories.findByPrice", query = "SELECT o FROM OcCategories o WHERE o.price = :price"),
    @NamedQuery(name = "OcCategories.findByLastModified", query = "SELECT o FROM OcCategories o WHERE o.lastModified = :lastModified"),
    @NamedQuery(name = "OcCategories.findByHasImage", query = "SELECT o FROM OcCategories o WHERE o.hasImage = :hasImage")})
public class OcCategories implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_category")
    private Integer idCategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order")
    private int order;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_category_parent")
    private int idCategoryParent;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_image")
    private boolean hasImage;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "idCategory")
    private Collection<OcAds> ocAdsCollection;

    public OcCategories() {
    }

    public OcCategories(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public OcCategories(Integer idCategory, String name, int order, Date created, int idCategoryParent, int parentDeep, String seoname, BigDecimal price, boolean hasImage) {
        this.idCategory = idCategory;
        this.name = name;
        this.order = order;
        this.created = created;
        this.idCategoryParent = idCategoryParent;
        this.parentDeep = parentDeep;
        this.seoname = seoname;
        this.price = price;
        this.hasImage = hasImage;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getIdCategoryParent() {
        return idCategoryParent;
    }

    public void setIdCategoryParent(int idCategoryParent) {
        this.idCategoryParent = idCategoryParent;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        hash += (idCategory != null ? idCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcCategories)) {
            return false;
        }
        OcCategories other = (OcCategories) object;
        if ((this.idCategory == null && other.idCategory != null) || (this.idCategory != null && !this.idCategory.equals(other.idCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcCategories[ idCategory=" + idCategory + " ]";
    }
    
}
