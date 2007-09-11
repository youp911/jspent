package org.ranjith.swing;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;

public class RoundButton extends JButton {
    public RoundButton() {
        super();
        buildUI();
    }
    public RoundButton(String text) {
        super(text);
        buildUI();
    }
    private void buildUI(){
        //No border/no box
        setBorderPainted(false);
        setOpaque(false);
        getUI().uninstallUI(this);
        ComponentUI ui = RoundButtonUI.createUI(this);
        setUI(ui);
        setForeground(Color.WHITE);
    }
}
