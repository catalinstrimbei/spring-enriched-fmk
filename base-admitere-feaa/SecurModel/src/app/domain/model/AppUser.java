/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author catalin
 */

@Entity
@Table(name = "APP_USERS_VIEW")
public class AppUser implements Serializable{
    public static final String USER_STATUS_OPEN = "OPEN";
    public static final String USER_STATUS_LOCKED = "LOCKED";
    public static final String USER_STATUS_NOT_CREATED = "NOT_CREATED";

    @Id private Integer id;
    private String dbUser;
    private String osUser;
    private String enabled;
    @Column(name="account_status")
    private String status;
    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
    private List<RoleAttachment> attachedRoles = new ArrayList<RoleAttachment>();

    public List<RoleAttachment> getAttachedRoles() {
        return attachedRoles;
    }

    public void setAttachedRolesDTO() {
        this.attachedRoles = new ArrayList(this.attachedRoles);
    }

    public AppUser() {
    }

    public AppUser(Integer id, String dbUser) {
        this.id = id;
        this.dbUser = dbUser;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getOsUser() {
        return osUser;
    }

    public void setOsUser(String osUser) {
        this.osUser = osUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEnabled(){
        return (this.enabled.equals("Y") ? true : false);
    }

    public void setEnabled(boolean enabled){
        if (enabled)
            this.enabled = "Y";
        else
            this.enabled = "N";
    }

    public Boolean isLocked(){
        return (USER_STATUS_LOCKED.equals(this.status) ? true : false);
    }

    @Override
    public String toString() {
        return this.dbUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppUser other = (AppUser) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }


//    @ManyToMany
//    @JoinTable(
//        name = "APP_USER_ROLES",
//        joinColumns  = @JoinColumn(name = "user_id"),
//        inverseJoinColumns=@JoinColumn(name="role_id"))
//    private List<AppRole> roles = new ArrayList<AppRole>();
//    public List<AppRole> getRoles() {
//        return roles;
//    }
}
