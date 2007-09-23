/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.ranjith.jspent.JSpent;

/**
 * @author ranjith
 * Takes the user back to application screen.
 * With setting context back to where it was.
 */
public class BackActionListener implements ActionListener {
    private JSpent testFrame;
    public BackActionListener(JSpent testFrame) {
        this.testFrame = testFrame;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
    	testFrame.setCurrentContext(testFrame.getCurrentContext());
        testFrame.restoreUI();
    }

}
