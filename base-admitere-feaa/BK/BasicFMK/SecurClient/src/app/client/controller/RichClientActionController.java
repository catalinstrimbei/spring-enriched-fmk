/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.client.controller;

import app.controller.AppActionController;
import app.controller.actions.OpenFormAction;
import app.controller.patterns.IApplicationController;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class RichClientActionController extends AppActionController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public RichClientActionController() {
    }

    public RichClientActionController(IApplicationController controller) {
        super(controller);
    }

    public RichAction getRichAction(String actionName) {
        return new RichAction(getAction(actionName));
    }

    /* - Navigational Actions */
    public void addForm(final String mappingName, Class formClass) {
        OpenFormAction action;
        try {
            action = new OpenFormAction(mappingName, formClass);
            this.add(action);
        } catch (InstantiationException ex) {
            Logger.getLogger(RichClientActionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RichClientActionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setForms(Map<String, String> forms) {
        for (Map.Entry<String, String> entryForm : forms.entrySet()){
            try {
                OpenFormAction action = new OpenFormAction(entryForm.getKey(), entryForm.getValue());
                this.add(action);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(RichClientActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RichClientActionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RichClientActionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
