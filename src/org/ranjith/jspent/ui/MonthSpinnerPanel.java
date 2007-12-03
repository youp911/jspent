/**
 * 
 */
package org.ranjith.jspent.ui;

import java.awt.Component;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
/**
 * @author ranjith
 *
 */
public class MonthSpinnerPanel extends JPanel {
    private static final Border RAISED_SOFT_BEVEL_BORDER = new SoftBevelBorder(BevelBorder.RAISED);
    private static final Border LOWERED_SOFT_BEVEL_BORDER = new SoftBevelBorder(BevelBorder.LOWERED);
    Calendar date = Calendar.getInstance();
    static String[] months = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    private JLabel monthLabel = null;
    private JLabel yearLabel = null;
    
    public MonthSpinnerPanel() {
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        add(getPrevButton());
        add(getMonthLabel());
        add(getYearLabel());
        add(getNextButton());
    }

    private JButton getNextButton() {
        return null;
    }

    private JLabel getYearLabel() {
        yearLabel = getLabel(Calendar.YEAR + "");
        return yearLabel;
    }

    private JLabel getMonthLabel() {
        int month = date.get(date.MONTH);
        monthLabel = getLabel(months[month]);
        return monthLabel;
    }

    private JButton getPrevButton() {
        return null;
    }
    
    private JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(RAISED_SOFT_BEVEL_BORDER);
        return label;
    }
}
