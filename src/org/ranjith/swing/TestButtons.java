package org.ranjith.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestButtons extends JFrame implements ItemListener, ActionListener {
    static String[] months = {"January","February","March","April","May","June",
        "July","August","September","October","November","December"};
    
    Calendar date = Calendar.getInstance();
    ModernToggleButton monthToggle;
    ModernToggleButton yearToggle;
    ModernButton prevButton;
    ModernButton nextButton;
    int monthIndex;
    static final ModernButton refButton = new ModernButton("September");
    public TestButtons() {
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimpleGradientPanel panel = new SimpleGradientPanel();
        
        panel.setLayout(new BorderLayout());
        
        prevButton = getPrevButton();
        prevButton.addActionListener(this);
        monthToggle = getMonthToggle();
        yearToggle = getYearToggle();
        
        monthToggle.addItemListener(this);
        yearToggle.addItemListener(this);
        monthToggle.setSelected(true);
        
        nextButton = getNextButton();
        nextButton.addActionListener(this);
        
        getContentPane().add(panel);
        panel.add(new JLabel("TEST1"), BorderLayout.PAGE_START);
        panel.add(prevButton, BorderLayout.LINE_START);

        panel.add(getCenterPanel(), BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.LINE_END);
        //panel.add(mb2, BorderLayout.LINE_END);
        panel.add(new JLabel("TEST1"), BorderLayout.PAGE_END);
        pack();
        setVisible(true);
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
        button.setIcon(new ImageIcon(TestButtons.class.getResource(imageURL)));
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestButtons t = new TestButtons();
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
    }
}
