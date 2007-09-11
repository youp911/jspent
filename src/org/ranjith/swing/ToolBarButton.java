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
public class ToolBarButton extends JButton {
    public ToolBarButton(int position) {
        super();
        buildUI(position);
    }
    public ToolBarButton(String text,int position) {
        super(text);
        buildUI(position);
    }
    protected void buildUI(int position) {
        setBorderPainted(false);
        setOpaque(false);
        getUI().uninstallUI(this);
        ComponentUI ui = ToolBarButtonUI.createUI(this,position);
        setUI(ui);
        setForeground(Color.WHITE);        
    }    
}
