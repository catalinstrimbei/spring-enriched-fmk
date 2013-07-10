/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client.controller;

import app.controller.AppActionEvent;
import app.controller.patterns.IApplicationAction;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author catalin
 */
public class RichAction extends AbstractAction implements Action{
    protected IApplicationAction appAction;


    public RichAction(IApplicationAction action){
        this.appAction = action;
        this.putValue(Action.NAME, action.getName());
    }

    @Override
    public Object getValue(String key) {
        return appAction.getValue(key);
    }

    @Override
    public void putValue(String key, Object value) {
        this.appAction.setValue(key, value);
    }

    public void actionPerformed(ActionEvent e) {
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        if (RichClientApplication.INSTANCE.getMainForm() != null)
            RichClientApplication.INSTANCE.getMainForm().setCursor(hourglassCursor);
        AppActionEvent appEvent = new AppActionEvent(this.appAction.getName());
        appEvent = (AppActionEvent) appAction.actionPerformed(appEvent);
        if (RichClientApplication.INSTANCE.getMainForm() != null)
            RichClientApplication.INSTANCE.getMainForm().setCursor(normalCursor);
    }

}
