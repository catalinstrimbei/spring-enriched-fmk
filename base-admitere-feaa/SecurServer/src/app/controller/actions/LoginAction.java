/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.domain.model.AppUser;
import app.services.ApplicationService;
import app.services.SecurityService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class LoginAction extends AppAction{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public LoginAction(String name) {
        super(name);
        logger.info("Instantiate as <" + name + ">");
    }

    public LoginAction(String name, Object service) {
        this(name);
        this.setValue("service", service);
    }

    public IApplicationEvent actionPerformed(IApplicationEvent appEvent) {
        // add logic here, example:
        SecurityService service = getValue("service");
        if (service != null){
            try {
                logger.info("u: " + appEvent.getValue("user"));
                logger.info("p: " + appEvent.getValue("password"));

                appEvent.setValue("login", service.login((AppUser) appEvent.getValue("user"),
                        new String((char[])appEvent.getValue("password"))));
            } catch (Exception ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }

        return appEvent;
    }
}
