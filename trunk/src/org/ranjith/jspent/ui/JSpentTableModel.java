/**
 * 
 */
package org.ranjith.jspent.ui;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.swing.QTableModel;

/**
 * @author ranjith
 *
 */
public class JSpentTableModel extends QTableModel {
    public static final int HIDDEN_INDEX = 5;
    static Logger LOG = Logger.getLogger("JSpentTableModel");
    
    public JSpentTableModel(List rows, String[] colNames, String[] colProps) {
        super(rows, colNames, colProps);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return (col != HIDDEN_INDEX);
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        super.setValueAt(value, rowIndex, columnIndex);
        //if an id'd expense, update it.
        Expense expense = (Expense) getRows().get(rowIndex);
        if(expense.getId() != 0) {
            ExpenseService.update(expense);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public boolean hasEmptyRow() {
        if (getRowCount() == 0) return false;
        Expense expense =  (Expense)getRows().get(getRowCount() - 1);
        if(isEmpty(expense)) {
           return true;
        }
        else return false;
    }
    
    //TODO: move this to expense service.
    private boolean isEmpty(Expense expense) {
        return (expense.getId() == 0 &&
                expense.getAmount() == 0f &&
                expense.getCategory() == null &&
                expense.getDate() == null &&
                expense.getNotes() == null &&
                expense.getSubCategory() == null);
    }

    public void addEmptyRow() {
        LOG.info("Adding empty row.");
        Expense e = new Expense();
        e.setDate(new Date());
        getRows().add(e);
        fireTableRowsInserted(
                getRowCount() - 1,
                getRowCount() - 1);
    }    
}
