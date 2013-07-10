/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.rules;

import app.validation.controller.ValidationEvent;
import java.beans.PropertyVetoException;

/**
 *
 * @author catalin
 */
public class NumericMinValueValidation extends AbstractNumericValidationRule {

    private String message = "trebuie sa fie mai mare decit";
    public static final String MINVAL_ARG = "minvalue";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        Number propertyValue = (Number) validationEvent.getNewValue();

        if (propertyValue != null){
//        Number minvalArgument = this.getNumberValueOf(
//                validationEvent.getValue(MINVAL_ARG).toString(),
//                propertyValue.getClass());

        Number minvalArgument = this.getNumberValueOf(
                getValue(MINVAL_ARG).toString(),
                propertyValue.getClass());

            System.out.println("Check " + minvalArgument + " minimum value for " +
                    propertyName + " = "+ propertyValue + " : " + this.compare(propertyValue, minvalArgument));
            if (this.compare(propertyValue, minvalArgument) == -1){
                String customMessage = "Valoarea " + propertyName + " " + message + minvalArgument + " !";
                throw new PropertyVetoException(customMessage, validationEvent);
            }
        }
        return true;
    }
}
