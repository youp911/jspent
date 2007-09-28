package org.ranjith.jspent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.ranjith.jspent.action.AddNewActionListener;
import org.ranjith.jspent.action.BackActionListener;
import org.ranjith.jspent.action.DeleteActionListener;
import org.ranjith.jspent.action.ModifyActionListener;
import org.ranjith.jspent.action.OptionSelectedActionListener;
import org.ranjith.jspent.action.RowSelectionActionListener;
import org.ranjith.jspent.action.SaveExpenseActionListener;
import org.ranjith.jspent.action.SavingsTypeListener;
import org.ranjith.jspent.data.Expense;
import org.ranjith.jspent.data.ExpenseService;
import org.ranjith.plugin.PluginInfo;
import org.ranjith.plugin.PluginManager;
import org.ranjith.swing.EmbossedLabel;
import org.ranjith.swing.GlassToolBar;
import org.ranjith.swing.IconLabelListCellRenderer;
import org.ranjith.swing.IconListItem;
import org.ranjith.swing.QTable;
import org.ranjith.swing.QTableModel;
import org.ranjith.swing.RoundButton;
import org.ranjith.swing.RoundButtonComboBox;
import org.ranjith.swing.SimpleGradientPanel;
import org.ranjith.swing.SwingRConstants;
import org.ranjith.swing.TestPanel;
import org.ranjith.swing.ToolBarButton;

import com.toedter.calendar.JMonthChooser;

/*
 *  $Id:$
 */
public class JSpent extends JFrame {
    private QTable table = null;
    private JSplitPane splitPane;
    String[] cols = {"Type", "Sub Type","Date", "Amount Spent", "Notes"};
    String[] props = {"category","subCategory", "date", "amount","notes"};
    ToolBarButton addButton = new ToolBarButton(0);
    ToolBarButton modifyButton = new ToolBarButton(1);
    ToolBarButton deleteButton = new ToolBarButton(2);
    private JList optionsList;
    private JPanel filterPanel;
    private static Map savingsPluginMap = new HashMap(1);
    private static PluginManager pm = PluginManager.getInstance();
    
    public static final String EXPENSES = "Expenses";
    public static final String INCOMES = "Incomes";
    public static final String SAVINGS = "Savings";
    public static final String LIABILITIES = "Liabilities";
    public static final String SUMMARY = "Summary";
    //savings form
    private JPanel centerPanel,buttonPanel;
    SimpleGradientPanel addSavingsForm;
 
    public JSpent() {
        super("Test frame");
        List expenses = getExpenses();
        getContentPane().setLayout(new BorderLayout());
        JPanel rightPanel = getTablePane(expenses, cols, props);
        JScrollPane categoryScrollPane = getOptionsPane();
        splitPane = getSplitPane(rightPanel, categoryScrollPane);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        SimpleGradientPanel topGradientPanel = getTopPanel();
        getContentPane().add(topGradientPanel,BorderLayout.PAGE_START);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionsList.requestFocusInWindow();
        setSize(800, 640);
    }

    private SimpleGradientPanel getTopPanel() {
        SimpleGradientPanel topGradientPanel = new SimpleGradientPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        topGradientPanel.setLayout(gridBagLayout);
        
        GlassToolBar toolBar = getToolBar();
        
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        gbConstraints.weightx = 0.5;
        gbConstraints.anchor = GridBagConstraints.PAGE_START;
        topGradientPanel.add(toolBar,gbConstraints);
        
        filterPanel = new JPanel(new BorderLayout());
        filterPanel.setOpaque(false);
        
        EmbossedLabel msgLabel = new EmbossedLabel("Showing records for the month of :",EmbossedLabel.TRAILING);
        msgLabel.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        filterPanel.add(msgLabel,BorderLayout.CENTER);
        
        JMonthChooser monthChooser = new JMonthChooser();
        monthChooser.setBorder(new EmptyBorder(0,0,0,0));
        filterPanel.add(monthChooser,BorderLayout.LINE_END);
        
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.gridx = 2;
        gbConstraints.gridy = 0;
        gbConstraints.anchor = GridBagConstraints.PAGE_END;
        topGradientPanel.add(filterPanel,gbConstraints);
        
        return topGradientPanel;
    }

    private GlassToolBar getToolBar() {
        GlassToolBar toolBar = new GlassToolBar();
        URL resource = JSpent.class.getResource("icons/add.png");
        addButton.setIcon(new ImageIcon(resource,"Add New"));
        addButton.addActionListener(new AddNewActionListener(this));
        resource = JSpent.class.getResource("icons/application_form_add.png");
        modifyButton.setIcon(new ImageIcon(resource,"Modify"));
        modifyButton.addActionListener(new ModifyActionListener(this));
        resource = JSpent.class.getResource("icons/delete.png");
        deleteButton.setIcon(new ImageIcon(resource,"Delete"));
        deleteButton.addActionListener(new DeleteActionListener(this));
        //Not enabled on start up. Enable only when table row is selected.
        modifyButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
        toolBar.add(addButton);
        toolBar.add(modifyButton);
        toolBar.add(deleteButton);
        
        return toolBar;
    }

    private JSplitPane getSplitPane(JComponent scrollPane, JComponent categoryScrollPane) {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(160);
        splitPane.add(scrollPane, JSplitPane.RIGHT);
        splitPane.add(categoryScrollPane, JSplitPane.LEFT);

        return splitPane;
    }

    private JPanel getTablePane(List expenses, String[] cols, String[] props) {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
 
        table = new QTable(expenses, cols, props);
        table.setPreferredWidth(2, 20);
        table.setCellRenderer(3, new CurrencyRenderer());
        table.setIsAlternateRowHightLighted(true);
        //XXX
        table.setBorder(null);
        table.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        table.getTableHeader().setFont(SwingRConstants.DEFAULT_HEADER_FONT);
        table.setSelectionBackground(SwingRConstants.DEFAULT_SELECTION_BACKGROUND_COLOR);
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new RowSelectionActionListener(this));
        table.setSelectionModel(selectionModel);
        JScrollPane scrollPane = new JScrollPane(table);
        //XXX
        scrollPane.setBorder(null);
        rightPanel.add(scrollPane,BorderLayout.CENTER);
        rightPanel.add(getBottomPanel(),BorderLayout.SOUTH);
        return rightPanel;
    }


	private Component getBottomPanel() {
	    GridBagLayout gridBagLayout = new GridBagLayout();
        SimpleGradientPanel bottomPanel = new SimpleGradientPanel();       
        bottomPanel.setLayout(gridBagLayout);
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 0;
        gbConstraints.anchor = GridBagConstraints.CENTER;

        EmbossedLabel totalLabel = new EmbossedLabel("Total Expenses " + NumberFormat.getCurrencyInstance().format(table.sum(3)) + " ");
        totalLabel.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        bottomPanel.add(totalLabel,gbConstraints);
        return bottomPanel;
    }

    private JScrollPane getOptionsPane() {
        DefaultListModel listModel = new DefaultListModel();
        optionsList = new JList(listModel);
        optionsList.setCellRenderer(new IconLabelListCellRenderer(1));
        
        URL resource = JSpent.class.getResource("icons/money_delete.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),EXPENSES));

        resource = JSpent.class.getResource("icons/money_add.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),INCOMES));

        resource = JSpent.class.getResource("icons/money.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),SAVINGS));

        resource = JSpent.class.getResource("icons/creditcards.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),LIABILITIES));
        
        resource = JSpent.class.getResource("icons/report.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),SUMMARY));

        optionsList.setBackground(SwingRConstants.PANEL_DEEP_BACKGROUND_COLOR);
        optionsList.addListSelectionListener(new OptionSelectedActionListener());
        //Start with expense always selected.
        optionsList.setSelectedIndex(0);
        JScrollPane categoryScrollPane = new JScrollPane(optionsList);
        JViewport colHeaderViewPort = new JViewport();
        colHeaderViewPort.setView(getHeader());
                
        categoryScrollPane.setColumnHeader(colHeaderViewPort);
        //XXX
        categoryScrollPane.setBorder(null);
        return categoryScrollPane;
    }

    private Component getHeader() {
        EmbossedLabel label = new EmbossedLabel("CATEGORY");
        label.setFont(SwingRConstants.DEFAULT_HEADER_FONT);
        label.setOpaque(true);
        label.setBackground(SwingRConstants.PANEL_DEEP_BACKGROUND_COLOR);
        return label;
    }

    private List getExpenses() {
        return new ExpenseService().getExpenses(Calendar.getInstance().get(Calendar.MONTH)+1);
    }
    
    private void prepareUIForForm() {
    	setAddToolBarButtonEnabled(false);
    	setModfyToolBarButtonEnabled(false);
    	setDeleteToolBarButtonEnabled(false);
    	filterPanel.setVisible(false);
    	optionsList.setEnabled(false);
    }
    
    /**
     * This needs to be called when we have to add a new
     * expense data.
     */
    public void showExpenseForm() {
        showExpenseForm(null);
    }

    /**
     * This needs to be called when we have to edit an
     * expense data.
     */
    public void showExpenseForm(Expense expense) {
        prepareUIForForm();
        ExpenseFormPanel panel = expense == null? new ExpenseFormPanel():new ExpenseFormPanel(expense);
        splitPane.setRightComponent(panel);
        splitPane.setDividerLocation(160);
        panel.setDoneButtonListener(new BackActionListener(this,panel));
        int mode = (expense == null?SaveExpenseActionListener.ADD_NEW_MODE : SaveExpenseActionListener.UPDATE_MODE);
        panel.setSaveButtonListener(new SaveExpenseActionListener(mode,panel,this));
    }    
    
    public void showAddSavings() {
    	prepareUIForForm();
        addSavingsForm = new SimpleGradientPanel(new Color(0x505866),new Color(0x7B8596));

        JPanel typeComboPanel = new JPanel();
        centerPanel = new JPanel();
        buttonPanel = new JPanel();
        
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
        List pluginList = pm.getPluginInfoList(PluginManager.PLUGIN_TYPE_SAVINGS_KEY);
        
        RoundButtonComboBox savingsTypeCombo = new RoundButtonComboBox();
        savingsTypeCombo.addItem("");
        for (Iterator iterator = pluginList.iterator(); iterator.hasNext();) {
            PluginInfo plugin = (PluginInfo) iterator.next();
            savingsTypeCombo.addItem(plugin);
        }        
        
        savingsTypeCombo.addActionListener(new SavingsTypeListener(this,pluginList));
        
        typeComboPanel.add(savingsTypeCombo);
        RoundButton cancelButton = new RoundButton("Cancel");
        cancelButton.addActionListener(new BackActionListener(this));
        typeComboPanel.add(cancelButton);
        
        splitPane.setRightComponent(addSavingsForm);
        splitPane.setDividerLocation(160);
    }
    public void restoreUI() {
        splitPane.setRightComponent(getTablePane(getExpenses(), cols, props));
        if(table.getSelectedRow() < 0) {
            setModfyToolBarButtonEnabled(false);
            setDeleteToolBarButtonEnabled(false);
        }
        setAddToolBarButtonEnabled(true);
        filterPanel.setVisible(true);
        splitPane.setDividerLocation(160);
        optionsList.setEnabled(true);
    }
    
    public void setForm(JComponent component) {
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(component,BorderLayout.CENTER);
       
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(new RoundButton("TEsting out"),BorderLayout.NORTH);
        
        addSavingsForm.add(centerPanel,BorderLayout.CENTER);
        addSavingsForm.updateUI();
    }
    
    public String getCurrentContext() {
    	Object selectedObject = optionsList.getSelectedValue();
    	return ((IconListItem)selectedObject).getText();
    }
    
    public void setCurrentContext(String context) {
    	if(context.equals(EXPENSES)) {
    	  this.optionsList.setSelectedIndex(0);
    	}else if(context.equals(INCOMES)) {
    		this.optionsList.setSelectedIndex(1);	
    	}else if(context.equals(SAVINGS)) {
    		this.optionsList.setSelectedIndex(2);	
    	}else if(context.equals(LIABILITIES)) {
    		this.optionsList.setSelectedIndex(3);		
    	}else if(context.equals(SUMMARY)){
    		this.optionsList.setSelectedIndex(4);		
    	}
    }
    
    public void setModfyToolBarButtonEnabled(boolean isEnabled) {
        this.modifyButton.setEnabled(isEnabled);
    }
    
    public void setAddToolBarButtonEnabled(boolean isEnabled) {
        this.addButton.setEnabled(isEnabled);
    }
    
    public void setDeleteToolBarButtonEnabled(boolean isEnabled) {
        this.deleteButton.setEnabled(isEnabled);
    }

    public Object getSelectedRowObject() {
        QTableModel model = (QTableModel) table.getModel();
        return model.getRows().get(table.getSelectedRow());
    } 
    
    public void clearTableSelection() {
        table.getSelectionModel().clearSelection();
    } 

    
    /**
     * Removes selected row from the table. But this would not make 
     * changes to underlying datastore.
     */
    public void removeSelectedRowObject() {
        if(table.getSelectedRow() > -1) {
            QTableModel model = (QTableModel) table.getModel();
            model.removeRow(table.getSelectedRow());
        }
    }   
    public static void main(String[] args) {
        JSpent frame = new JSpent();
        frame.setVisible(true);
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




}