/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.support;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author catalin
 */
public class ValidationPropertyChangeEvent extends PropertyChangeEvent {

    private Object validationSource;

    public ValidationPropertyChangeEvent(Object source, String propertyName,
            Object oldValue, Object newValue) {
        super(source, propertyName, oldValue, newValue);
        this.validationSource = source;
    }

    @Override
    public Object getSource() {
        return this.validationSource;
    }
}