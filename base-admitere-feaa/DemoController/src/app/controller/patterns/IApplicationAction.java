/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.patterns;

import java.io.Serializable;

/**
 *
 * @author catalin
 */
public interface IApplicationAction extends Serializable{
    String getName(); 
    IApplicationEvent actionPerformed(IApplicationEvent event);
    
    <T> T getValue(String name);
    <T> void setValue(String name, T value);
}
