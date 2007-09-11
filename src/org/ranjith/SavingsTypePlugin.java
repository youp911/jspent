/**
 * 
 */
package org.ranjith;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.ranjith.data.BankAccount;
import org.ranjith.data.Bonds;
import org.ranjith.data.Saving;

/**
 * @author XR1CTSO
 *
 */
public class SavingsTypePlugin {

    public  JComponent loadUI(String selectedItem) {
        return null;
    }
    
    class SavingsPanel extends JPanel {
        Saving saving;
        public SavingsPanel(SpringLayout springLayout) {
            super(springLayout);
        }

        /**
         * @return the saving
         */
        public Saving getSaving() {
            return saving;
        }

        /**
         * @param saving the saving to set
         */
        public void setSaving(Saving saving) {
            this.saving = saving;
        }
        
    }
}
