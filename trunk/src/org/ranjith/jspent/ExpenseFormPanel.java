/**
 * 
 */
package org.ranjith.jspent;

import java.net.URL;

import javax.swing.ImageIcon;

import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.swing.IconLabelListCellRenderer;
import org.ranjith.swing.IconListItem;

/**
 * @author ranjith
 * A UI Pannel with controls to add a new 
 * expense information.
 */
public class ExpenseFormPanel extends javax.swing.JPanel {
    
    /** Creates new form ExpenseFormPanel */
    public ExpenseFormPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">
    private void initComponents() {

        categoryLabel = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox(ExpenseService.EXPENSE_CATEGORIES);
        subCategoryLabel = new javax.swing.JLabel();
        subCategoryComboBox = new javax.swing.JComboBox(ExpenseService.getExpenseSubCategories());
        dateLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        amountTextField = new javax.swing.JTextField();
        notesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        currencyComboBox = new javax.swing.JComboBox(getCurrencyItemArray());

        categoryLabel.setText("Category:");

        subCategoryLabel.setText("Sub-Category:");

        dateLabel.setText("Date:");

        amountLabel.setText("Amount:");

        notesLabel.setText("Notes:");

        notesTextArea.setColumns(20);
        notesTextArea.setRows(5);
        jScrollPane1.setViewportView(notesTextArea);

        saveButton.setText("Save");

        doneButton.setText("Done");
        currencyComboBox.setRenderer(new IconLabelListCellRenderer());
        
        //Generated using mattisse GUI designer from NB 6.0. DONOT edit beyond this point.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(notesLabel)
                    .addComponent(dateLabel)
                    .addComponent(categoryLabel)
                    .addComponent(amountLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(saveButton)
                        .addComponent(subCategoryLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(amountTextField)
                            .addComponent(dateTextField)
                            .addComponent(subCategoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(categoryComboBox, 0, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(doneButton)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLabel)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subCategoryLabel)
                    .addComponent(subCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountLabel)
                    .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notesLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );
        categoryComboBox.grabFocus();
    }// </editor-fold>

	private Object[] getCurrencyItemArray() {
		Object[] currencyListItems = new Object[3];
        URL resource = JSpent.class.getResource("icons/money_dollar.png");
        currencyListItems[0] = new IconListItem(new ImageIcon(resource),"Dollars");	
        resource = JSpent.class.getResource("icons/money_euro.png");
        currencyListItems[1] = new IconListItem(new ImageIcon(resource),"Euros");
        resource = JSpent.class.getResource("icons/money_pound.png");
        currencyListItems[2] = new IconListItem(new ImageIcon(resource),"Pounds");	        
        return currencyListItems;
	}

	// Variables declaration - do not modify
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JComboBox currencyComboBox;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton doneButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox subCategoryComboBox;
    private javax.swing.JLabel subCategoryLabel;
    // End of variables declaration
    
	public void setDoneButtonListener(BackActionListener backActionListener) {
		doneButton.addActionListener(backActionListener);
	}
    
}
