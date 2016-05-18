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
@Table(name = "oc_forums")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcForums.findAll", query = "SELECT o FROM OcForums o"),
    @NamedQuery(name = "OcForums.findByIdForum", query = "SELECT o FROM OcForums o WHERE o.idForum = :idForum"),
    @NamedQuery(name = "OcForums.findByName", query = "SELECT o FROM OcForums o WHERE o.name = :name"),
    @NamedQuery(name = "OcForums.findByOrder", query = "SELECT o FROM OcForums o WHERE o.order = :order"),
    @NamedQuery(name = "OcForums.findByCreated", query = "SELECT o FROM OcForums o WHERE o.created = :created"),
    @NamedQuery(name = "OcForums.findByIdForumParent", query = "SELECT o FROM OcForums o WHERE o.idForumParent = :idForumParent"),
    @NamedQuery(name = "OcForums.findByParentDeep", query = "SELECT o FROM OcForums o WHERE o.parentDeep = :parentDeep"),
    @NamedQuery(name = "OcForums.findBySeoname", query = "SELECT o FROM OcForums o WHERE o.seoname = :seoname"),
    @NamedQuery(name = "OcForums.findByDescription", query = "SELECT o FROM OcForums o WHERE o.description = :description")})
public class OcForums implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forum")
    private Integer idForum;
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
    @Column(name = "id_forum_parent")
    private int idForumParent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent_deep")
    private int parentDeep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "seoname")
    private String seoname;
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public OcForums() {
    }

    public OcForums(Integer idForum) {
        this.idForum = idForum;
    }

    public OcForums(Integer idForum, String name, int order, Date created, int idForumParent, int parentDeep, String seoname) {
        this.idForum = idForum;
        this.name = name;
        this.order = order;
        this.created = created;
        this.idForumParent = idForumParent;
        this.parentDeep = parentDeep;
        this.seoname = seoname;
    }

    public Integer getIdForum() {
        return idForum;
    }

    public void setIdForum(Integer idForum) {
        this.idForum = idForum;
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

    public int getIdForumParent() {
        return idForumParent;
    }

    public void setIdForumParent(int idForumParent) {
        this.idForumParent = idForumParent;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForum != null ? idForum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcForums)) {
            return false;
        }
        OcForums other = (OcForums) object;
        if ((this.idForum == null && other.idForum != null) || (this.idForum != null && !this.idForum.equals(other.idForum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcForums[ idForum=" + idForum + " ]";
    }
    
}
