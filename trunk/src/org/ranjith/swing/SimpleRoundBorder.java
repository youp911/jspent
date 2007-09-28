/**
 * 
 */
package org.ranjith.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

/**
 * @author ranjith
 * 
 */
public class SimpleRoundBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
            int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);
        
        //dirty hack to make the round rect proper.
        g2d.drawRoundRect(x, y, width-1, height-3, 30, 30);
        // g2d.dispose();
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(3, 3, 3, 3);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 3;
        return insets;
    }

}
