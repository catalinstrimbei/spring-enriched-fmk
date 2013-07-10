/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.support;

import java.beans.VetoableChangeListener;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class ValidationAutoWire {

    public ValidationAutoWire(VetoableChangeListener listener) {
        ValidationChangeSupport.DEFAULT_DELEGATE_LISTENER = listener;
        Logger.getLogger(this.getClass().getName()).info("Autowired ValidationChangeSupport ...");
    }

}
