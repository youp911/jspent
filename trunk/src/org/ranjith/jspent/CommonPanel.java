/**
 * 
 */
package org.ranjith.jspent;

import javax.swing.JPanel;

import org.ranjith.jspent.action.BackActionListener;

/**
 * @author ranjith
 *
 */
public abstract class CommonPanel extends JPanel {
	public abstract void setDoneButtonListener(BackActionListener backActionListener);
	public abstract void setSaveButtonListener(SaveActionListener saveActionListener);
	public abstract void showAddNew();
	public abstract Object getDataObject();

}
