package org.ranjith.jspent.ui;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.ranjith.jspent.Application;
import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.RowSelectionActionListener;
import org.ranjith.jspent.action.SavingsTypeListener;
import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.plugin.PluginInfo;
import org.ranjith.plugin.PluginManager;
import org.ranjith.swing.QTable;
import org.ranjith.swing.QTableHeaderRenderer;
import org.ranjith.swing.RoundButton;
import org.ranjith.swing.SimpleGradientPanel;
import org.ranjith.swing.SimpleRoundComboBox;
import org.ranjith.swing.SwingRConstants;

public class UIFactory {
    private static final Icon SMALL_OK_ICON = new ImageIcon(UIFactory.class
            .getResource(Application.getResourceBundle().getString(
                    "small.ok.icon")));
    private static final Icon SMALL_WARN_ICON = new ImageIcon(UIFactory.class
            .getResource(Application.getResourceBundle().getString(
                    "small.warn.icon")));

    public static final String[] cols = {
        Application.getResourceBundle().getString("table.col.type"),
        Application.getResourceBundle().getString("table.col.subtype"),
        Application.getResourceBundle().getString("table.col.date"),
        Application.getResourceBundle().getString("table.col.amount"),
        Application.getResourceBundle().getString("table.col.notes")
                                        };
    public static final String[] props = { "category", "subCategory", "date",
            "amount", "notes" };
    private static UIFactory INSTANCE = null;
    
    private JSpent jSpent = null;

    public  static PluginManager pluginManager = PluginManager.getInstance();
    
    static Logger LOG = Logger.getLogger("UIFactory");
    
    public static UIFactory getInstance(JSpent application) {
        if (INSTANCE == null) {
            INSTANCE = new UIFactory(application);
        }
        return INSTANCE;
    }

    private UIFactory() {

    }

    private UIFactory(JSpent app) {
        this.jSpent = app;
    }

    public QTable createDataTable() {
        QTable table = new QTable();
        // table.setPreferredWidth(2, 20);
        table.setIsAlternateRowHightLighted(true);
        table.setGridColor(SwingRConstants.TABLE_GRID_COLOR);
        table.setBorder(SwingRConstants.EMPTY_BORDER);
        table.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        table.getTableHeader().setDefaultRenderer(new QTableHeaderRenderer());
        table.getTableHeader().setFont(SwingRConstants.DEFAULT_HEADER_FONT);
        table
                .setSelectionBackground(SwingRConstants.DEFAULT_SELECTION_BACKGROUND_COLOR);
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new RowSelectionActionListener(
                jSpent));
        
        //TODO refactor.
        table.setSelectionModel(selectionModel);
        table.setSurrendersFocusOnKeystroke(true);
        return table;
    }

    public void updateExpenseDataTable(QTable table, int monthNumber) {
        List expenses = getExpenses(monthNumber);
        JSpentTableModel tableModel = new JSpentTableModel(expenses, cols, props);
        if (!tableModel.hasEmptyRow()) {
            //todo: SAVE?
            //tableModel.addEmptyRow();
        }
        tableModel.addTableModelListener(new InteractiveTableModelListener(table));
        table.setQTableModel(tableModel);
        TableColumn hidden = table.getColumnModel().getColumn(4);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        
        hidden.setCellRenderer(new InteractiveRenderer(JSpentTableModel.HIDDEN_INDEX,tableModel));
        
        // ordering this is important. We should have data.
        table.setCellRenderer(0, new CategoryRenderer());
        
        table.getColumnModel().getColumn(0).setCellEditor(new DropDownCellEditor(ExpenseService.EXPENSE_CATEGORIES));
        table.getColumnModel().getColumn(1).setCellEditor(new DropDownCellEditor(ExpenseService.getExpenseSubCategories()));
        
        table.setCellRenderer(3, new CurrencyRenderer());
    }

    /**
     * Creates and returns a Savings - add form.
     */
    public SimpleGradientPanel createAddSavingsForm() {

        SimpleGradientPanel addSavingsForm = new SimpleGradientPanel(new Color(
                0x505866), new Color(0x7B8596));

        JPanel typeComboPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        jSpent.setCenterPanel(centerPanel);
        jSpent.setButtonPanel(buttonPanel);
        GroupLayout gl = new GroupLayout(addSavingsForm);
        addSavingsForm.setLayout(gl);
        gl.setHorizontalGroup(gl.createSequentialGroup().addGroup(
                gl.createParallelGroup().addComponent(typeComboPanel)
                        .addComponent(centerPanel).addComponent(buttonPanel)));
        gl.setVerticalGroup(gl.createParallelGroup().addComponent(
                typeComboPanel).addComponent(centerPanel).addComponent(
                buttonPanel));
        typeComboPanel.setOpaque(false);
        JLabel label1 = new JLabel(Application.getResourceBundle().getString("prompt.choose.savingstype"));
        // label1.setForeground(Color.WHITE);
        typeComboPanel.add(label1);

        List pluginList = pluginManager.getPluginInfoList(PluginManager.PLUGIN_TYPE_SAVINGS_KEY);

        SimpleRoundComboBox savingsTypeCombo = new SimpleRoundComboBox();
        savingsTypeCombo.addItem("");
        savingsTypeCombo.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        for (Iterator iterator = pluginList.iterator(); iterator.hasNext();) {
            PluginInfo plugin = (PluginInfo) iterator.next();
            savingsTypeCombo.addItem(plugin);
        }
        savingsTypeCombo.addActionListener(new SavingsTypeListener(jSpent,
                pluginList));

        typeComboPanel.add(savingsTypeCombo);
        RoundButton cancelButton = new RoundButton("Cancel");
        cancelButton.addActionListener(new BackActionListener(jSpent));
        typeComboPanel.add(cancelButton);
        return addSavingsForm;
    }

    private List getExpenses(int month) {
        return new ExpenseService().getExpenses(month);
    }
    

    // Renderer: currency fields --------------------------------
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

    // Renderer: categories field ------------------------------
    class CategoryRenderer extends DefaultTableCellRenderer {

        @Override
        protected void setValue(Object value) {
            if (value == null) {
                value = new String();
            }
            String category = (String) value;
            setText(category);
            if (category.equals(ExpenseService.EXPENSE_CAT_MANDATORY)) {
                setIcon(SMALL_OK_ICON);
            } else if (category.equals(ExpenseService.EXPENSE_CAT_FLEXIBLE)) {
                setIcon(SMALL_WARN_ICON);
            }
        }
    }
    
    // ---------------------------------------------------------
    class InteractiveRenderer extends DefaultTableCellRenderer {
        protected int interactiveColumn;
        protected JSpentTableModel tableModel;

        public InteractiveRenderer(int interactiveColumn,
                JSpentTableModel tableModel) {
            this.interactiveColumn = interactiveColumn;
            this.tableModel = tableModel;
        }

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            Component c = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                if ((tableModel.getRowCount() - 1) == row
                        && !tableModel.hasEmptyRow()) {
                    //TODO:refactor
                    Expense expense = (Expense) tableModel.getRows().get(row);
                    ExpenseService.saveExpense(expense);
                    tableModel.addEmptyRow();
                    LOG.info("Validating and saving data at this point.");
                }
                // TODO: refactor
                int lastrow = tableModel.getRowCount();
                LOG.info("Last row was -  " + lastrow);
                LOG.info("Column counted - " + tableModel.getColumnCount());
                if (row == lastrow - 1) {
                    table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
                } else {
                    table.setRowSelectionInterval(row + 1, row + 1);
                }

                table.setColumnSelectionInterval(0, 0);
            }

            return c;
        }
    }
    
    // -------------------------------------------------------------------
    public class InteractiveTableModelListener implements TableModelListener {
        QTable table;
        public InteractiveTableModelListener(QTable table) {
            this.table = table;
        }
        public void tableChanged(TableModelEvent evt) {
            if (evt.getType() == TableModelEvent.UPDATE) {
                int column = evt.getColumn();
                int row = evt.getFirstRow();
                LOG.info("Something changed on row: " + row + ", column: " + column );
                if(row > 0) {
                    LOG.info("New value: " + table.getModel().getValueAt(row, column));
                }
                table.setColumnSelectionInterval(column + 1, column + 1);
                table.setRowSelectionInterval(row, row);
            }
        }
    }
    
    // ----------------------
    public class DropDownCellEditor extends DefaultCellEditor {
        public DropDownCellEditor(String[] items) {
            super(new javax.swing.JComboBox(items));
        }
    }

}
