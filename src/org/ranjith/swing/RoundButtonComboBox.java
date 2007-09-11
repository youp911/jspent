/**
 * 
 */
package org.ranjith.swing;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.ComponentUI;

/**
 * @author XR1CTSO
 *
 */
public class RoundButtonComboBox extends JComboBox {

    public RoundButtonComboBox() {
        super();
        buildUI();
    }

    public RoundButtonComboBox(ComboBoxModel model) {
        super(model);
        buildUI();
    }

    public RoundButtonComboBox(Object[] items) {
        super(items);
        buildUI();
    }

    public RoundButtonComboBox(Vector<?> items) {
        super(items);
        buildUI();
    }

    private void buildUI() {
        //getUI().uninstallUI(this);
        setUI(BlackComboBoxUI.INSTACE);
    }
    
}
