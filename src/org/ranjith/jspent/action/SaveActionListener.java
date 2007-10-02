/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action associated with save actions. All save actions should implement this class.
 * @author ranjith
 *
 */
public abstract class SaveActionListener implements ActionListener {
	public static final int ADD_NEW_MODE = 0;
	public static final int UPDATE_MODE = 1;
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent arg0);

}
