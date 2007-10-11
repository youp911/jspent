/**
 * 
 */
package org.ranjith.jspent.action;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.ranjith.jspent.JSpent;

/**
 * @author ranjith
 *
 */
public class OptionSelectedActionListener implements ListSelectionListener {
    private JSpent application = null;
    public OptionSelectedActionListener(JSpent application) {
        this.application = application;
    }

    /* (non-Javadoc)
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent event) {
        JList list = (JList)event.getSource();
        int option = list.getSelectedIndex();
        switch(option) {
        case 0: //expenses
            application.refreshUI();
            break;
        case 1: //Incomes
            break;
        case 3: //Savings
            application.refreshUI();
            break;
        case 4: //Liabilities
            break;
        case 5: //summary
            break;
        }
    }

}
