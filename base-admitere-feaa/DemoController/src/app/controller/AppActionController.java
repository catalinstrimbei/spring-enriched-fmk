/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.patterns.IApplicationAction;
import app.controller.patterns.IApplicationController;
import app.controller.patterns.IApplicationEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class AppActionController implements IApplicationController, Serializable {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public AppActionController() {
    }

    public AppActionController(IApplicationController controller) {
        this.addActionListener(controller);
    }

    // Action invocation:
    public IApplicationEvent actionPerformed(IApplicationEvent e) {
        if (!this.containsActionFor(e)){
            throw new RuntimeException("Missing action: " + e.getActionName() + " !");
        }
        logger.log(Level.INFO, "<<<<<< To perform action: " + e.getActionName());
        IApplicationAction action = this.getAction(e.getActionName());
        if (action != null)
            return action.actionPerformed(e);
        else{
            return fireActionEvent(e);
        }
    }

    // Action Management
    protected Map<String, IApplicationAction> actions =
            new HashMap<String, IApplicationAction>();

    public void add(IApplicationAction action) {
        actions.put(action.getName(), action);
        logger.info("Action Controller registered: " + action.getName());
    }

    public void remove(IApplicationAction action) {
        actions.remove(action.getName());
    }

    /* - Direct access */
    public <T extends IApplicationAction> T getAction(String actionName) {
        IApplicationAction action = actions.get(actionName);
        return (T) action;
    }

    public <T> T getBusinessService(String businessServiceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addAll(Collection<IApplicationAction> actions) {
        for (IApplicationAction action: actions)
            this.add(action);
    }

    public List<IApplicationAction> getActions() {
        return new ArrayList(actions.values());
    }

    public void setActions(List<IApplicationAction> actions) {
//        this.actions.clear();
        this.addAll(actions);
    }

    private List<IApplicationController> actionListeners = new ArrayList<IApplicationController>();
    public void addActionListener(IApplicationController backupController) {
        actionListeners.add(backupController);
    }

    public void removeActionListener(IApplicationController backupController) {
        actionListeners.remove(backupController);
    }
    private IApplicationEvent fireActionEvent(IApplicationEvent event){
        for (IApplicationController backupController : actionListeners){
            if (backupController.containsActionFor(event))
                return backupController.actionPerformed(event);
        }
            
        return null;
    }

    public boolean contains(String actionName) {
        if (this.actions.keySet().contains(actionName))
            return true;
        for (IApplicationController backupController : actionListeners){
            if (backupController.contains(actionName))
                return true;
        }
        return false;
    }

    public boolean containsActionFor(IApplicationEvent e) {
        return this.contains(e.getActionName());
    }

    public void setActionListeners(List<IApplicationController> backupControllers) {
        for (IApplicationController backupController : backupControllers)
            this.addActionListener(backupController);
    }
}
