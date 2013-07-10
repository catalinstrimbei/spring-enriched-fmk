/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.patterns;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author catalin
 */
public interface IApplicationController{
    IApplicationEvent actionPerformed(IApplicationEvent e);

    <T extends IApplicationAction> T getAction(String actionName);
    void add(IApplicationAction action);
    void remove(IApplicationAction action);
    void addAll(Collection<IApplicationAction> actions);
    List<IApplicationAction> getActions();
    void setActions(List<IApplicationAction> actions);
    boolean contains(String actionName);
    boolean containsActionFor(IApplicationEvent e);

    <T> T getBusinessService(String businessServiceId);

    void addActionListener(IApplicationController backupController);
    void removeActionListener(IApplicationController backupController);
    void setActionListeners(List<IApplicationController> backupControllers);
}
