/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services.impl;

import app.services.ApplicationService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 */
public class ApplicationServiceImpl implements ApplicationService {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public ApplicationServiceImpl(){
        logger.log(Level.INFO, ">>>> Start AppServiceVinzari Service ....");
    }



    public void serviceOpperation() {
        logger.info("Application Service execution ... ... ");
    }

}
