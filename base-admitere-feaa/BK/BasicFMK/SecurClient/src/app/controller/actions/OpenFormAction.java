/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller.actions;

import app.client.controller.RichClientApplication;
import app.client.controller.RichMainForm;
import app.controller.AppAction;
import app.controller.patterns.IApplicationEvent;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author catalin
 */
public class OpenFormAction extends AppAction {

    public static final String FORM_CLASS = "formClass";

    public OpenFormAction(String mappingName, String formClassName)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        this(mappingName, Class.forName(formClassName));
    }

    public OpenFormAction(String mappingName, Class formClass)
            throws InstantiationException, IllegalAccessException {
        super(mappingName);
        this.setValue(OpenFormAction.FORM_CLASS, formClass);
    }

    public IApplicationEvent actionPerformed(IApplicationEvent e) {
        Class formClass = this.getValue(OpenFormAction.FORM_CLASS);
        Component form;
        try {
            form = (Component) formClass.newInstance();
            if (form instanceof JFrame) {
                ((JFrame) form).setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            if (form instanceof JDialog) {
                ((JDialog) form).setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }
            Component mainForm = RichClientApplication.INSTANCE.getMainForm();
            if (mainForm != null && mainForm instanceof RichMainForm){
                ((RichMainForm)mainForm).showForm(form);
            }else
                form.setVisible(true);

        } catch (InstantiationException ex) {
            Logger.getLogger(OpenFormAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OpenFormAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }
}
