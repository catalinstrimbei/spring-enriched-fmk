/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.services;

import app.domain.model.AppRole;
import app.domain.model.AppUser;
import java.util.List;
import javax.management.relation.Role;

/**
 *
 * @author catalin
 */
public interface SecurityService {
    List<AppUser> getUsers();
    AppUser getUser(AppUser user);
    List<Role> getEnabledRoleOf(AppUser user);
    List<AppRole> getRoles();
    List<AppUser> getRoleUsers(AppRole role);

    //pac_gest_secur.generate_user_standard(':USER');
    AppUser addUserInApp(AppUser user) throws Exception;
    //pac_gest_secur.force_revalidate_user(':USER');
    AppUser forceRevalidateUser(AppUser user) throws Exception;
    //pac_gest_secur.set_user_role(':USER', ':ROLE', :enabled);
    AppUser setUserRole(AppUser user, AppRole role, boolean enabled) throws Exception;
    //pac_gest_secur.force_revalidate_user_for(':USER', ':ROLE');
    AppUser forceRevalidateUserFor(AppUser user, AppRole role) throws Exception;
    //pac_gest_secur.set_user_role(':USER', ':ENABLED', :STATUS);
    AppUser setUserState(AppUser user) throws Exception;
    //pac_gest_secur.force_revalidate_user(':USER');
    AppUser forceRevalidateUserPassword(AppUser user) throws Exception;

    List<AppUser> batchSetUsersRole(List<AppUser> users, AppRole role, boolean enabled) throws Exception;

    Boolean login(AppUser user, String password) throws Exception;
}
