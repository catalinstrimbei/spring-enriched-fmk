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
public class StringCaseValidation extends ValidationRule {

    private String inMessage = "trebuie scrisa cu";
    public static final String CASE_TYPE_RULE_ARG = "caseType";
    public static final String INITCAP_CASE = "initcap";
    public static final String UPPER_CASE = "upper";
    public static final String LOWER_CASE = "lower";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        String propertyStringValue = (String) validationEvent.getNewValue();

        String caseArgument = (String) getValue(CASE_TYPE_RULE_ARG);
        String customMessage = (String) getValue(MESSAGE);
        if (customMessage == null)
                customMessage = "Valoarea " + propertyName + " " + inMessage + " majuscula !";
        if (propertyStringValue != null) {

            if (caseArgument.equals(INITCAP_CASE)) {
                if (!propertyStringValue.startsWith(propertyStringValue.substring(0, 1).toUpperCase())) {
                    throw new PropertyVetoException(customMessage, validationEvent);
                }
            } else if (caseArgument.equals(UPPER_CASE)) {
                if (!propertyStringValue.equals(propertyStringValue.toUpperCase())) {
                    throw new PropertyVetoException(customMessage, validationEvent);
                }
            } else if (caseArgument.equals(LOWER_CASE)) {
                if (!propertyStringValue.equals(propertyStringValue.toLowerCase())) {
                    throw new PropertyVetoException(customMessage, validationEvent);
                }
            }
        }
        return true;
    }

    public void setCaseType(String value){
        setValue(CASE_TYPE_RULE_ARG, value);
    }
    public void setMessage(String value){
        setValue(MESSAGE, value);
    }
}
