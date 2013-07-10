/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author catalin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // To start SpringContainer as stand-alone server
        // just create the Spring ApplicationContext
        //context = new ClassPathXmlApplicationContext("META-INF/serverContext.xml");
        context = new ClassPathXmlApplicationContext("META-INF/jboss-spring.xml");
    }

    private static ApplicationContext context;
    public static ApplicationContext getApplicationContext(){
        return context;
    }
}
