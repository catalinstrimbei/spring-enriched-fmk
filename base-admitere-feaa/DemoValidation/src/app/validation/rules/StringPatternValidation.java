/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.rules;

import app.validation.controller.ValidationEvent;
import app.validation.controller.ValidationRule;
import java.beans.PropertyVetoException;

/**
 *
 * @author catalin
 */
public class StringPatternValidation extends ValidationRule {
    private String inMessage = "trebuie scrisa dupa sablonul";

    public static String INITCASE_PATTERN;
    public static String UPPERCASE_PATTERN;
    public static String LOWERCASE_PATTERN;
    public static String MAIL_PATTERN;
    public static String URL_PATTERN;
    public static String PHONE_PATTERN;
    public static String ALFANUMERIC_PATTERN;
    public static String NUMERIC_PATTERN;
    public static String ALFA_PATTERN;

    public boolean validate(ValidationEvent validationEvent) throws PropertyVetoException {
        String propertyName = validationEvent.getPropertyName();
        String propertyStringValue = (String) validationEvent.getNewValue();
        String regExPattern = (String) this.getValue("regExPattern");
        String patternName = (String) this.getValue("patternName");
        String customMessage = (String) getValue(MESSAGE);
        if (customMessage == null)
                customMessage = "Valoarea " + propertyName + " " + inMessage + "<"
                        + ((patternName != null)? patternName : regExPattern)
                        + "> !";

        if (!propertyStringValue.matches(regExPattern)){
            throw new PropertyVetoException(customMessage, validationEvent);
        }
        return true;
    }


    public void setPattern(String patternName){
        if (patternName.toLowerCase().equals("initcase"))
            this.setValue("regExPattern", INITCASE_PATTERN);
        if (patternName.toLowerCase().equals("lowercase"))
            this.setValue("regExPattern", LOWERCASE_PATTERN);
        if (patternName.toLowerCase().equals("uppercase"))
            this.setValue("regExPattern", UPPERCASE_PATTERN);
        if (patternName.toLowerCase().equals("mail"))
            this.setValue("regExPattern", MAIL_PATTERN);
        if (patternName.toLowerCase().equals("url"))
            this.setValue("regExPattern", URL_PATTERN);
        if (patternName.toLowerCase().equals("phone"))
            this.setValue("regExPattern", PHONE_PATTERN);
        if (patternName.toLowerCase().equals("alfanumeric"))
            this.setValue("regExPattern", ALFANUMERIC_PATTERN);
        if (patternName.toLowerCase().equals("NUMERIC"))
            this.setValue("regExPattern", NUMERIC_PATTERN);
        if (patternName.toLowerCase().equals("alfan"))
            this.setValue("regExPattern", ALFA_PATTERN);

        this.setValue("patternName", patternName);

    }

    public void setMessage(String value){
        setValue(MESSAGE, value);
    }

    public void setRegExPattern(String regExPattern){
        this.setValue("regExPattern", regExPattern);
    }
}
