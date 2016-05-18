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
@Table(name = "SS_TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsTask.findAll", query = "SELECT s FROM SsTask s"),
    @NamedQuery(name = "SsTask.findByTaskId", query = "SELECT s FROM SsTask s WHERE s.taskId = :taskId"),
    @NamedQuery(name = "SsTask.findByTaskDesc", query = "SELECT s FROM SsTask s WHERE s.taskDesc = :taskDesc"),
    @NamedQuery(name = "SsTask.findByNumPeople", query = "SELECT s FROM SsTask s WHERE s.numPeople = :numPeople"),
    @NamedQuery(name = "SsTask.findByPay", query = "SELECT s FROM SsTask s WHERE s.pay = :pay"),
    @NamedQuery(name = "SsTask.findByTargetHours", query = "SELECT s FROM SsTask s WHERE s.targetHours = :targetHours"),
    @NamedQuery(name = "SsTask.findByStatus", query = "SELECT s FROM SsTask s WHERE s.status = :status")})
public class SsTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Size(max = 85)
    @Column(name = "TASK_DESC")
    private String taskDesc;
    @Column(name = "NUM_PEOPLE")
    private Integer numPeople;
    @Column(name = "PAY")
    private Long pay;
    @Column(name = "TARGET_HOURS")
    private Integer targetHours;
    @Column(name = "STATUS")
    private Short status;

    public SsTask() {
    }

    public SsTask(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    public Long getPay() {
        return pay;
    }

    public void setPay(Long pay) {
        this.pay = pay;
    }

    public Integer getTargetHours() {
        return targetHours;
    }

    public void setTargetHours(Integer targetHours) {
        this.targetHours = targetHours;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsTask)) {
            return false;
        }
        SsTask other = (SsTask) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.SsTask[ taskId=" + taskId + " ]";
    }
    
}
