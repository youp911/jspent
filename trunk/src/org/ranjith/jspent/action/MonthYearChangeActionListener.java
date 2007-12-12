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
import org.ranjith.swing.MonthYearSpinnerPanel;
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
    	MonthYearSpinnerPanel source = (MonthYearSpinnerPanel) event.getSource();
        int month = getMonth(source);
        application.setCurrentMonth(month);
        application.refreshUI();
    }

    /**
     * @param source
     * @return
     */
    public static int getMonth(MonthYearSpinnerPanel source) {
        Calendar cal = (Calendar) source.getValue();
        int month=cal.get(Calendar.MONTH) +1;
        return month;
    }

}
