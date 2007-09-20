/**
 * 
 */
package org.ranjith;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author ranjith
 *
 */
public class AddNewActionListener implements ActionListener {
	TestFrame application;
	public AddNewActionListener(TestFrame testFrame) {
		this.application = testFrame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(application.getCurrentContext().equals(TestFrame.EXPENSES)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}else if(application.getCurrentContext().equals(TestFrame.INCOMES)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}else if(application.getCurrentContext().equals(TestFrame.SAVINGS)) {
			application.showAddSavings();
		}else if(application.getCurrentContext().equals(TestFrame.LIABILITIES)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}else if(application.getCurrentContext().equals(TestFrame.SUMMARY)) {
			JOptionPane.showMessageDialog(application, "Not implemented yet");
		}
	}

}
