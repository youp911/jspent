/**
 * 
 */
package org.ranjith.jspent.action;

import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.ranjith.jspent.JSpent;
import org.ranjith.swing.SimpleRoundSpinner;

/**
 * @author XR1CTSO
 *
 */
public class MonthYearChangeActionListener implements ChangeListener {
    private JSpent application = null;
    public MonthYearChangeActionListener(JSpent application) {
        this.application = application;
    }
    /*
     * updates expesnses shown in current application.
     *  (non-Javadoc)
     * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        SimpleRoundSpinner source = (SimpleRoundSpinner) event.getSource();
        Date date = (Date) source.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        application.updateExpenseTableForMonth(cal.get(Calendar.MONTH) + 1);
        application.setTotal();
    }

}
