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
public class SimpleRoundComboBox extends JComboBox {

    public SimpleRoundComboBox() {
        super();
        buildUI();
    }

    public SimpleRoundComboBox(ComboBoxModel model) {
        super(model);
        buildUI();
    }

    public SimpleRoundComboBox(Object[] items) {
        super(items);
        buildUI();
    }

    public SimpleRoundComboBox(Vector<?> items) {
        super(items);
        buildUI();
    }

    private void buildUI() {
        //getUI().uninstallUI(this);
        setUI(SimpleRoundComboBoxUI.INSTACE);
    }
    
}
