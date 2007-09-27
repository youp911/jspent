/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.ranjith.jspent.JSpent;
import org.ranjith.jspent.data.Expense;

/**
 * @author ranjith
 *
 */
public class ModifyActionListener implements ActionListener {
    private JSpent application;
	public ModifyActionListener(JSpent app) {
	    application = app;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if(application.getCurrentContext().equals(JSpent.EXPENSES)) {
	        application.showExpenseForm((Expense) application.getSelectedRowObject());
	    }
	}

}
