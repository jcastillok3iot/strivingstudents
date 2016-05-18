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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcastillo
 */
@Entity
@Table(name = "oc_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OcConfig.findAll", query = "SELECT o FROM OcConfig o"),
    @NamedQuery(name = "OcConfig.findByGroupName", query = "SELECT o FROM OcConfig o WHERE o.groupName = :groupName"),
    @NamedQuery(name = "OcConfig.findByConfigKey", query = "SELECT o FROM OcConfig o WHERE o.configKey = :configKey")})
public class OcConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "group_name")
    private String groupName;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "config_key")
    private String configKey;
    @Lob
    @Size(max = 65535)
    @Column(name = "config_value")
    private String configValue;

    public OcConfig() {
    }

    public OcConfig(String configKey) {
        this.configKey = configKey;
    }

    public OcConfig(String configKey, String groupName) {
        this.configKey = configKey;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configKey != null ? configKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcConfig)) {
            return false;
        }
        OcConfig other = (OcConfig) object;
        if ((this.configKey == null && other.configKey != null) || (this.configKey != null && !this.configKey.equals(other.configKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.striving.domain.OcConfig[ configKey=" + configKey + " ]";
    }
    
}
