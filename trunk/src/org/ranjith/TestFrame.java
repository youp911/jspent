package org.ranjith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.ranjith.plugin.PluginInfo;
import org.ranjith.plugin.PluginManager;
import org.ranjith.swing.EmbossedLabel;
import org.ranjith.swing.GlassToolBar;
import org.ranjith.swing.IconListItem;
import org.ranjith.swing.IconListItemRenderer;
import org.ranjith.swing.QTable;
import org.ranjith.swing.RoundButton;
import org.ranjith.swing.RoundButtonComboBox;
import org.ranjith.swing.SimpleGradientPanel;
import org.ranjith.swing.SwingRConstants;
import org.ranjith.swing.ToolBarButton;

/*
 *  $Id:$
 */
public class TestFrame extends JFrame {

    private QTable table = null;
    private JSplitPane splitPane;
    String[] cols = {"Type", "Date", "Sub Type", "Amount Spent"};
    String[] props = {"category", "date", "subCategory", "amount"};
    private static Map savingsPluginMap = new HashMap(1);
    private static PluginManager pm = PluginManager.getInstance();
    //savings form
    private JPanel centerPanel,buttonPanel;
    SimpleGradientPanel addSavingsForm;
 
    public TestFrame() {
        super("Test frame");
        List expenses = getExpenses();

        getContentPane().setLayout(new BorderLayout());
        
        JPanel rightPanel = getTablePane(expenses, cols, props);
        JScrollPane categoryScrollPane = getOptionsPane();
        
        splitPane = getSplitPane(rightPanel, categoryScrollPane);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        SimpleGradientPanel topGradientPanel = getTopPanel();
        getContentPane().add(topGradientPanel,BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 640);
    }

    private SimpleGradientPanel getTopPanel() {
        SimpleGradientPanel topGradientPanel = new SimpleGradientPanel();
        topGradientPanel.setLayout(new BorderLayout());
        GlassToolBar toolBar = new GlassToolBar();

        ToolBarButton lb = new ToolBarButton(0);
        ToolBarButton cb = new ToolBarButton(1);
        ToolBarButton rb = new ToolBarButton(2);
        URL resource = TestFrame.class.getResource("../../icons/add.png");
        lb.setIcon(new ImageIcon(resource,"Add New"));
        resource = TestFrame.class.getResource("../../icons/application_form_add.png");
        cb.setIcon(new ImageIcon(resource,"Modify"));
        resource = TestFrame.class.getResource("../../icons/delete.png");
        rb.setIcon(new ImageIcon(resource,"Delete"));
        
        toolBar.add(lb);
        toolBar.add(cb);
        toolBar.add(rb);
        toolBar.addSeparator();
        JPanel panel = new JPanel();
        panel.add(toolBar);
        panel.add(new JComboBox());
        topGradientPanel.add(panel,BorderLayout.PAGE_START);
        return topGradientPanel;
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

        JScrollPane scrollPane = new JScrollPane(table);
        //XXX
        scrollPane.setBorder(null);
        rightPanel.add(scrollPane,BorderLayout.CENTER);
        rightPanel.add(getActionPanel(),BorderLayout.SOUTH);
        return rightPanel;
    }

    private Component getActionPanel() {
        SimpleGradientPanel actionPanel = new SimpleGradientPanel();
        actionPanel.setLayout(new BorderLayout());
        actionPanel.add(new JLabel("Total Expenses Amount $ " + table.sum(3)), BorderLayout.EAST);
        return actionPanel;
    }

    private JScrollPane getOptionsPane() {
        DefaultListModel listModel = new DefaultListModel();
        JList optionsList = new JList(listModel);
        optionsList.setCellRenderer(new IconListItemRenderer());
        
        URL resource = TestFrame.class.getResource("../../icons/money_delete.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),"Expenses"));

        resource = TestFrame.class.getResource("../../icons/money_add.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),"Incomes"));

        resource = TestFrame.class.getResource("../../icons/money.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),"Savings"));

        resource = TestFrame.class.getResource("../../icons/creditcards.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),"Liabilities"));
        
        resource = TestFrame.class.getResource("../../icons/report.png");
        listModel.addElement(new IconListItem(new ImageIcon(resource),"Reports"));

        optionsList.setBackground(SwingRConstants.PANEL_DEEP_BACKGROUND_COLOR);
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
        label.setFont(new Font("Sans-Serif", Font.BOLD, 11));
        label.setOpaque(true);
        label.setBackground(SwingRConstants.PANEL_DEEP_BACKGROUND_COLOR);
        return label;
    }

    private List getExpenses() {
        return new ExpenseService().getExpenses();
    }

    public static void main(String[] args) {
        TestFrame frame = new TestFrame();
        frame.setVisible(true);
    }
    
    public void showAddSavings() {
        //splitPane.remove(1);
        
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
        JLabel label1 = new JLabel("Please Choose a Savings type to begin: ");
        label1.setForeground(Color.WHITE);
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
        cancelButton.addActionListener(new GoBackAction(this));
        typeComboPanel.add(cancelButton);
        
        splitPane.setRightComponent(addSavingsForm);
        splitPane.setDividerLocation(160);
    }
    public void restoreUI() {
        splitPane.setRightComponent(getTablePane(getExpenses(), cols, props));
        splitPane.setDividerLocation(160);
    }
    
    public void setForm(JComponent component) {
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(component,BorderLayout.CENTER);
       
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(new RoundButton("TEsting out"),BorderLayout.NORTH);
    
        
        addSavingsForm.add(centerPanel,BorderLayout.CENTER);
        addSavingsForm.updateUI();
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