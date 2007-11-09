package org.ranjith.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestButtons extends JFrame {

    public TestButtons() {
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimpleGradientPanel panel = new SimpleGradientPanel();
        panel.setLayout(new BorderLayout());
        
        ModernButton mb1 = new ModernButton("LEFT"); mb1.setButtonStyle(ModernButton.BUTTONSTYLE_TOOLBAR_LEFT);
        ModernButton mb2 = new ModernButton("MIDDLE"); mb2.setButtonStyle(ModernButton.BUTTONSTYLE_TOOLBAR_CENTER);
        ModernButton mb3 = new ModernButton("RIGHT"); mb3.setButtonStyle(ModernButton.BUTTONSTYLE_TOOLBAR_RIGHT);
        
        
        getContentPane().add(panel);
        panel.add(new JLabel("TEST1"), BorderLayout.PAGE_START);
        panel.add(mb1, BorderLayout.LINE_START);
        panel.add(mb2, BorderLayout.CENTER);
        panel.add(mb3, BorderLayout.LINE_END);
        //panel.add(mb2, BorderLayout.LINE_END);
        panel.add(new JLabel("TEST1"), BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestButtons t = new TestButtons();
    }

}
