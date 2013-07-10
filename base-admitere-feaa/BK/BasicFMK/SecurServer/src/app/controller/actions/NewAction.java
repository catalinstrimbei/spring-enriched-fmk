/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.actions;

import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import app.services.ApplicationService;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class NewAction extends AppAction{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    public NewAction(String name) {
        super(name);
        logger.info("Instantiate as <" + name + ">");
    }

    public NewAction(String name, Object service) {
        this(name);
        this.setValue("service", service);
    }

    public IApplicationEvent actionPerformed(IApplicationEvent appEvent) {
        // add logic here, example:
        if (getValue("service") != null){
            ApplicationService service = getValue("service");
            service.serviceOpperation();
        }

        return appEvent;
    }
}
