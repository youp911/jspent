/**
 * 
 */
package org.ranjith.swing;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import org.ranjith.jspent.JSpent;

import com.sun.xml.bind.v2.runtime.reflect.Lister.Pack;


/**
 * @author ranjith
 *
 */
public class TestPanel extends JPanel {
	public TestPanel() {
		setLayout(new BorderLayout());
		setOpaque(false);
		ToolBarButton leftButton = new ToolBarButton(0);
		ToolBarButton rightButton = new ToolBarButton(2);

		URL resource1 = TestPanel.class.getResource("images/resultset_previous.png");
		//leftButton.setIcon(new ImageIcon(resource));
		URL resource2 = TestPanel.class.getResource("images/resultset_next.png");
		//rightButton.setIcon(new ImageIcon(resource));
		add(new JLabel(new ImageIcon(resource1)),BorderLayout.LINE_START);
		JLabel monthLabel = new JLabel("September 2007");
		monthLabel.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
		add(monthLabel);
		add(new JLabel(new ImageIcon(resource2)),BorderLayout.LINE_END);
	}
}
