/**
 * 
 */
package org.ranjith.jspent.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import org.ranjith.jspent.Application;
import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.SaveActionListener;
import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.swing.IconLabelListCellRenderer;
import org.ranjith.swing.IconListItem;
import org.ranjith.swing.RoundButton;
import org.ranjith.util.DataTypeUtil;

import com.toedter.calendar.JDateChooser;

/**
 * A UI Pannel with controls to add a new 
 * expense information
 * @author ranjith.
 */
public class ExpenseFormPanel extends CommonDataPanel {
    /** underlying data object */
    private Expense expenseDataObject = null;
    
    /** save action handler */
    private SaveActionListener saveActionListener = null;
    
    /**
     * Creates new form ExpenseFormPanel 
     **/
    public ExpenseFormPanel() {
        initComponents();
        showAddNew();
    }
    
    /**
     * Creates new form from given data object
     * @param expense
     */
    public ExpenseFormPanel(Expense expense) {
        this.expenseDataObject = expense;
        initComponents();
        setDataObject(expense);
    }
    
    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor (ex; Netbeans).
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">
    private void initComponents() {
        ResourceBundle bundle = Application.getResourceBundle();
        
        categoryLabel = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox(ExpenseService.EXPENSE_CATEGORIES);
        subCategoryLabel = new javax.swing.JLabel();
        subCategoryComboBox = new javax.swing.JComboBox(ExpenseService.getExpenseSubCategories());
        dateLabel = new javax.swing.JLabel();
        dateChooser = new JDateChooser();
        amountLabel = new javax.swing.JLabel();
        amountTextField = new javax.swing.JFormattedTextField(NumberFormat.getNumberInstance());
        notesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        saveButton = new RoundButton();
        doneButton = new RoundButton();
        currencyComboBox = new javax.swing.JComboBox(getCurrencyItemArray());

        categoryLabel.setText(bundle.getString("prompt.category"));

        subCategoryLabel.setText(bundle.getString("prompt.subcategory"));

        dateLabel.setText(bundle.getString("prompt.date"));

        amountLabel.setText(bundle.getString("prompt.amount"));

        notesLabel.setText(bundle.getString("prompt.notes"));

        notesTextArea.setColumns(20);
        notesTextArea.setRows(5);
        jScrollPane1.setViewportView(notesTextArea);

        saveButton.setText(bundle.getString("prompt.save"));
        doneButton.setText(bundle.getString("prompt.done"));
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
                            .addComponent(dateChooser)
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
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
    /**
     * Returns list of currency items for a JList.
     * TODO: Move to util class
     * @return currencyListItems
     */
	private Object[] getCurrencyItemArray() {
	    ResourceBundle bundle = Application.getResourceBundle();
		Object[] currencyListItems = new Object[3];
        URL resource = JSpent.class.getResource(bundle.getString("options.dollar.icon"));
        currencyListItems[0] = new IconListItem(new ImageIcon(resource),bundle.getString("options.dollar"));	
        resource = JSpent.class.getResource(bundle.getString("options.euro.icon"));
        currencyListItems[1] = new IconListItem(new ImageIcon(resource),bundle.getString("options.euro"));
        resource = JSpent.class.getResource(bundle.getString("options.pound.icon"));
        currencyListItems[2] = new IconListItem(new ImageIcon(resource),bundle.getString("options.pound"));	        
        return currencyListItems;
	}

	// Variables declaration - do not modify
    private javax.swing.JLabel amountLabel;
    private javax.swing.JFormattedTextField amountTextField;
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JComboBox currencyComboBox;
    private javax.swing.JLabel dateLabel;
    private JDateChooser dateChooser;
    private RoundButton doneButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JTextArea notesTextArea;
    private RoundButton saveButton;
    private javax.swing.JComboBox subCategoryComboBox;
    private javax.swing.JLabel subCategoryLabel;
    // End of variables declaration
    
	public void setDoneButtonListener(BackActionListener backActionListener) {
		doneButton.addActionListener(backActionListener);
	}
	
	public void setSaveButtonListener(SaveActionListener saveActionListener) {
		saveButton.addActionListener(saveActionListener);
		this.saveActionListener = saveActionListener;
	}
	
	private void setDataObject(Expense expense) {
		this.expenseDataObject = expense;
		this.categoryComboBox.setSelectedItem(expense.getCategory());
		this.subCategoryComboBox.setSelectedItem(expense.getSubCategory());
		this.dateChooser.setDate(expense.getDate());
		this.amountTextField.setText(Float.toString(expense.getAmount()));
		this.notesTextArea.setText(expense.getNotes());
	}

	public void showExpense(Expense expense) {
		
	}

	@Override
	public Object getDataObject() {
	    Expense uiData = new Expense();
	    if(expenseDataObject != null) {
	        uiData.setId(expenseDataObject.getId());
	    }
		if(!DataTypeUtil.isEmptyOrNullString(amountTextField.getText())) {
			try {
			    uiData.setAmount(NumberFormat.getNumberInstance().parse(amountTextField.getText()).floatValue());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
		}
		uiData.setCategory((String)categoryComboBox.getSelectedItem());
		uiData.setSubCategory((String)subCategoryComboBox.getSelectedItem());
		if(dateChooser.getDate() != null){
		    uiData.setDate(dateChooser.getDate());	
		}
		if(!DataTypeUtil.isEmptyOrNullString(notesTextArea.getText())) {
		    uiData.setNotes(notesTextArea.getText());
		}
		return uiData;
	}

	@Override
	public boolean isDirty() {
		Expense e = (Expense) getDataObject();
		return(!getDataObject().equals(this.expenseDataObject));
	}

	@Override
	public void showAddNew() {
		setDataObject(new Expense());
	}

	@Override
	public void fireSaveButtonPressed(ActionEvent e) {
		saveActionListener.actionPerformed(e);
	}

    @Override
    public List getValidationErrors() {
        ResourceBundle bundle = Application.getResourceBundle();
        List errors = new ArrayList();
        if(DataTypeUtil.isEmptyOrNullString(amountTextField.getText())) {
            errors.add(bundle.getString("validation.amount.spent.missing"));
        }
        try{
            NumberFormat.getNumberInstance().parse(amountTextField.getText());    
        }catch(ParseException parseException) {
            errors.add(bundle.getString("validation.amount.spent.nonnumeric"));
        }
        if(DataTypeUtil.isEmptyOrNullString((String)categoryComboBox.getSelectedItem()) ){
            errors.add(bundle.getString("validation.amount.category.notselected"));
        }
        if(DataTypeUtil.isEmptyOrNullString((String)subCategoryComboBox.getSelectedItem()) ){
            errors.add(bundle.getString("validation.amount.subcategory.notselected"));
        }
        return errors;
    }

}

