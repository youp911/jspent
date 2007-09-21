/**
 * 
 */
package org.ranjith.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * @author XR1CTSO
 * A list cell renderer with icon and text on it.
 * The focuse/selection color behavior tries to mimic
 * the left side list seen in Apple's iTunes UI.
 */
public class IconLabelListCellRenderer implements ListCellRenderer {
   private int padding = 0;
   public IconLabelListCellRenderer() {
       this(0);
   }
   /**
    * Padding is the units to pad at the leading of list item
    * (can be used to show a hierarchy) default is zero
    * @param padding
    */
   public IconLabelListCellRenderer(int padding) {
       this.padding = padding;
   }
 
    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int cellIndex, boolean isSelected, boolean hasFocus) {

        IconListItem listItem = (IconListItem)value;

        JPanel panel = null;
        JLabel itemLabel = new JLabel(listItem.getText(),listItem.getIcon(),JLabel.LEFT);
        itemLabel.setFont(SwingRConstants.DEFAULT_TEXT_FONT);
        if(isSelected) {
            if(hasFocus) {
                panel = getGradientPanel(new Color(0x5B92D5),new Color(0x1C58AE), new Color(0x1C58AE));
                
            }else {
                panel = getGradientPanel(new Color(0xA1B0CF),new Color(0x7185AB), new Color(0x91A0C0));            }
            
            itemLabel.setForeground(Color.WHITE);
        }else{
            panel = new JPanel();
            itemLabel.setForeground(Color.BLACK);
        }
        JLabel spacerLabel = new JLabel("  ");
        spacerLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        
        panel.setLayout(new BorderLayout());
        panel.setOpaque(isSelected);
        panel.add(spacerLabel,BorderLayout.LINE_START);
        panel.add(itemLabel,BorderLayout.CENTER);

        return panel;
    }
    private JPanel getGradientPanel(Color startColor, Color endColor, Color borderColor) {
        JPanel panel;
        panel = new SimpleGradientPanel(startColor,endColor);
        panel.setBorder(BorderFactory.createLineBorder(borderColor));
        
        return panel;
    }
    
}
