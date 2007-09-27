/**
 * 
 */
package org.ranjith.jspent;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author ranjith
 *
 */
public class LostFocusListener implements FocusListener {
	private JSpent application;

	public LostFocusListener(JSpent spent) {
      this.application = spent;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		//FIXME
		application.setModfyToolBarButtonEnabled(false);
		application.setDeleteToolBarButtonEnabled(false);
	}

}
