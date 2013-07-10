/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author catalin
 */
@Entity
@Table(name = "APP_USER_ROLES")
public class RoleAttachment implements Serializable{
    @EmbeddedId private RoleAttachmentID id;
    @ManyToOne @JoinColumn(name = "user_id",insertable=false, updatable=false)
    private AppUser user;
    @ManyToOne @JoinColumn(name = "role_id",insertable=false, updatable=false)
    private AppRole role;
    private String enabled;

    public RoleAttachment() {
    }

    public RoleAttachment(AppUser user, AppRole role, String enabled) {
        this.user = user;
        this.role = role;
        this.enabled = enabled;
        this.setId(new RoleAttachmentID(user.getId(), role.getId()));
    }

    public RoleAttachment(RoleAttachmentID id) {
        this.id = id;
    }

    public RoleAttachmentID getId() {
        return id;
    }

    public void setId(RoleAttachmentID id) {
        this.id = id;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleAttachment other = (RoleAttachment) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }


}
