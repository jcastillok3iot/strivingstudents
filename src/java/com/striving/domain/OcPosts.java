/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "oc_posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcPosts.findAll", query = "SELECT o FROM OcPosts o"),
    @NamedQuery(name = "OcPosts.findByIdPost", query = "SELECT o FROM OcPosts o WHERE o.idPost = :idPost"),
    @NamedQuery(name = "OcPosts.findByIdUser", query = "SELECT o FROM OcPosts o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "OcPosts.findByIdPostParent", query = "SELECT o FROM OcPosts o WHERE o.idPostParent = :idPostParent"),
    @NamedQuery(name = "OcPosts.findByIdForum", query = "SELECT o FROM OcPosts o WHERE o.idForum = :idForum"),
    @NamedQuery(name = "OcPosts.findByTitle", query = "SELECT o FROM OcPosts o WHERE o.title = :title"),
    @NamedQuery(name = "OcPosts.findBySeotitle", query = "SELECT o FROM OcPosts o WHERE o.seotitle = :seotitle"),
    @NamedQuery(name = "OcPosts.findByCreated", query = "SELECT o FROM OcPosts o WHERE o.created = :created"),
    @NamedQuery(name = "OcPosts.findByIpAddress", query = "SELECT o FROM OcPosts o WHERE o.ipAddress = :ipAddress"),
    @NamedQuery(name = "OcPosts.findByStatus", query = "SELECT o FROM OcPosts o WHERE o.status = :status")})
public class OcPosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_post")
    private Integer idPost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_post_parent")
    private Integer idPostParent;
    @Column(name = "id_forum")
    private Integer idForum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 245)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 245)
    @Column(name = "seotitle")
    private String seotitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "ip_address")
    private BigInteger ipAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public OcPosts() {
    }

    public OcPosts(Integer idPost) {
        this.idPost = idPost;
    }

    public OcPosts(Integer idPost, int idUser, String title, String seotitle, String description, Date created, boolean status) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.title = title;
        this.seotitle = seotitle;
        this.description = description;
        this.created = created;
        this.status = status;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPostParent() {
        return idPostParent;
    }

    public void setIdPostParent(Integer idPostParent) {
        this.idPostParent = idPostParent;
    }

    public Integer getIdForum() {
        return idForum;
    }

    public void setIdForum(Integer idForum) {
        this.idForum = idForum;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigInteger getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(BigInteger ipAddress) {
        this.ipAddress = ipAddress;
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
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcPosts)) {
            return false;
        }
        OcPosts other = (OcPosts) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcPosts[ idPost=" + idPost + " ]";
    }
    
}
