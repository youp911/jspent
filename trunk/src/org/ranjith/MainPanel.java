/**
 * 
 */
package org.ranjith;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import org.ranjith.swing.QTable;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.CardLayout;

/**
 * @author XR1CTSO
 *
 */
public class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel actionPanel = null;
    private JButton addButton = null;
    private JScrollPane jScrollPane = null;
    protected QTable qTable = null;
    /**
     * This is the default constructor
     */
    public MainPanel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(350, 247);
        this.setLayout(new BorderLayout());
        this.add(getActionPanel(), BorderLayout.SOUTH);
        this.add(getJScrollPane(), BorderLayout.CENTER);
    }

    /**
     * This method initializes actionPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getActionPanel() {
        if (actionPanel == null) {
            actionPanel = new JPanel();
            actionPanel.setLayout(new BorderLayout());
            actionPanel.add(getAddButton(), BorderLayout.EAST);
        }
        return actionPanel;
    }

    /**
     * This method initializes addButton	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText("Add New");
            addButton.setName("addButton");
        }
        return addButton;
    }

    /**
     * This method initializes jScrollPane	
     * 	
     * @return javax.swing.JScrollPane	
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getQTable());
        }
        return jScrollPane;
    }

    /**
     * This method initializes qTable	
     * 	
     * @return org.ranjith.swing.QTable	
     */
    private QTable getQTable() {
        if (qTable == null) {
            qTable = new QTable();
        }
        return qTable;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
