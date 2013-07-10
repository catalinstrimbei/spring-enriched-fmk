/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller.patterns;

import java.io.Serializable;
import java.util.EventListener;

/**
 *
 * @author catalin
 */
public interface IApplicationEvent  extends EventListener, Serializable{
    Object getSource();
    String getActionName();
    
    <T> T getValue(String name);
    <T> void setValue(String name, T value);
    String[] getAttributeNames();
}
