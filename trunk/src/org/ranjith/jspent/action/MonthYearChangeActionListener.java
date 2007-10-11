/**
 * 
 */
package org.ranjith.jspent.action;

import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.ranjith.jspent.ui.JSpent;
import org.ranjith.swing.SimpleRoundSpinner;

/**
 * Month year value change handler.
 * @author ranjith
 *
 */
public class MonthYearChangeActionListener implements ChangeListener {
    private JSpent application = null;
    public MonthYearChangeActionListener(JSpent application) {
        this.application = application;
    }
    
    /**
     * Updates list of expenses shown in application.
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        SimpleRoundSpinner source = (SimpleRoundSpinner) event.getSource();
        int month = getMonth(source);
        application.setCurrentMonth(month);
        application.refreshUI();
    }

    /**
     * @param source
     * @return
     */
    public static int getMonth(SimpleRoundSpinner source) {
        Date date = (Date) source.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month=cal.get(Calendar.MONTH) +1;
        return month;
    }

}
