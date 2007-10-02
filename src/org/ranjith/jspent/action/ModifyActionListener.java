/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.ranjith.jspent.JSpent;
import org.ranjith.jspent.data.Expense;

/**
 * Action handler for "Modify" command on toolbar(or from elsewhere)
 * @author ranjith
 *
 */
public class ModifyActionListener implements ActionListener {
    private JSpent application;
	public ModifyActionListener(JSpent app) {
	    application = app;
	}

	/**
	 * Based on application context, show modify form.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if(application.getCurrentContext().equals(JSpent.EXPENSES)) {
	        application.showExpenseForm((Expense) application.getSelectedRowObject());
	    }
	}

}
