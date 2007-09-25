/**
 * 
 */
package org.ranjith.jspent;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.SaveActionListener;

/**
 * @author ranjith
 *
 */
public abstract class CommonDataPanel extends JPanel {
	public abstract void setDoneButtonListener(BackActionListener backActionListener);
	public abstract void setSaveButtonListener(SaveActionListener saveActionListener);
	public abstract void fireSaveButtonPressed(ActionEvent e);
	public abstract void showAddNew();
	public abstract Object getDataObject();
	public abstract boolean isDirty();
}
