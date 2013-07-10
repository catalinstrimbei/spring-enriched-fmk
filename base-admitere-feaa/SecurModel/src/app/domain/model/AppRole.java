/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author catalin
 */
@Entity
@Table(name = "APP_ROLES_VIEW")
public class AppRole implements Serializable{
    public static final String ROLE_STATUS_CREATED = "CREATED";
    public static final String ROLE_STATUS_NOT_CREATED = "NOT_CREATED";

    @Id private Integer id;
    private String roleName;
    private String enabled;
    @Column(name="role_status")
    private String roleStatus;

    public AppRole() {
    }

    public AppRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Boolean isCreated(){
        return (ROLE_STATUS_CREATED.equals(this.roleStatus) ? true : false);
    }

    public Boolean isEnabled(){
        return (this.enabled.equals("Y") ? true : false);
    }

    public void setEnabled(Boolean enabled){
        if (enabled)
            this.enabled = "N";
        else
            this.enabled = "Y";
    }

    @Override
    public String toString() {
        return this.roleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppRole other = (AppRole) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }


}
