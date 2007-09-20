/**
 * 
 */
package org.ranjith.swing;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * @author ranjith
 *
 */
public class FlatComboBox extends JComboBox {

	public FlatComboBox() {
		super();
		buildUI();
	}

	public FlatComboBox(ComboBoxModel arg0) {
		super(arg0);
		buildUI();
	}

	public FlatComboBox(Object[] arg0) {
		super(arg0);
		buildUI();
	}

	public FlatComboBox(Vector<?> arg0) {
		super(arg0);
		buildUI();
	}
    private void buildUI() {
        //getUI().uninstallUI(this);
        setUI(FlatCombBoxUI.INSTANCE);
    }
}
