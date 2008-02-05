/**
 * 
 */
package org.ranjith.jspent.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @author ranjith
 *
 */
public class ContextMenuListener extends MouseAdapter {
	JPopupMenu popupMenu;
	
	public ContextMenuListener(JPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		showPopup(event);
	}

	private void showPopup(MouseEvent event) {
		if(event.isPopupTrigger()) {
			popupMenu.show(event.getComponent(), event.getX(), event.getY());
		}
	}
	
}
