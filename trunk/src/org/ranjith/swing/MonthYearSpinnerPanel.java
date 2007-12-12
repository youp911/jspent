package org.ranjith.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MonthYearSpinnerPanel extends JPanel implements ItemListener, ActionListener {
    static String[] months = {"January","February","March","April","May","June",
        "July","August","September","October","November","December"};
    
    Calendar date = Calendar.getInstance();
    ModernToggleButton monthToggle;
    ModernToggleButton yearToggle;
    ModernButton prevButton;
    ModernButton nextButton;
    int monthIndex;
    private List<ChangeListener> changeListenerList;
    
    static final ModernButton refButton = new ModernButton("September");
    public MonthYearSpinnerPanel() {
    	this.setOpaque(false);
    	this.setLayout(new BorderLayout());
        
        prevButton = getPrevButton();
        prevButton.addActionListener(this);
        monthToggle = getMonthToggle();
        yearToggle = getYearToggle();
        
        monthToggle.addItemListener(this);
        yearToggle.addItemListener(this);
        monthToggle.setSelected(true);
        
        nextButton = getNextButton();
        nextButton.addActionListener(this);
        
        
        this.add(prevButton, BorderLayout.LINE_START);
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(nextButton, BorderLayout.LINE_END);
        changeListenerList = new ArrayList<ChangeListener>();
    }

    private ModernButton getNextButton() {
        return getButton("images/resultset_next.png", ModernButton.BUTTONSTYLE_TOOLBAR_RIGHT);
    }

    /**
     * @param imageURL
     * @param style
     * @return
     */
    private ModernButton getButton(String imageURL, int style) {
        ModernButton button = new ModernButton(); 
        button.setButtonStyle(style);
        button.setIcon(new ImageIcon(MonthYearSpinnerPanel.class.getResource(imageURL)));
        return button;
    }

    private ModernButton getPrevButton() {
       return getButton("images/resultset_previous.png", ModernButton.BUTTONSTYLE_TOOLBAR_LEFT);
    }

    private Component getCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(monthToggle,BorderLayout.LINE_START);
        centerPanel.add(yearToggle,BorderLayout.LINE_END);
        return centerPanel;
    }

    private ModernToggleButton getMonthToggle() {
        monthIndex = date.get(date.MONTH);
        ModernToggleButton toggleButton =  getToggleButton(months[monthIndex]);
        toggleButton.setPreferredSize(refButton.getPreferredSize());
        toggleButton.setMinimumSize(refButton.getPreferredSize());
        return toggleButton;
    }

    private ModernToggleButton getYearToggle() {
        return getToggleButton("" + date.get(date.YEAR));
    }

    /**
     * @param value
     * @return
     */
    private ModernToggleButton getToggleButton(String value) {
        ModernToggleButton toggle = new ModernToggleButton(value);
        toggle.setButtonStyle(ModernButton.BUTTONSTYLE_TOOLBAR_CENTER);
        return toggle;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        //if(event.getSource() == monthToggle && event.getStateChange() == ItemEvent.SELECTED) {
        if(event.getSource() == monthToggle) { 
            yearToggle.setSelected(!monthToggle.isSelected());
        }
        if(event.getSource() == yearToggle) { 
            monthToggle.setSelected(!yearToggle.isSelected());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == nextButton) {
            if(monthToggle.isSelected()) {
                monthIndex++;
                if(monthIndex > 11) {
                    monthIndex = 0;
                }
                monthToggle.setText(months[monthIndex]);
            }
            
            if(yearToggle.isSelected()) {
                int year = Integer.parseInt(yearToggle.getText());
                year++;
                if(year > 2050) {
                    year = 2050;
                    return;
                }
                yearToggle.setText(""+ year);
            }
        }
        if(event.getSource() == prevButton) {
            if(monthToggle.isSelected()) {
                monthIndex--;
                if(monthIndex < 0) {
                    monthIndex = 11;
                }
                monthToggle.setText(months[monthIndex]);
            }
            if(yearToggle.isSelected()) {
                int year = Integer.parseInt(yearToggle.getText());
                --year;
                if(year < 1900) {
                    year = 1900;
                    return;
                }
                yearToggle.setText(year + "");
            }
            
        }
        fireStateChanged();
    }
    
    private void fireStateChanged() {
    	ChangeEvent event = new ChangeEvent(this);
    	for (ChangeListener changeListener : changeListenerList) {
			changeListener.stateChanged(event);
		}
	}

	public void addChangeListener(ChangeListener changeListener) {
    	this.changeListenerList.add(changeListener);
    }
    
    public void removeChangeListener(ChangeListener cl) {
    	this.changeListenerList.remove(cl);
    }
    
    public Object getValue() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.MONTH, monthIndex);
    	cal.set(Calendar.YEAR, Integer.parseInt(yearToggle.getText()));
    	return cal;
    }
    
    public static void main(String[] args) {
		JFrame f = new JFrame("test");
		f.getContentPane().add(new MonthYearSpinnerPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
    
    @Override
    public Dimension getPreferredSize() {
    	return getPrefSize();
    }

	private Dimension getPrefSize() {
		return new Dimension(prevButton.getPreferredSize().width+ monthToggle.getPreferredSize().width + yearToggle.getPreferredSize().width + nextButton.getPreferredSize().width,
    			prevButton.getPreferredSize().height);
	}
    
    @Override
    public Dimension getMaximumSize() {
    	return getPreferredSize();
    }
    
    @Override
    public Dimension getMinimumSize() {
    	return getPreferredSize();
    }
}
