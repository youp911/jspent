/**
 * 
 */
package org.ranjith.swing;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A month spinner UI element.
 * let's you choose go month by month
 * using a previous or next action.
 * @author ranjith
 *
 */
public class MonthSpinnerPanel extends JPanel {
    public static final JLabel MAX = new JLabel("September");
    private EmbossedLabel monthLabel;
    private JLabel nextButton;
    private JLabel prevButton;
    private int idx;
    public static final String[] MONTHS = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    
    /**
     * Creates month spinner panel with current month.
     */
    public MonthSpinnerPanel() {
        this(Calendar.getInstance().get(Calendar.MONTH)+1);
    }
    
    /**
     * Creates month spinner panel with given month 
     * @param monthNumber number indicating month(1..12) 1 for JAN, 12 for December.
     */
    public MonthSpinnerPanel(int monthNumber) {
        validateMonth(monthNumber);
        
        idx = monthNumber - 1;
        monthLabel = new EmbossedLabel(MONTHS[idx], JLabel.CENTER);
        monthLabel.setPreferredSize(MAX.getMaximumSize());
        monthLabel.setOpaque(true);
        
        initButtons();
        prevButton.addMouseListener(new ClickListenerAdapter(ClickListenerAdapter.PREV_ACTION));
        nextButton.addMouseListener(new ClickListenerAdapter(ClickListenerAdapter.NEXT_ACTION));

        add(prevButton);
        add(monthLabel);
        add(nextButton);
    }

    private void initButtons() {
        java.net.URL vUrl = this.getClass().getResource("images/resultset_next.png");
        ImageIcon vIcon = new ImageIcon(vUrl);
        nextButton = new JLabel(vIcon);
        vUrl = this.getClass().getResource("images/resultset_previous.png");
        vIcon = new ImageIcon(vUrl);
        prevButton = new JLabel(vIcon);
    }

    private void validateMonth(int monthNumber) {
        if(monthNumber > 12 || monthNumber < 1) {
            throw new IllegalArgumentException("Month number shouldbe between 1..12");
        }
    }
    
    public void setFont(Font font) {
        if(monthLabel != null) {
            this.monthLabel.setFont(font);
        }
    }
    
    public Font getFont() {
        if(monthLabel == null) {
            return MAX.getFont();
        }
        return this.monthLabel.getFont();
    }
    
    public void addMonthChangeListener(ChangeListener listener) {
        monthLabel.addPropertyChangeListener("text",new ActionHandler(this,listener));
    }
    
    public int getMonthNumber() {
        return idx+1;
    }
    public void setMonthNumber(int monthNumber) {
        validateMonth(monthNumber);
        this.idx = monthNumber -1 ;
    }
    public String getMonth() {
        return monthLabel.getText();
    }
    
    static class ActionHandler implements PropertyChangeListener {
        private MonthSpinnerPanel component;
        private ChangeListener listener;
        
        public ActionHandler(MonthSpinnerPanel panel, ChangeListener listener) {
            this.component = panel;
            this.listener = listener;
        }
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            ChangeEvent chgEvent = new ChangeEvent(component);
            listener.stateChanged(chgEvent); 
        }
    }
    
    /**
     * class that handles prev and next actions.
     * @author ranjith
     *
     */
    class ClickListenerAdapter extends MouseAdapter {
        public static final int PREV_ACTION=0;
        public static final int NEXT_ACTION=1;
        private int action;
        
        public ClickListenerAdapter(int action) {
            this.action = action;
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if(action == PREV_ACTION) {
                idx--;
                if(idx < 0) {
                    idx = 11;
                }
            }else {
                idx++;
                if(idx > 11) {
                    idx = 0;
                }
            }
            monthLabel.setText(MONTHS[idx]);
        }

    }
    
    
    /**
     * rudimentory test.
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        final MonthSpinnerPanel mPanel = new MonthSpinnerPanel();
        mPanel.addMonthChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
                System.out.println(mPanel.getMonthNumber());
                System.out.println(mPanel.getMonth());
                }
        });
        
        frame.getContentPane().add(mPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    
    
}
