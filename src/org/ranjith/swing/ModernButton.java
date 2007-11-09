package org.ranjith.swing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;

/**
 * @author ranjith
 * A modern button, pretty much like OS X 10.5's toolbar buttons.
 *
 */
public class ModernButton extends JButton {
    
    public static final int BUTTONSTYLE_TOOLBAR_STANDALONE = 0;
    public static final int BUTTONSTYLE_TOOLBAR_RIGHT = 1;
    public static final int BUTTONSTYLE_TOOLBAR_CENTER = 2;
    public static final int BUTTONSTYLE_TOOLBAR_LEFT = 3;
    
    private static final Color fgColor = new Color(0x454545);
    
    private int buttonStyle = BUTTONSTYLE_TOOLBAR_STANDALONE;
    
    public ModernButton() {
        super();
        buildUI();
    }
    public ModernButton(String text) {
        super(text);
        buildUI();
    }
    protected void buildUI() {
        setBorderPainted(false);
        setForeground(fgColor);
        setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        setOpaque(false);
        getUI().uninstallUI(this);
        ComponentUI ui = ModernButtonUI.createUI(this);
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
