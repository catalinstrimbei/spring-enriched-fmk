/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client.ui;
import org.jdesktop.beansbinding.Converter;
/**
 *
 * @author catalin
 */
public class UserEnabledConvertor extends Converter<Boolean, String>{

    @Override
    public String convertForward(Boolean value) {
        if (value)
            return "Activ";
        else
            return "Inactiv";
    }

    @Override
    public Boolean convertReverse(String value) {
        if (value.equals("Activ"))
            return true;
        else
            return false;
    }



}
