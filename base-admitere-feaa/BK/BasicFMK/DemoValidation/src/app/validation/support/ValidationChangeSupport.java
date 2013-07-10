/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.support;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 *
 * @author catalin
 */
public class ValidationChangeSupport extends VetoableChangeSupport {

    /* Static auto-context */
    public static VetoableChangeListener DEFAULT_DELEGATE_LISTENER;
    /* End static auto-context */

    private Object validationSource;
    private transient VetoableChangeListener delegateListener;

    public ValidationChangeSupport(Object sourceBean) {
        super(sourceBean);
        this.validationSource = sourceBean;

        if (this.delegateListener == null && DEFAULT_DELEGATE_LISTENER != null)
            this.delegateListener = DEFAULT_DELEGATE_LISTENER;
    }

    @Override
    public void fireVetoableChange(String propertyName,
            Object oldValue, Object newValue) throws PropertyVetoException {



        if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
            return;
        }

        ValidationPropertyChangeEvent evt = new ValidationPropertyChangeEvent(validationSource,
                propertyName, oldValue, newValue);

        if (this.getVetoableChangeListeners().length == 0 && this.delegateListener == null) {
            throw new ValidationChangeSupportException(this, evt, "Empty vetoable change listeners list!");
        }
        if (this.getVetoableChangeListeners().length > 0) {
            super.fireVetoableChange(evt);
        }
        if (this.delegateListener != null) {
            delegateListener.vetoableChange(evt);
        }

    }

    @Override
    public void fireVetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        fireVetoableChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    /**
     * @return the delegateListener
     */
    public VetoableChangeListener getDelegateListener() {
        return delegateListener;
    }

    /**
     * @param delegateListener the delegateListener to set
     */
    public void setDelegateListener(VetoableChangeListener delegateListener) {
        this.delegateListener = delegateListener;
    }
}