/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.ranjith.jspent.CommonDataPanel;
import org.ranjith.jspent.JSpent;

/**
 * Takes the user back to application screen.
 * With setting context back to where it was.
 * @author ranjith
 */
public class BackActionListener implements ActionListener {
    private JSpent jSPent;
    private CommonDataPanel panel;
    public BackActionListener(JSpent app) {
        this.jSPent = app;
        this.panel = null;
    }
    public BackActionListener(JSpent app,CommonDataPanel panel) {
        this.jSPent = app;
        this.panel = panel;
    }

    /**
     * Checks if the form is 'dirty' if so, prompts to save/cancel/don't save.
     * Otherwise restores main application UI.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(panel != null && panel.isDirty()) {
        	Object[] options = {"Save","Don't Save","Cancel"};
        	int response = JOptionPane.showOptionDialog(this.jSPent,
        		    "Would you like some green eggs to go "
        		    + "with that ham?",
        		    "A Silly Question",
        		    JOptionPane.YES_NO_CANCEL_OPTION,
        		    JOptionPane.QUESTION_MESSAGE,
        		    null,
        		    options,
        		    options[2]);
        	if(response == JOptionPane.CANCEL_OPTION) {
        		return;
        	}
        	if(response == JOptionPane.YES_OPTION) {
        		panel.fireSaveButtonPressed(event);
        	}
        	
        }
        jSPent.refreshUI();
    }

}
