/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;

import javax.swing.JToggleButton;
import javax.swing.plaf.ComponentUI;

public class ModernToggleButton extends JToggleButton {
    public static final int BUTTONSTYLE_TOOLBAR_STANDALONE = 0;
    public static final int BUTTONSTYLE_TOOLBAR_RIGHT = 1;
    public static final int BUTTONSTYLE_TOOLBAR_CENTER = 2;
    public static final int BUTTONSTYLE_TOOLBAR_LEFT = 3;
    
    private static final Color fgColor = new Color(0x454545);
    
    private int buttonStyle = BUTTONSTYLE_TOOLBAR_STANDALONE;
    
    public ModernToggleButton() {
        super();
        buildUI();
    }
    public ModernToggleButton(String text) {
        super(text);
        buildUI();
    }
    protected void buildUI() {
        setBorderPainted(false);
        setForeground(fgColor);
        setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        setOpaque(false);
        getUI().uninstallUI(this);
        ComponentUI ui = ModernToggleButtonUI.createUI(this);
        setUI(ui);        
    }
    /**
     * @return the buttonStyle
     */
    public int getButtonStyle() {
        return buttonStyle;
    }
    /**
     * @param buttonStyle the buttonStyle to set
     */
    public void setButtonStyle(int buttonStyle) {
        this.buttonStyle = buttonStyle;
    }  
}
