/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.controller;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author catalin
 */
public class ValidationEvent extends PropertyChangeEvent {

    public ValidationEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        super(source, propertyName, oldValue, newValue);
    }

    public ValidationEvent(PropertyChangeEvent propertyChangeEvent) {
        super(propertyChangeEvent.getSource(), propertyChangeEvent.getPropertyName(),
                propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public void setValue(String id, Object value) {
        attributes.put(id, value);
    }

    public Object getValue(String id) {
        return attributes.get(id);
    }

    public String[] getAttributeNames() {
        return this.attributes.keySet().toArray(new String[]{});
    }
}
