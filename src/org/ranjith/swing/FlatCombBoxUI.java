package org.ranjith.swing;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * A UI delegate to create flat combo appearance.
 * Very useful for table cells.
 * @author ranjith
 *
 */
public class FlatCombBoxUI extends BasicComboBoxUI {
	public static final FlatCombBoxUI INSTANCE = new FlatCombBoxUI();
	@Override
	protected void installDefaults() {
		super.installDefaults();
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setOpaque(false);
	}
	
	@Override
	protected JButton createArrowButton() {
		URL vUrl = this.getClass().getResource("images/bullet_arrow_down.png");
		ImageIcon vIcon = new ImageIcon(vUrl);
		JButton vButton = new JButton(vIcon);
		vButton.setOpaque(false);
		vButton.setBorder(new EmptyBorder(0,0,0,0));
		
		return vButton;
	}
}
