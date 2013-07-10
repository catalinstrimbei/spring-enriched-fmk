/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client.ui.binding;

import app.domain.model.Adresa;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author catalin
 */
public class AdresaConverter extends Converter<Adresa, String>{

    @Override
    public String convertForward(Adresa value) {
        return value.toString();
    }

    @Override
    public Adresa convertReverse(String value) {
        String[] parts = value.split(",");
        String adr[] = new String[]{"", "", ""};
        for (int i = 0; i < adr.length; i++){
            if (i < parts.length)
                adr[i] = parts[i];
        }
        return new Adresa(adr[0], adr[2], adr[1]);
    }

}
