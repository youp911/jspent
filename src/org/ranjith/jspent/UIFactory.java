package org.ranjith.jspent;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.RowSelectionActionListener;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.plugin.PluginInfo;
import org.ranjith.plugin.PluginManager;
import org.ranjith.swing.QTable;
import org.ranjith.swing.QTableModel;
import org.ranjith.swing.RoundButton;
import org.ranjith.swing.SimpleGradientPanel;
import org.ranjith.swing.SimpleRoundComboBox;
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
    
    public QTable createDataTable() {
        QTable table = new QTable();
        //table.setPreferredWidth(2, 20);
        table.setIsAlternateRowHightLighted(true);
        table.setGridColor(SwingRConstants.TABLE_GRID_COLOR);
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
    
    public void updateExpenseDataTable(QTable table,int monthNumber) {
        List expenses = getExpenses(monthNumber);
        QTableModel tableModel = new QTableModel(expenses,cols,props);
        table.setQTableModel(tableModel);
        //ordering this is important. We should have data.
        table.setCellRenderer(0, new CategoryRenderer());
        table.setCellRenderer(3, new CurrencyRenderer());
    }
   
    /**
     * Creates and returns a Savings - add form.
     */
    public SimpleGradientPanel createAddSavingsForm() {

		SimpleGradientPanel addSavingsForm = new SimpleGradientPanel(new Color(0x505866),new Color(0x7B8596));

        JPanel typeComboPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        jSpent.setCenterPanel(centerPanel);
        jSpent.setButtonPanel(buttonPanel);
        GroupLayout gl = new GroupLayout(addSavingsForm);
        addSavingsForm.setLayout(gl);
        gl.setHorizontalGroup(
                gl.createSequentialGroup().addGroup(
                gl.createParallelGroup().addComponent(typeComboPanel).addComponent(centerPanel).addComponent(buttonPanel)
                )
                );
       gl.setVerticalGroup(
               gl.createParallelGroup().addComponent(typeComboPanel).addComponent(centerPanel).addComponent(buttonPanel)
               );
        typeComboPanel.setOpaque(false);
        JLabel label1 = new JLabel("Please Choose a Savings type to begin :");
        //label1.setForeground(Color.WHITE);
        typeComboPanel.add(label1);
        List pluginList = jSpent.pluginManager.getPluginInfoList(PluginManager.PLUGIN_TYPE_SAVINGS_KEY);
        
        SimpleRoundComboBox savingsTypeCombo = new SimpleRoundComboBox();
        savingsTypeCombo.addItem("");
        savingsTypeCombo.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        for (Iterator iterator = pluginList.iterator(); iterator.hasNext();) {
            PluginInfo plugin = (PluginInfo) iterator.next();
            savingsTypeCombo.addItem(plugin);
        }        
        
        //savingsTypeCombo.addActionListener(new SavingsTypeListener(this,pluginList));
        
        typeComboPanel.add(savingsTypeCombo);
        RoundButton cancelButton = new RoundButton("Cancel");
        cancelButton.addActionListener(new BackActionListener(jSpent));
        typeComboPanel.add(cancelButton);
        return addSavingsForm;
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
