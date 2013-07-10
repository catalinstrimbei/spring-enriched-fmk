/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client;

import app.client.controller.RichClientApplication;
import app.client.ui.FormLogin;
import app.client.ui.MainForm;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author catalin
 */
public class SecurRichClient extends RichClientApplication{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void startup(){
        logger.info("VinzariRichClient starting ... ");
        JFrame mainFrm = new MainForm(false, SINGLE_FRAME_APP);
        this.setMainForm(mainFrm);
        mainFrm.setEnabled(false);
        mainFrm.setFocusableWindowState(false);

        logger.info("VinzariRichClient login ... ");
        JFrame formLogin = new FormLogin();
        formLogin.setVisible(true);

    }

    @Override
    public void shutdown(){
        logger.info("VinzariRichClient shutdown !");
    }

    public static void main(String[] args){
        new SecurRichClient().lunch();
    }
}
