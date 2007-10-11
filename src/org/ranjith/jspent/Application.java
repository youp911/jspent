/**
 * 
 */
package org.ranjith.jspent;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.ranjith.jspent.ui.JSpent;

/**
 * The jSpent Application
 * 
 * @author ranjith
 */
public class Application {
    private static final String APP_LOGGER = "Application";
    private static final Logger LOGGER = Logger.getLogger(APP_LOGGER);
    
    private static ResourceBundle bundle = null;
    
    public static void main(String[] args) {
        initAndRunUI();
    }

    public static synchronized ResourceBundle getResourceBundle() {
        if(bundle == null) {
            bundle = ResourceBundle.getBundle("org.ranjith.jspent.messages");
        }
        return bundle;
    }
    
    private static void initAndRunUI() {
        LOGGER.info("Staring application");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                  JSpent jSpent = new JSpent();
                  jSpent.setVisible(true);
                } 
            });
        }catch(Exception e) {
            LOGGER.warning("System look and feel can not be used");
        }
    }
}
