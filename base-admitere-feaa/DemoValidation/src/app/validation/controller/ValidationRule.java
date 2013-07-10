/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.controller;

import app.validation.patterns.IValidationRule;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author catalin
 */
public abstract class ValidationRule implements IValidationRule{
    public static String MESSAGE = "message";
    
    protected Map<String, Object> attributes = new HashMap<String, Object>();

    public Object getValue(String name){
        return attributes.get(name);
    }
    public void setValue(String name, Object value){
        attributes.put(name, value);
    }
    public String[] getAttributeNames(){
        return this.attributes.keySet().toArray(new String[]{});
    }

    public <T> void setValues(Map<String, T> values){
        for (Map.Entry<String, T> value : values.entrySet()){
            setValue(value.getKey(), value.getValue());
        }
    }
}
