/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * @author XR1CTSO
 *
 */
public class BlackComboBoxUI extends BasicComboBoxUI {
    public static final BlackComboBoxUI INSTACE = new BlackComboBoxUI();
    protected void installDefaults()
    {
        super.installDefaults();

        comboBox.setOpaque(false);
        //comboBox.setBorder(new EmptyBorder(0,0,0,0));
        comboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
    
    protected JButton createArrowButton()
    {
        java.net.URL vUrl = this.getClass().getResource("images/arrow_down.png");
        ImageIcon vIcon = new ImageIcon(vUrl);
        JButton vButton = new JButton(vIcon);
        
        vButton.setBorder(new EmptyBorder(0,0,0,0));
        return vButton;
    }
}
