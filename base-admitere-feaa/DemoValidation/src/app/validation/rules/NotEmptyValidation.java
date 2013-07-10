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
public class NotEmptyValidation extends ValidationRule{

    private String message = "este obligatorie !";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        Object propertyValue = validationEvent.getNewValue();
        System.out.println("Check not empty for" + propertyValue);
        if (propertyValue == null || propertyValue.toString().trim().isEmpty()) {
            String customMessage = "Valoarea " + propertyName + " " + message;
            throw new PropertyVetoException(customMessage, validationEvent);
        }
        return true;

    }
}
