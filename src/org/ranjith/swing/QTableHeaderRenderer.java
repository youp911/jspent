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
import java.awt.geom.Rectangle2D;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * An "iTunes(R)" like table header
 * @author ranjith
 *
 */
public class QTableHeaderRenderer extends SimpleGradientPanel implements TableCellRenderer{
    
    
    protected JTable table;
    protected Object value;
    protected boolean isSelected;
    protected boolean hasFocus;
    protected int row;
    protected int column;

    public static final int LEFTJUSTIFICATION = 0;
    public static final int RIGHTJUSTIFICATION = 1;
    
    public static final Color selectedTopStartColor = new Color(0xD0E0F4);
    public static final Color selectedTopEndColor = new Color(0x8FBBE8);
    public static final Color selectedBottomStartColor = new Color(0x6AAAEB);
    public static final Color selectedBottomEndColor = new Color(0xB8FAFF);

    public static final Color unselectedTopStartColor = Color.WHITE;
    public static final Color unselectedTopEndColor = new Color(0xF2F2F2);
    public static final Color unselectedBottomStartColor = new Color(0xE8E8E8);
    public static final Color unselectedBottomEndColor = Color.WHITE;

    public static final Color borderMiddleColor = new Color(0x666666);
    public static final Color borderVerticalColor = new Color(150, 150, 150, 150);
    
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

        return this;
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color topStartColor = isSelected ? selectedTopStartColor : unselectedTopStartColor;
        Color topEndColor = isSelected ? selectedTopEndColor : unselectedTopEndColor;
        Color bottomStartColor = isSelected ? selectedBottomStartColor : unselectedBottomStartColor;
        Color bottomEndColor = isSelected ? selectedBottomEndColor : unselectedBottomEndColor;

        java.awt.Dimension vSize = getSize();

        int h = vSize.height;
        int w = vSize.width;

        int h2 = h / 2;
        int h4 = h / 4;

        g2.setPaint(new GradientPaint(0, 0, topStartColor, 0, h4, topEndColor));
        g2.fillRect(0, 0, w, h2);

        g2.setColor(topEndColor);
        g2.setPaint(new GradientPaint(0, h4 + 1, topStartColor, 0, h2, topEndColor));

        g2.setPaint(new GradientPaint(0, h2, bottomStartColor, 0, h - 2, bottomEndColor));
        g2.fillRect(0, h2, w, h - 2);

        g2.setColor(borderMiddleColor);
        g2.drawLine(0, h - 1, w, h - 1);

        boolean isLast = column == table.getColumnCount() - 1;
        if (!isLast)
        {
            g2.setColor(borderVerticalColor);
            g2.drawLine(w - 1, 0, w - 1, h - 1);
        }

        String vText = getText(value);
        System.out.println(vText + " - " + isSelected);
        if (vText != null)
        {
            Rectangle2D vRectangle = g.getFontMetrics().getStringBounds(vText,g);

            int sx;

            if (justification == LEFTJUSTIFICATION)
            {
                sx = 5;
            }
            else
            {
                sx = w-((int)vRectangle.getWidth()+5);
            }
            int sy = h - (h-(int)vRectangle.getHeight()) / 2 - g.getFontMetrics().getDescent();

            g.setColor(Color.BLACK);
            g.drawString(vText, sx, sy);
        }
    }
    
    public Dimension getPreferredSize()
    {
        Dimension vDimension = super.getPreferredSize();
        vDimension.height = getFont().getSize() + 5;
        return vDimension;
    }
    
    private String getText(Object value) {
        return value==null?"":value.toString();
    }
    
    // ------------------------------------------------------------------------------------------------------------------
    // The following methods override the defaults for performance reasons
    // ------------------------------------------------------------------------------------------------------------------


    public void validate()
    {
    }

    public void revalidate()
    {
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
    }

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue)
    {
    }    
}
