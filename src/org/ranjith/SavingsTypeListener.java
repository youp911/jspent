/**
 * 
 */
package org.ranjith;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * @author XR1CTSO
 *
 */
public class SavingsTypeListener implements ActionListener {
    private TestFrame testFrame;
    public SavingsTypeListener(TestFrame testFrame) {
        this.testFrame = testFrame;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        JComponent component = new SavingsTypePlugin().loadUI((String) comboBox.getSelectedItem());
        testFrame.setForm(component);
    }

}
