package org.ranjith.swing;


import javax.swing.JLabel;

public class EmbossedLabel extends JLabel {

	public EmbossedLabel(String text) {
		super(text);
		setUI(new EmbossedLabelUI());
	}

	public EmbossedLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setUI(new EmbossedLabelUI());
	}

}