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
@Table(name = "oc_crontab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcCrontab.findAll", query = "SELECT o FROM OcCrontab o"),
    @NamedQuery(name = "OcCrontab.findByIdCrontab", query = "SELECT o FROM OcCrontab o WHERE o.idCrontab = :idCrontab"),
    @NamedQuery(name = "OcCrontab.findByName", query = "SELECT o FROM OcCrontab o WHERE o.name = :name"),
    @NamedQuery(name = "OcCrontab.findByPeriod", query = "SELECT o FROM OcCrontab o WHERE o.period = :period"),
    @NamedQuery(name = "OcCrontab.findByCallback", query = "SELECT o FROM OcCrontab o WHERE o.callback = :callback"),
    @NamedQuery(name = "OcCrontab.findByParams", query = "SELECT o FROM OcCrontab o WHERE o.params = :params"),
    @NamedQuery(name = "OcCrontab.findByDescription", query = "SELECT o FROM OcCrontab o WHERE o.description = :description"),
    @NamedQuery(name = "OcCrontab.findByDateCreated", query = "SELECT o FROM OcCrontab o WHERE o.dateCreated = :dateCreated"),
    @NamedQuery(name = "OcCrontab.findByDateStarted", query = "SELECT o FROM OcCrontab o WHERE o.dateStarted = :dateStarted"),
    @NamedQuery(name = "OcCrontab.findByDateFinished", query = "SELECT o FROM OcCrontab o WHERE o.dateFinished = :dateFinished"),
    @NamedQuery(name = "OcCrontab.findByDateNext", query = "SELECT o FROM OcCrontab o WHERE o.dateNext = :dateNext"),
    @NamedQuery(name = "OcCrontab.findByTimesExecuted", query = "SELECT o FROM OcCrontab o WHERE o.timesExecuted = :timesExecuted"),
    @NamedQuery(name = "OcCrontab.findByOutput", query = "SELECT o FROM OcCrontab o WHERE o.output = :output"),
    @NamedQuery(name = "OcCrontab.findByRunning", query = "SELECT o FROM OcCrontab o WHERE o.running = :running"),
    @NamedQuery(name = "OcCrontab.findByActive", query = "SELECT o FROM OcCrontab o WHERE o.active = :active")})
public class OcCrontab implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_crontab")
    private Integer idCrontab;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "period")
    private String period;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "callback")
    private String callback;
    @Size(max = 255)
    @Column(name = "params")
    private String params;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStarted;
    @Column(name = "date_finished")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinished;
    @Column(name = "date_next")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNext;
    @Column(name = "times_executed")
    private BigInteger timesExecuted;
    @Size(max = 50)
    @Column(name = "output")
    private String output;
    @Basic(optional = false)
    @NotNull
    @Column(name = "running")
    private boolean running;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    public OcCrontab() {
    }

    public OcCrontab(Integer idCrontab) {
        this.idCrontab = idCrontab;
    }

    public OcCrontab(Integer idCrontab, String name, String period, String callback, Date dateCreated, boolean running, boolean active) {
        this.idCrontab = idCrontab;
        this.name = name;
        this.period = period;
        this.callback = callback;
        this.dateCreated = dateCreated;
        this.running = running;
        this.active = active;
    }

    public Integer getIdCrontab() {
        return idCrontab;
    }

    public void setIdCrontab(Integer idCrontab) {
        this.idCrontab = idCrontab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public Date getDateNext() {
        return dateNext;
    }

    public void setDateNext(Date dateNext) {
        this.dateNext = dateNext;
    }

    public BigInteger getTimesExecuted() {
        return timesExecuted;
    }

    public void setTimesExecuted(BigInteger timesExecuted) {
        this.timesExecuted = timesExecuted;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCrontab != null ? idCrontab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcCrontab)) {
            return false;
        }
        OcCrontab other = (OcCrontab) object;
        if ((this.idCrontab == null && other.idCrontab != null) || (this.idCrontab != null && !this.idCrontab.equals(other.idCrontab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcCrontab[ idCrontab=" + idCrontab + " ]";
    }
    
}
