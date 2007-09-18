/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * @author XR1CTSO
 * A list cell renderer with icon and text on it.
 */
public class IconListItemRenderer extends JLabel implements ListCellRenderer {

   public IconListItemRenderer() {
       super();
       setOpaque(false);
   }
 
    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int cellIndex, boolean isSelected, boolean hasFocus) {

        IconListItem listItem = (IconListItem)value;
        setIcon(listItem.getIcon());
        setAlignmentY(SwingConstants.LEFT);
        setText(" " + listItem.getText());
        if(isSelected) {
            setOpaque(true);
            setBackground(new Color(0x2F6ABA));
            setForeground(Color.WHITE);
        }else{
            setOpaque(false);
            setForeground(Color.BLACK);
        }
        return this;
    }
}
