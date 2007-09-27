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
        if(listSelectionEvent.getFirstIndex() > 0) {
            application.setModfyToolBarButtonEnabled(true);
            application.setDeleteToolBarButtonEnabled(true);
        }
        System.out.println(listSelectionEvent.getFirstIndex());
        System.out.println(listSelectionEvent.getLastIndex());
        System.out.println(listSelectionEvent.getValueIsAdjusting());
        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
        System.out.println(lsm.isSelectionEmpty());
    }

}
