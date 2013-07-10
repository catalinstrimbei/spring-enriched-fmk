/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller;

import app.controller.patterns.IApplicationEvent;
import java.io.Serializable;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author catalin
 */
public class AppActionEvent extends EventObject implements IApplicationEvent, Serializable{
    protected Object eventSource;
    public AppActionEvent(Object source){
        super(source);
        this.eventSource = source;
    }
    @Override
    public Object getSource(){
        if (super.getSource() == null)
            return eventSource;
        return super.getSource();
    }

    // repository pentru atribute extinse
    protected Map<String, Object> attributes = new HashMap<String, Object>();
    // action name to be bounded/processed
    protected String actionName;

    public Object getValue(String name){
        return attributes.get(name);
    }
    public void setValue(String name, Object value){
        attributes.put(name, value);
    }
    public String[] getAttributeNames(){
        return this.attributes.keySet().toArray(new String[]{});
    }

    public AppActionEvent(Object source, String actionName) {
        super(source);
        this.actionName = actionName;
        this.eventSource = source;
    }

    public String getActionName() {
        return actionName;
    }
}
