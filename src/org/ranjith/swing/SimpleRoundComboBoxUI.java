/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * @author XR1CTSO
 *
 */
public class SimpleRoundComboBoxUI extends BasicComboBoxUI {
    public static final SimpleRoundComboBoxUI INSTACE = new SimpleRoundComboBoxUI();
    protected void installDefaults()
    {
        super.installDefaults();

        comboBox.setOpaque(false);
        comboBox.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    }
    
    protected JButton createArrowButton()
    {
        java.net.URL vUrl = this.getClass().getResource("images/bullet_arrow_down.png");
        ImageIcon vIcon = new ImageIcon(vUrl);
        JButton vButton = new JButton(vIcon);
        vButton.setUI(new BorderlessButtonUI());
        return vButton;
    }
}
