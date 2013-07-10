/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppRole;
import app.domain.model.AppUser;
import app.services.SecurityService;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class ValidateUserRole extends AppAction{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ValidateUserRole(String name, SecurityService service) {
        super(name);
        setValue("service", service);
        logger.info("Instantiate as <" + name + ">");
    }

    @Override
    public IApplicationEvent actionPerformed(IApplicationEvent event) {
        SecurityService service = getValue("service");
        try{
            event.setValue("user", service.forceRevalidateUserFor(
                (AppUser)event.getValue("user"),
                (AppRole)event.getValue("role")));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
        return event;
    }
}
