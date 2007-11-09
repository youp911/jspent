/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SortOrder;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableCellRenderer;

/**
 * An "iTunes(R)" like table header. This is a minimal implementation. Column
 * selection etc are not taken care.
 * 
 * @author ranjith
 * 
 */
public class QTableHeaderRenderer extends JPanel implements TableCellRenderer {

    protected JTable table;
    protected Object value;
    protected boolean isSelected;
    protected boolean hasFocus;
    protected int row;
    protected int column;
    boolean isDrawingSortedColumn = false;
    protected int prevSortedColumn = -1;

    public static final int LEFTJUSTIFICATION = 0;
    public static final int RIGHTJUSTIFICATION = 1;

    private SortOrder sortDirection;

    public static final Color selectedTopStartColor = new Color(0xD0E0F4);
    public static final Color selectedTopEndColor = new Color(0x8FBBE8);
    public static final Color selectedBottomStartColor = new Color(0x6AAAEB);
    public static final Color selectedBottomEndColor = new Color(0xB8FAFF);

    public static final Color unselectedTopStartColor = Color.WHITE;
    public static final Color unselectedTopEndColor = new Color(0xF2F2F2);
    public static final Color unselectedBottomStartColor = new Color(0xE8E8E8);
    public static final Color unselectedBottomEndColor = Color.WHITE;

    public static final Color borderMiddleColor = SwingRConstants.LINE_COLOR;
    public static final Color borderVerticalColor = new Color(150, 150, 150,
            150);

    private int justification = LEFTJUSTIFICATION;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        this.table = table;
        this.value = value;
        this.isSelected = isSelected;
        this.hasFocus = hasFocus;
        this.row = row;
        this.column = column;
        setFont(table.getFont());
        //-- get the 'auto sorter's properties.
        List sortKeys = ((QTable)table).getRowSorter().getSortKeys();
        if(sortKeys != null && !sortKeys.isEmpty()) {
        	SortKey sortKey = (SortKey) sortKeys.get(0);
        	this.isDrawingSortedColumn = (column == sortKey.getColumn());
        	sortDirection = sortKey.getSortOrder();
        } else {
        	this.isDrawingSortedColumn = false;
        	sortDirection = SortOrder.UNSORTED;
        }
        return this;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension vSize = getSize();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (isDrawingSortedColumn) {
            g2.setPaint(new GradientPaint(0, 0, new Color(0xC8D4E2), 0,
                    vSize.height, new Color(0x7F95B3)));
        } else {
            g2.setPaint(new GradientPaint(0, 0, new Color(0xEEEEEE), 0,
                    vSize.height, new Color(0xBBBBBB)));
        }
        g2.fillRect(0, 0, vSize.width, vSize.height);

        g2.setColor(borderMiddleColor);
        g2.drawLine(0, vSize.height - 1, vSize.width, vSize.height - 1);

        boolean isLast = column == table.getColumnCount() - 1;
        if (!isLast) {
            g2.setColor(borderVerticalColor);
            g2.drawLine(vSize.width - 1, 0, vSize.width - 1, vSize.height - 1);
        }

        String vText = getText(value);
        if (vText != null) {
            Rectangle2D vRectangle = g2.getFontMetrics().getStringBounds(vText,
                    g2);

            int sx;

            if (justification == LEFTJUSTIFICATION) {
                sx = 5;
            } else {
                sx = vSize.width - ((int) vRectangle.getWidth() + 5);
            }
            int sy = vSize.height
                    - (vSize.height - (int) vRectangle.getHeight()) / 2
                    - g.getFontMetrics().getDescent();
            
            if (isDrawingSortedColumn) {
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawString(vText, sx, sy+1);                
            }
            g2.setColor(new Color(0x202020));
            g2.drawString(vText, sx, sy);
        }
        if (isDrawingSortedColumn) {
            paintIcon(g2, vSize.width, vSize.height);

        }
        g2.dispose();
    }

    private void paintIcon(Graphics2D g2, int width, int height) {
        URL imageURL = (sortDirection == SortOrder.DESCENDING) ? this.getClass()
                .getResource("images/sort_desc.png") : this.getClass()
                .getResource("images/sort_asc.png");

        Icon vIcon = new ImageIcon(imageURL, "S");

        int x = (width - vIcon.getIconWidth());
        int y = (height - vIcon.getIconHeight()) / 2;
        
        //some dirty math to make the icon location correct.
        vIcon.paintIcon(this, g2, x-1, y-2);
    }

    public Dimension getPreferredSize() {
        Dimension vDimension = super.getPreferredSize();
        vDimension.height = getFont().getSize() + 6;
        return vDimension;
    }

    private String getText(Object value) {
        return value == null ? "" : value.toString();
    }

    // ------------------------------------------------------------------------------------------------------------------
    // The following methods override the defaults for performance reasons
    // ------------------------------------------------------------------------------------------------------------------

    public void validate() {
    }

    public void revalidate() {
    }

    protected void firePropertyChange(String propertyName, Object oldValue,
            Object newValue) {
    }

    public void firePropertyChange(String propertyName, boolean oldValue,
            boolean newValue) {
    }
}
