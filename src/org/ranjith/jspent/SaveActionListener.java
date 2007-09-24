/**
 * 
 */
package org.ranjith.jspent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.util.HibernateUtil;

/**
 * An action to save object in to database. 
 * The save can be "add new" or "edit-save"
 * @author ranjith
 */
public class SaveActionListener implements ActionListener{
	public static  int ADD_NEW_MODE = 0;
	public static  int UPDATE_MODE = 1;
	private int mode = ADD_NEW_MODE;
	private CommonPanel panel = null;
	/**
	 * Creates a listener for specified save mode.
	 * Save mode can be either 
	 * SaveButtonListener.ADD_NEW_MODE or
	 * SaveButtonListener.UPDATE_MODE any other value
	 * will default to SaveButtonListener.ADD_NEW_MODE
	 * @param mode
	 */
	public SaveActionListener(int mode) {
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
	public SaveActionListener(int mode, CommonPanel panel) {
		this.mode = mode;
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ExpenseService.saveExpense((Expense) panel.getDataObject());
		panel.showAddNew();
	}
}
