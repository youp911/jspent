/**
 * 
 */
package org.ranjith.jspent.action;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.ranjith.jspent.JSpent;

/**
 * @author ranjith
 *
 */
public class RowSelectionActionListener implements ListSelectionListener {
    private JSpent application = null;
    public RowSelectionActionListener(JSpent spent) {
        application = spent;
    }

    /* (non-Javadoc)
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        System.out.println(listSelectionEvent.getFirstIndex() );
        if(listSelectionEvent.getFirstIndex() >= 0) {
            application.setModfyToolBarButtonEnabled(true);
            application.setDeleteToolBarButtonEnabled(true);
        }
        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
    }

}
