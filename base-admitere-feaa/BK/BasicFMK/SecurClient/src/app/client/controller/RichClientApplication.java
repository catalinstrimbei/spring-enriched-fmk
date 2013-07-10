/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.client.controller;

import app.controller.patterns.IApplicationController;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author catalin
 */
public class RichClientApplication {
    public static final int SINGLE_FRAME_APP = 1;
    public static final int MULTI_FRAME_APP = 2;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // Singleton
    public static RichClientApplication INSTANCE;

    public RichClientApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        return INSTANCE;
    }
    ;

    // context resources
    protected IApplicationController appActionController;

    public static IApplicationController getActionController() {
        return (IApplicationController) INSTANCE.appActionController;
    }
    ;

    public static IApplicationController setActionController() {
        return (IApplicationController) INSTANCE.appActionController;
    }
    ;
    protected BeanFactory beanFactory;

    public static <T> T getBusinessService(String businessServiceId) {
        return (T) INSTANCE.beanFactory.getBean(businessServiceId);
    }
    protected Component mainForm;

    public Component getMainForm() {
        return mainForm;
    }

    public void setMainForm(Component mainForm) {
        this.mainForm = mainForm;
    }

    protected int applicationFrameType = SINGLE_FRAME_APP;

    public int getApplicationFrameType() {
        return applicationFrameType;
    }

    public void setApplicationFrameType(int applicationFrameType) {
        this.applicationFrameType = applicationFrameType;
    }
    

    // lifecycle
    public void lunch() {
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    getINSTANCE().initialization();
                    getINSTANCE().startup();

                    if (getMainForm() != null) {
                        getMainForm().setPreferredSize(new Dimension(800, 600));
                        if (getMainForm() instanceof JFrame)
                            ((JFrame)getMainForm()).pack();
                        getMainForm().setVisible(true);
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ;

    public static void exit() {
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    INSTANCE.shutdown();
                    System.exit(0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lifecycle customize
    private void initialization() {
        this.beanFactory = (BeanFactory) new ClassPathXmlApplicationContext("META-INF/clientContext.xml");
        RichClientExceptionHandler.register();
        this.appActionController = (IApplicationController) this.beanFactory.getBean("richClientActionController");
        try {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(RichClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void startup() {
        logger.info("RichClientApplication starting ... ");
    }
    ;

    protected void shutdown() {
        logger.info("RichClientApplication shutdown !");
    }
    ;
}

