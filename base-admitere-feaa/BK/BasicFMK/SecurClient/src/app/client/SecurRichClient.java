/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client;

import app.client.controller.RichClientApplication;
import app.client.ui.MainForm;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class SecurRichClient extends RichClientApplication{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void startup(){
        logger.info("VinzariRichClient starting ... ");
        this.setMainForm(new MainForm(false, SINGLE_FRAME_APP));
    }

    @Override
    public void shutdown(){
        logger.info("VinzariRichClient shutdown !");
    }

    public static void main(String[] args){
        new SecurRichClient().lunch();
    }
}
