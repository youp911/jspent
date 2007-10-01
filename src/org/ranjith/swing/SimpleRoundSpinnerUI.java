/**
 * 
 */
package org.ranjith.swing;

import java.awt.Component;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;

/**
 * A spinner UI over ride.
 * @author ranjith
 *
 */
public class SimpleRoundSpinnerUI extends BasicSpinnerUI {
	public static final SimpleRoundSpinnerUI INSTANCE = new SimpleRoundSpinnerUI();
	private JButton previousButton;
	private JButton nextButton;
	@Override
	protected void installDefaults() {
		super.installDefaults();
		spinner.setBorder(new SimpleRoundBorder());
	}

	  /**
	   * Install the UI delegate for the given component.
	   *
	   * @param component The component for which to install the UI delegate.
	   */
	  public void installUI (JComponent component)
	  {
	    super.installUI(component);
	    nextButton.addActionListener(SwingUtilities.getUIActionMap(spinner).get("increment"));
	    nextButton.addMouseListener((MouseListener) SwingUtilities.getUIActionMap(spinner).get("increment"));
	    previousButton.addActionListener(SwingUtilities.getUIActionMap(spinner).get("decrement"));
	    previousButton.addMouseListener((MouseListener) SwingUtilities.getUIActionMap(spinner).get("decrement"));
	  }
	@Override
	protected Component createNextButton() {
        java.net.URL vUrl = this.getClass().getResource("images/resultset_next_up.png");
        ImageIcon vIcon = new ImageIcon(vUrl);
        nextButton = new ToolBarButton(2);
        nextButton.setIcon(vIcon);
        setButtonProperties(nextButton);
        return nextButton;		
	}

	@Override
	protected Component createPreviousButton() {
        java.net.URL vUrl = this.getClass().getResource("images/resultset_previous_down.png");
        ImageIcon vIcon = new ImageIcon(vUrl);
        previousButton = new ToolBarButton(2);
        previousButton.setIcon(vIcon);
        setButtonProperties(previousButton);
        return previousButton;
	}
	
	private void setButtonProperties(JButton button) {
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);
	}

}
