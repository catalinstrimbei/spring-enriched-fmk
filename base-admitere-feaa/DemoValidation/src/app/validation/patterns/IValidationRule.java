/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.patterns;

import app.validation.controller.*;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.Map;

public interface IValidationRule extends Serializable{
    boolean validate(ValidationEvent validationEvent) throws PropertyVetoException;
    // atrribute - parameters
    <T> T getValue(String name);
    <T> void setValue(String name, T value);
    String[] getAttributeNames();
    <T> void setValues(Map<String, T> values);
}
