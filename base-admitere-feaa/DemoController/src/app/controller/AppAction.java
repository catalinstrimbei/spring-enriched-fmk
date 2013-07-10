/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller;

import app.controller.patterns.IApplicationAction;
import app.controller.patterns.IApplicationEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author catalin
 */
public abstract class AppAction implements IApplicationAction{
    public static final String NAME = "actionName";

    public AppAction(String name){
        setValue(AppAction.NAME, name);
    }

    public String getName() {
        return (String) getValue(AppAction.NAME);
    }

    

    protected Map<String, Object> attributes = new HashMap<String, Object>();
    
    public <T> T getValue(String name){
        return (T) attributes.get(name);
    }
    public <T> void setValue(String name, T value){
        attributes.put(name, value);
    }
    public String[] getAttributeNames(){
        return this.attributes.keySet().toArray(new String[]{});
    }

    public abstract IApplicationEvent actionPerformed(IApplicationEvent event);
}
