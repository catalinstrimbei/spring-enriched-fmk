/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppUser;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author catalin
 */
public class SortUsersAction extends AppAction {

    public SortUsersAction(String name) {
        super(name);
    }

    @Override
    public IApplicationEvent actionPerformed(IApplicationEvent event) {
        List<AppUser> users = event.getValue("users");
        Collections.sort(users, new UserComparator());
        event.setValue("users", users);
        return event;
    }

}
class UserComparator implements Comparator<AppUser>{

    public int compare(AppUser u1, AppUser u2) {
        return u1.getDbUser().compareTo(u2.getDbUser());
    }

 }