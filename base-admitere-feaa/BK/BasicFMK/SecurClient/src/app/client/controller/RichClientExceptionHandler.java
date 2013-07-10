/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.client.controller;


import app.validation.support.ValidationChangeSupport;
import app.validation.support.ValidationChangeSupportException;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author catalin
 */
public class RichClientExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void uncaughtException(Thread t, Throwable e) {
        try {
            handle(e);
        } catch (Exception ex) {
            Logger.getLogger(RichClientExceptionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Throwable getApplicationException(Throwable e) {
        if (e.getCause() != null) {
            return getApplicationException(e.getCause());
        }
        return e;
    }

    public void handle(Throwable e) throws Exception {
        Throwable rootException = getApplicationException(e);
        logger.info("Exception Class: " + rootException.getClass().getName());
        Object evtSource = EventQueue.getCurrentEvent();

        Throwable managedException = rootException;

//        if (rootException instanceof ValidationChangeSupportException) {
//            try {
//                handleValidationException((ValidationChangeSupportException) rootException);
//                return;
//            } catch (Exception ex) {
//                managedException = ex;
//            }
//
//        }
        managedException.printStackTrace();
        if (evtSource != null && evtSource instanceof AWTEvent) {
            AWTEvent awtEvent = (AWTEvent) evtSource;
            if (awtEvent.getSource() != null && awtEvent.getSource() instanceof JComponent) {
                JComponent sourceComponent = (JComponent) awtEvent.getSource();
                sourceComponent.grabFocus();
            }
        }
        JOptionPane.showMessageDialog(null, managedException.getMessage(),
                rootException.getClass().getName(), JOptionPane.ERROR_MESSAGE);


    }

    public static void register() {
        System.setProperty("sun.awt.exception.handler", RichClientExceptionHandler.class.getName());
    }

//    private void handleValidationException(ValidationChangeSupportException vcsEx) throws PropertyVetoException {
//        ValidationChangeSupport sourceSupport = vcsEx.getSourceSupport();
//        PropertyChangeEvent sourceEvt = vcsEx.getSourceEvent();
//
//        VetoableChangeListener remoteValidationController =
//                RichClientApplication.getBusinessService("validationController");
//        if (sourceSupport != null){
//            if (remoteValidationController != null) {
//                sourceSupport.setDelegateListener(remoteValidationController);
//                sourceSupport.fireVetoableChange(sourceEvt);
//            } else {
//                logger.info("remoteValidationController is not reachable ...");
//                new RuntimeException(vcsEx.getMessage());
//                throw vcsEx;
//            }
//        }
//        else
//            throw vcsEx;
//    }
}
