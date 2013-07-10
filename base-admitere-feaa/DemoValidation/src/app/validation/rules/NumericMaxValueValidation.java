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
public class NumericMaxValueValidation extends AbstractNumericValidationRule{
    private String message = "trebuie sa fie mai mica decit";
    public static final String MAXVAL_ARG = "maxvalue";

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        Number propertyValue = (Number) validationEvent.getNewValue();

        if (propertyValue != null){
//        Number maxvalArgument = this.getNumberValueOf(
//                validationEvent.getValue(MAXVAL_ARG).toString(),
//                propertyValue.getClass());
        Number maxvalArgument = this.getNumberValueOf(
                getValue(MAXVAL_ARG).toString(),
                propertyValue.getClass());

            System.out.println("Check " + maxvalArgument + " maximum value for " +
                    propertyName + " = "+ propertyValue);
            if (this.compare(propertyValue, maxvalArgument) == 1){
                String customMessage = "Valoarea " + propertyName + " " + message + maxvalArgument + " !";
                throw new PropertyVetoException(customMessage, validationEvent);
            }
        }
        return true;
    }
}
