/**
 * 
 */
package org.ranjith;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author XR1CTSO
 *
 */
public class AddSavingsListener implements ActionListener {
    private TestFrame testFrame;
    public AddSavingsListener(TestFrame testFrame) {
        this.testFrame = testFrame;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        testFrame.showAddSavings();
    }

}
