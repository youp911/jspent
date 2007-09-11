package org.ranjith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.ranjith.swing.EmbossedLabel;
import org.ranjith.swing.GlassToolBar;
import org.ranjith.swing.QTable;
import org.ranjith.swing.RoundButton;
import org.ranjith.swing.SimpleGradientPanel;
import org.ranjith.swing.SwingRConstants;
import org.ranjith.swing.ToolBarButton;

/*
 *  $Id:$
 */
public class TestFrame extends JFrame {

    QTable table = null;
    public TestFrame() {
        super("Test frame");
        List expenses = getExpenses();
        String[] cols = {"Type", "Date", "Sub Type", "Amount Spent"};
        String[] props = {"category", "date", "subCategory", "amount"};
        getContentPane().setLayout(new BorderLayout());
        

        JPanel rightPanel = getTablePane(expenses, cols, props);
        JScrollPane categoryScrollPane = getOptionsPane();

        JSplitPane splitPane = getSplitPane(rightPanel, categoryScrollPane);
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
        ToolBarButton cb = new ToolBarButton("Test2",1);
        ToolBarButton rb = new ToolBarButton("Test3",2);
        URL resource = TestFrame.class.getResource("../../icons/money_add.png");
        lb.setIcon(new ImageIcon(resource,"Money"));
        rb.setToolTipText("Save the details");
        toolBar.add(lb);
        toolBar.add(cb);
        toolBar.add(rb);
        toolBar.addSeparator();
        
        topGradientPanel.add(toolBar,BorderLayout.PAGE_START);
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
        table.addMouseListener(new TestAdapter());
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
        SimpleGradientPanel actionPanel = new SimpleGradientPanel(new Color(0x505866),new Color(0x7B8596));
        RoundButton rb1 = new RoundButton("Add Expense");
        RoundButton rb2 = new RoundButton("Add Income");
        RoundButton rb3 = new RoundButton("Close");
        actionPanel.add(rb1);
        actionPanel.add(rb2);
        actionPanel.add(rb3);
        return actionPanel;
    }

    private JScrollPane getOptionsPane() {
        JList optionsList = new JList(new String[]{"  Expenses", "  Incomes"});
        optionsList.setBackground(SwingRConstants.PANEL_DEEP_BACKGROUND_COLOR);
        JScrollPane categoryScrollPane = new JScrollPane(optionsList);
        JViewport viewport = new JViewport();
        viewport.setView(getHeader());
        categoryScrollPane.setColumnHeader(viewport);
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

    class TestAdapter extends MouseAdapter {

        public TestAdapter() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Number n = table.sum(table.getColumnModel().getColumnIndexAtX(e.getX()));
            System.out.println(n);
        }
    }

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