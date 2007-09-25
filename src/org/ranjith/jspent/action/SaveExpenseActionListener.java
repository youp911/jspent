/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.ranjith.jspent.CommonDataPanel;
import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.util.HibernateUtil;
import org.ranjith.util.SwingRUtil;

/**
 * An action to save object in to database. 
 * The save can be "add new" or "edit-save"
 * @author ranjith
 */
public class SaveExpenseActionListener extends SaveActionListener{

	private int mode = ADD_NEW_MODE;
	private CommonDataPanel panel = null;
	/**
	 * Creates a listener for specified save mode.
	 * Save mode can be either 
	 * SaveButtonListener.ADD_NEW_MODE or
	 * SaveButtonListener.UPDATE_MODE any other value
	 * will default to SaveButtonListener.ADD_NEW_MODE
	 * @param mode
	 */
	public SaveExpenseActionListener(int mode) {
		if(mode <= UPDATE_MODE) {
			this.mode = mode;
		}
	}
	
	/**
	 * Creates a save action listener based on mode and a panel 
	 * with "save" button. CommonPanel's public object getData()
	 * will be used to save.
	 * @param add_new_mode2
	 * @param panel
	 */
	public SaveExpenseActionListener(int mode, CommonDataPanel panel) {
		this.mode = mode;
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    try{
	        List errors = panel.getValidationErrors();
	        if(errors !=null && !errors.isEmpty()) {
	            SwingRUtil.showErrorDialog(errors, "Can't Save", panel);
	            return;
	        }
	        ExpenseService.saveExpense((Expense) panel.getDataObject());
	        panel.showAddNew();
	    }catch(Throwable t) {
	        t.printStackTrace();
	        JOptionPane.showMessageDialog(panel, "Error occured!");
	    }
	}
}
