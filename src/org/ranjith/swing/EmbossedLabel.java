package org.ranjith.swing;


import javax.swing.JLabel;
/**
 * A label with embossed text.
 * @author ranjith
 *
 */
public class EmbossedLabel extends JLabel {
    /**
     * Create label with text
     * @param text
     */
	public EmbossedLabel(String text) {
		super(text);
		setUI(new EmbossedLabelUI());
	}

	/**
	 * Create label with text and alignment
	 * @param text
	 * @param horizontalAlignment
	 */
	public EmbossedLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setUI(new EmbossedLabelUI());
	}

}