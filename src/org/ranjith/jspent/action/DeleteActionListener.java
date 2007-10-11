/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.jspent.ui.JSpent;

/**
 * Action handler for delete command from toolbar(or elsewhere)
 * @author ranjith
 *
 */
public class DeleteActionListener implements ActionListener {
    private JSpent application = null;
	public DeleteActionListener(JSpent testFrame) {
	    this.application = testFrame;
	}

	/**
	 * Prompt and then delete on YES
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    Object[] options = {"Delete","Don't Delete"};
        if(JOptionPane.showOptionDialog(this.application,
                    "Are you sure you want to delete the selected row?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]) == JOptionPane.YES_OPTION ) {    
    	    Object selectedRowObject = application.getSelectedRowObject();
    	    ExpenseService.delete((Expense) selectedRowObject);
    	    application.removeSelectedRowObject();
        }
	}

}
