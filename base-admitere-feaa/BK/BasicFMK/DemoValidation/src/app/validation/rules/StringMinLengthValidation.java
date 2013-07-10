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
public class StringMinLengthValidation extends ValidationRule {

    private String message = "trebuie sa aiba lungimea minima de ";
    public static final String LENGTH_ARG = "length";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        String propertyStringValue = (String) validationEvent.getNewValue();

        Integer lengthArgument = Integer.valueOf(getValue(LENGTH_ARG).toString());
        if (propertyStringValue != null) {
            if (propertyStringValue != null) {
                if (propertyStringValue.length() < lengthArgument.intValue()) {
                    String customMessage = "Valoarea " + propertyName + " " + message +
                            lengthArgument + " caractere !";
                    throw new PropertyVetoException(customMessage, validationEvent);
                }
            }
        }
        return true;
    }

    public void setLength(Integer length){
        setValue(LENGTH_ARG, length);
    }
}
