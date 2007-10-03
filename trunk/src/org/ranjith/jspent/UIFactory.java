package org.ranjith.jspent;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.ranjith.jspent.action.RowSelectionActionListener;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.swing.QTable;
import org.ranjith.swing.SwingRConstants;

public class UIFactory {
    private static final Icon SMALL_OK_ICON = new ImageIcon(UIFactory.class.getResource("icons/okIcon-sm.png"));
    private static final Icon SMALL_WARN_ICON = new ImageIcon(UIFactory.class.getResource("icons/warningIcon.png"));

    public static final String[] cols = {"Type", "Sub Type","Date", "Amount Spent", "Notes"};
    public static final String[] props = {"category","subCategory", "date", "amount","notes"};
    private static UIFactory INSTANCE = null;
    private JSpent jSpent = null;
    
    public static UIFactory getInstance(JSpent application) {
        if(INSTANCE == null) {
            INSTANCE = new UIFactory(application);
        }
        return INSTANCE;
    }
    private UIFactory() {
        
    }
    
    private UIFactory(JSpent app) {
        this.jSpent = app;
    }
    
    public QTable createExpenseTableForMonth(int monthNumber) {
        System.out.println("create..");
        List expenses = getExpenses(monthNumber);
        QTable table = new QTable(expenses, cols, props);
        table.setPreferredWidth(2, 20);
        table.setCellRenderer(0, new CategoryRenderer());
        table.setCellRenderer(3, new CurrencyRenderer());
        table.setIsAlternateRowHightLighted(true);
        table.setGridColor(SwingRConstants.TABLE_GRID_COLOR);
        //XXX
        table.setBorder(SwingRConstants.EMPTY_BORDER);
        table.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        table.getTableHeader().setFont(SwingRConstants.DEFAULT_HEADER_FONT);
        table.setSelectionBackground(SwingRConstants.DEFAULT_SELECTION_BACKGROUND_COLOR);
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new RowSelectionActionListener(jSpent));
        table.setSelectionModel(selectionModel);
        return table;        
    }
    
    private List getExpenses(int month) {
        return new ExpenseService().getExpenses(month);
    }
    
    //-------------------------------------------------------
    class CurrencyRenderer extends DefaultTableCellRenderer {

        @Override
        protected void setValue(Object value) {
            if (value == null) {
                value = new Float(0.0F);
            }
            Float amount = (Float) value;
            setHorizontalAlignment(SwingConstants.RIGHT);
            NumberFormat format = NumberFormat.getCurrencyInstance();
            setText(format.format(amount));
        }
    }
    //-------------------------------------------------------
    class CategoryRenderer extends DefaultTableCellRenderer {

        @Override
        protected void setValue(Object value) {
            if (value == null) {
                value = new String();
            }
            String category = (String)value;
            setText(category);
            if(category.equals(ExpenseService.EXPENSE_CAT_MANDATORY)){
                setIcon(SMALL_OK_ICON);
            }else if(category.equals(ExpenseService.EXPENSE_CAT_FLEXIBLE)){
                setIcon(SMALL_WARN_ICON);
            }
        }
    }
}
