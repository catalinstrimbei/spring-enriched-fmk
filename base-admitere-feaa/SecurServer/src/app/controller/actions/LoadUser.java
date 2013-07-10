/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppUser;
import app.services.SecurityService;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class LoadUser extends AppAction{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public LoadUser(String name, SecurityService service) {
        super(name);
        setValue("service", service);
        logger.info("Instantiate as <" + name + ">");
    }

    @Override
    public IApplicationEvent actionPerformed(IApplicationEvent event) {
        SecurityService service = getValue("service");
        event.setValue("user", service.getUser((AppUser)event.getValue("user")));
        return event;
    }
}
