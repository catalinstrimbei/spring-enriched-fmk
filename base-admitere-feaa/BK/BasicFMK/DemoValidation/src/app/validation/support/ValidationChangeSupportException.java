/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.support;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author catalin
 */
public class ValidationChangeSupportException extends PropertyVetoException {

    private PropertyChangeEvent sourceEvent;
    private ValidationChangeSupport sourceSupport;
    private List<PropertyVetoException> multiExceptionList =
            new ArrayList<PropertyVetoException>();

    public ValidationChangeSupportException(ValidationChangeSupport source,
            PropertyChangeEvent evt, String mess) {
        super(mess, evt);
        this.sourceSupport = source;
        this.sourceEvent = evt;
    }

    public ValidationChangeSupportException(
            PropertyChangeEvent evt, List<PropertyVetoException> multiExceptionList, String message) {
        super(message, evt);
        this.multiExceptionList = multiExceptionList;
    }

    public PropertyChangeEvent getSourceEvent() {
        return sourceEvent;
    }

    public ValidationChangeSupport getSourceSupport() {
        return sourceSupport;
    }
}
