package org.ranjith;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.ranjith.swing.QTable;
import org.ranjith.swing.EmbossedLabel;
import org.ranjith.swing.SwingRConstants;

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
        JScrollPane scrollPane = getTablePane(expenses, cols, props);
        JScrollPane categoryScrollPane = getOptionsPane();

        JSplitPane splitPane = getSplitPane(scrollPane, categoryScrollPane);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 640);
    }

    private JSplitPane getSplitPane(JScrollPane scrollPane, JScrollPane categoryScrollPane) {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(160);
        splitPane.add(scrollPane, JSplitPane.RIGHT);
        splitPane.add(categoryScrollPane, JSplitPane.LEFT);

        return splitPane;
    }

    private JScrollPane getTablePane(List expenses, String[] cols, String[] props) {
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
        return scrollPane;
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