/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.ranjith.jspent.JSpent;

/**
 * Handler to add new action performed on the toolbar.
 * @author ranjith
 *
 */
public class AddNewActionListener implements ActionListener {
	JSpent application;
	public AddNewActionListener(JSpent testFrame) {
		this.application = testFrame;
	}

	/**
	 * Check what is the current context and based on it, show the 'add new'
	 * form.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(application.getCurrentContext().equals(JSpent.EXPENSES)) {
			application.showExpenseForm();
		}else if(application.getCurrentContext().equals(JSpent.INCOMES)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}else if(application.getCurrentContext().equals(JSpent.SAVINGS)) {
			application.showAddSavings();
		}else if(application.getCurrentContext().equals(JSpent.LIABILITIES)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}else if(application.getCurrentContext().equals(JSpent.SUMMARY)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}
	}

}
