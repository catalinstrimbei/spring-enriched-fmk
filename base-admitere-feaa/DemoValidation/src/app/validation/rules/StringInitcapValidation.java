/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.rules;

import app.validation.controller.ValidationRule;
import app.validation.controller.ValidationEvent;
import java.beans.PropertyVetoException;

/**
 *
 * @author catalin
 */
// S2: Validation Suport: Extend JavaBeans contstraint support support for validation
public class StringInitcapValidation extends ValidationRule{

    private String message = "trebuie scrisa cu majuscule !";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        String propertyStringValue = (String) validationEvent.getNewValue();
        if (propertyStringValue != null) {
            if (!propertyStringValue.startsWith(propertyStringValue.substring(0, 1).toUpperCase())) {
                String customMessage = propertyName + " " + message;
                throw new PropertyVetoException(customMessage, validationEvent);
            }
        }
        return true;
    }
}
