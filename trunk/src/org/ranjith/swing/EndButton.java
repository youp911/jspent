/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;

/**
 * @author XR1CTSO
 *
 */
public class EndButton extends JButton {
    public EndButton() {
        super();
    }
    public EndButton(String text) {
        super(text);
        buildUI();
    }
    private void buildUI(){
        //No border/no box
        setBorderPainted(false);
        getUI().uninstallUI(this);
        ComponentUI ui = EndButtonUI.createUI(this);
        setUI(ui);
        
        setForeground(Color.WHITE);
        //putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, new Boolean(true));
    }    
}
