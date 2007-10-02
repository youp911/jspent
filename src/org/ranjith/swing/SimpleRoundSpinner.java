/**
 * 
 */
package org.ranjith.swing;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

/**
 * A round spinner with simple round border and
 * some custom buttons.
 * @author ranjith
 *
 */
public class SimpleRoundSpinner extends JSpinner {
	
	/**
	 * 
	 */
	public SimpleRoundSpinner() {
	buildUI();
	}

	/**
	 * @param spinnerModel
	 */
	public SimpleRoundSpinner(SpinnerModel spinnerModel) {
		super(spinnerModel);
		buildUI();
	}
	
    private void buildUI() {
        //getUI().uninstallUI(this);
        setUI(SimpleRoundSpinnerUI.INSTANCE);
    }

}
