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
@Table(name = "oc_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcContent.findAll", query = "SELECT o FROM OcContent o"),
    @NamedQuery(name = "OcContent.findByIdContent", query = "SELECT o FROM OcContent o WHERE o.idContent = :idContent"),
    @NamedQuery(name = "OcContent.findByLocale", query = "SELECT o FROM OcContent o WHERE o.locale = :locale"),
    @NamedQuery(name = "OcContent.findByOrder", query = "SELECT o FROM OcContent o WHERE o.order = :order"),
    @NamedQuery(name = "OcContent.findByTitle", query = "SELECT o FROM OcContent o WHERE o.title = :title"),
    @NamedQuery(name = "OcContent.findBySeotitle", query = "SELECT o FROM OcContent o WHERE o.seotitle = :seotitle"),
    @NamedQuery(name = "OcContent.findByFromEmail", query = "SELECT o FROM OcContent o WHERE o.fromEmail = :fromEmail"),
    @NamedQuery(name = "OcContent.findByCreated", query = "SELECT o FROM OcContent o WHERE o.created = :created"),
    @NamedQuery(name = "OcContent.findByType", query = "SELECT o FROM OcContent o WHERE o.type = :type"),
    @NamedQuery(name = "OcContent.findByStatus", query = "SELECT o FROM OcContent o WHERE o.status = :status")})
public class OcContent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_content")
    private Integer idContent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "locale")
    private String locale;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order")
    private int order;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 145)
    @Column(name = "from_email")
    private String fromEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public OcContent() {
    }

    public OcContent(Integer idContent) {
        this.idContent = idContent;
    }

    public OcContent(Integer idContent, String locale, int order, String title, String seotitle, Date created, String type, boolean status) {
        this.idContent = idContent;
        this.locale = locale;
        this.order = order;
        this.title = title;
        this.seotitle = seotitle;
        this.created = created;
        this.type = type;
        this.status = status;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContent != null ? idContent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcContent)) {
            return false;
        }
        OcContent other = (OcContent) object;
        if ((this.idContent == null && other.idContent != null) || (this.idContent != null && !this.idContent.equals(other.idContent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcContent[ idContent=" + idContent + " ]";
    }
    
}
