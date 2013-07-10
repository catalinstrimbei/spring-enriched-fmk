/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author catalin
 */
@Embeddable
public class RoleAttachmentID implements Serializable {
    @Column(name = "user_id")
    private Integer userID;
    @Column(name = "role_id")
    private Integer roleID;

    public RoleAttachmentID() {
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public RoleAttachmentID(Integer userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleAttachmentID other = (RoleAttachmentID) obj;
        if (this.userID != other.userID && (this.userID == null || !this.userID.equals(other.userID))) {
            return false;
        }
        if (this.roleID != other.roleID && (this.roleID == null || !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.userID != null ? this.userID.hashCode() : 0);
        hash = 37 * hash + (this.roleID != null ? this.roleID.hashCode() : 0);
        return hash;
    }

    
}
