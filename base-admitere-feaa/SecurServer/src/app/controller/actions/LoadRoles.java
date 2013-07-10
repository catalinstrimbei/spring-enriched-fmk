/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.services.SecurityService;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class LoadRoles extends AppAction{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public LoadRoles(String name, SecurityService service) {
        super(name);
        setValue("service", service);
        logger.info("Instantiate as <" + name + ">");
    }

    @Override
    public IApplicationEvent actionPerformed(IApplicationEvent event) {
        SecurityService service = getValue("service");
        event.setValue("roles", service.getRoles());
        return event;
    }
}
