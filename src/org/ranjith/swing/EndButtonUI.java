package org.ranjith.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;

public class EndButtonUI extends MetalButtonUI {
    private static final EndButtonUI INSTANCE = new EndButtonUI();
    public static ComponentUI createUI(JComponent b) {
        return INSTANCE;
    }  
    protected Color getSelectColor() {
        return new Color(183, 234, 98);
    }
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawEndshape(g2,b.getX(),b.getY(),b.getWidth(),b.getHeight(),Color.BLACK, Color.LIGHT_GRAY,Color.WHITE);
        }
    }
    public void update(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        if (c.isOpaque()) {
            if (b.isContentAreaFilled()) {
                int width = b.getWidth();
                int height = b.getHeight();
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);        
                g.setColor(b.getBackground());
                if (!b.getModel().isArmed() && !b.getModel().isPressed()) {
                    drawEndshape(g2,b.getX(),b.getY(),b.getWidth(),b.getHeight(),
                           Color.GRAY,Color.BLACK,Color.WHITE);
                }
            }
        }
        paint(g, c);
        //Dirty hack to make the borders smooth.
        c.getParent().repaint();
    }    
    
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        return;
    }    
    
    private void drawEndshape(Graphics2D g2,int x, int y, int width, int height, Color gradientStart, Color gradientEnd, Color borderColor){
        int arc = 30;
        
        Paint oldPaint = g2.getPaint();
        //set color
        g2.setColor(new Color(0,0,0,220));
        //set stroke of 3f for border
        g2.setStroke(new BasicStroke(1.0f));
        //set rendering hint for anti alias
        //fill rounded rectangle

        //g2.setPaint(new GradientPaint(1,1,gradientStart,1+height,1+width,gradientEnd));
        g2.setPaint(new GradientPaint(1,1,gradientStart,1,1+width,gradientEnd));
        drawEndButtonShape(g2,1,1,width-3,height-3,arc);
        //g2.fillRect(0, 0, getWidth(), getHeight());
        //g2.fillRoundRect(1,1,width-3,height-3,arc,arc);
        //g2.fillRect(width/2,1,width-3,height-3);
        //draw white rounded rectangle
        g2.setColor(borderColor);
       // g2.drawRoundRect(1,1,width-3,height-3,arc,arc);
        //g2.drawRect(width/2,1,width-3,height-3);
        //g2.drawRoundRect(1,1,width -3,height-3,arc,arc);
        //dispose graphics resource.
        g2.setPaint(oldPaint);
    }
    
    private void drawEndButtonShape(Graphics2D g2, int x, int y, int width, int height,
            int radius) {
        g2.drawLine(x + radius, y, x + width - (radius * 2), y);
        g2.drawArc(x + width - (radius * 2), y, radius * 2, radius * 2, 270, 90);
        g2.drawLine(x + width, y + radius, x + width, y + height - (radius * 2));
        g2.drawArc(x + width - (radius * 2), y + height - (radius * 2), radius * 2, radius * 2, 0, 90);
        g2.drawLine(x + width - (radius * 2), y + height, x + radius, y + height);
        g2.drawArc(x, y + height - (radius * 2), radius * 2, radius * 2, 90, 90);
        g2.drawLine(x, y + height - (radius * 2), x, y + radius);
        g2.drawArc(x, y, radius * 2, radius * 2, 180, 90);
    }
    
}
