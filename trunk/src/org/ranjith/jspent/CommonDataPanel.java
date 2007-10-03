/**
 * 
 */
package org.ranjith.jspent;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPanel;

import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.SaveActionListener;
import org.ranjith.swing.SimpleGradientPanel;

/**
 * A panel to be extended by all data-form like panel.
 * This panel exposes some APIs that can be called from 
 * an application context.
 * @author ranjith
 *
 */
public abstract class CommonDataPanel extends SimpleGradientPanel {
    
    /**
     * Add a "done" or "cancel" button listener to the form.
     * The listener can determine where to transfer the UI once user
     * presses "Done" or "Cancel"
     * @param backActionListener
     */
	public abstract void setDoneButtonListener(BackActionListener backActionListener);
	
	/**
	 * Add a "Save" button action listener to the form.
	 * So that data from the panel retrieved by calling -
	 * @see getObject  and can be persisted.
	 * @param saveActionListener
	 */
	public abstract void setSaveButtonListener(SaveActionListener saveActionListener);
	
	/**
	 * Fire save button events.
	 * @param e
	 */
	public abstract void fireSaveButtonPressed(ActionEvent e);
	
	/**
	 * Show a data form for adding new data.
	 */
	public abstract void showAddNew();
	
	/**
	 * Returns underlying data object; if UI fields are filled,
	 * the data object's fields will also be filled.
	 * @return data object
	 */
	public abstract Object getDataObject();
	
	/**
	 * Returns true if the form was changed.Useful during save or 
	 * modify.
	 * @return true if form was modified. Otherwise False.
	 */
	public abstract boolean isDirty();
	/**
	 * Error handlers must check for isEmtpy or NULL
	 * on returned List.
	 * @return
	 */
    public abstract List getValidationErrors();
}
