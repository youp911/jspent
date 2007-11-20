/**
 * 
 */
package org.ranjith.swing;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author ranjith
 * 
 */
public class ModernButtonUI extends BasicButtonUI {
    private static final ModernButtonUI INSTANCE = new ModernButtonUI();
    private static final Color BORDER_COLOR = new Color(0x454545);
    private static final Color BORDER_HIGHTLIGHT = new Color(255, 255, 255, 150);
    private static final Color START_COLOR = Color.WHITE;
    //private static final Color END_COLOR = new Color(0xACACAC);
    private static final Color END_COLOR = new Color(0x838383);

    private static final Color PRESSED_START_COLOR = Color.GRAY;
    private static final Color PRESSED_END_COLOR = Color.WHITE;

    public static ComponentUI createUI(JComponent b) {
        return INSTANCE;
    }

    public void paint(Graphics g, JComponent c) {

        AbstractButton vButton = (AbstractButton) c;
        ButtonModel vButtonModel = vButton.getModel();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = c.getWidth();
        int h = c.getHeight();

        int x = 0;
        int y = 0;

        boolean isPressed = vButtonModel.isArmed() && vButtonModel.isPressed();
        boolean isRollover = vButtonModel.isRollover();
        boolean isSelected = vButtonModel.isSelected();
        ModernButton mButton = (ModernButton) vButton;

        int style = mButton.getButtonStyle();

        paintButtonBody(g2d, vButton, x, y, w, h, isPressed, isRollover,
                isSelected, style);
        paintButtonBorder(g2d, x, y, w, h, isPressed, isRollover, isSelected,
                style);
        paintText(g2d, vButton.getText(), vButton.getForeground(), w, h,
                isPressed, isRollover, isSelected);
        g2d.dispose();
    }

    private void paintText(Graphics2D g2d, String text, Color foreground,
            int w, int h, boolean isPressed, boolean isRollover,
            boolean isSelected) {
        // String size
        FontMetrics fm = g2d.getFontMetrics();
        int swid = fm.stringWidth( text );
        int shgt = fm.getHeight();
        
        int x = (w-swid)/2;
        int y = (h - shgt)/2 + fm.getAscent();
        if(isPressed) {
            y +=1;
        }
        g2d.translate( x, y );
        g2d.setColor(foreground);
        int offset =  0;
        g2d.drawString( text, offset, offset );
    }

    private void paintButtonBody(Graphics2D g2, AbstractButton button, int x,
            int y, int w, int h, boolean isPressed, boolean isRollover,
            boolean isSelected, int style) {
        Shape vOldClip = g2.getClip();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // LEFT Shape vButtonShape = new RoundRectangle2D.Double((double) x,
        // (double) y, (double) w + h, (double) h - 2, (double) h * 0.2,
        // (double) h * 0.2);
        Shape vButtonShape = null;
        Shape vOptionalShape = null;
        
        switch (style) {
        case ModernButton.BUTTONSTYLE_TOOLBAR_STANDALONE:
            vButtonShape = new RoundRectangle2D.Double((double) x, (double) y,
                    (double) w, (double) h -2, (double) h * 0.2, (double) h * 0.2);
            break;
        case ModernButton.BUTTONSTYLE_TOOLBAR_LEFT:
            //vButtonShape = new RoundRectangle2D.Double((double) x, (double) y, (double) w, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
            vOptionalShape =     new RoundRectangle2D.Double((double) x , (double) y, (double) w-2, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
            vButtonShape = new Rectangle.Double((double) x + w/2, (double) y, (double) w/2, (double) h - 2);
            break;
        case ModernButton.BUTTONSTYLE_TOOLBAR_RIGHT:
            //vButtonShape = new RoundRectangle2D.Double((double) x, (double) y, (double) w, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
            vOptionalShape = new RoundRectangle2D.Double((double) x - h - 1, (double) y, (double) w + h, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
            vButtonShape = new Rectangle.Double((double) x , (double) y, (double) x, (double) h - 2);
            break;
        case ModernButton.BUTTONSTYLE_TOOLBAR_CENTER :
            vButtonShape = new Rectangle2D.Double((double) x, (double) y, (double) w, (double) h - 2);
            break;
        }

        //g2.setClip(vButtonShape);

        Color startColor = START_COLOR;
        Color endColor = END_COLOR;
        if (isPressed || isSelected) {
            startColor = PRESSED_START_COLOR;
            endColor = PRESSED_END_COLOR;
        }
        Paint vTopPaint = new GradientPaint(x, y, startColor, x, h, endColor);
        
        g2.setPaint(vTopPaint);
        
        g2.fill(vButtonShape);
        if(vOptionalShape != null) {
            g2.fill(vOptionalShape);
        }
        /*
         * Paint vTopPaint = new GradientPaint(0, y + 1, vTopStartColor, 0, y +
         * h / 2 - 1, vTopEndColor); g2.setPaint(vTopPaint); g2.fillRect(x, y +
         * 1, w, h / 2 - 1);
         * 
         * Paint vBottomPaint = new GradientPaint(0, y + h / 2,
         * vBottomStartColor, 0, y + h - 1, vBottomEndColor);
         * g2.setPaint(vBottomPaint); g2.fillRect(x, y + h / 2, w, h / 2 - 1);
         */
        g2.setClip(vOldClip);
        paintIcon(g2, button, isRollover, isSelected);
    }

    private void paintButtonBorder(Graphics2D g, int x, int y, int w, int h,
            boolean isPressed, boolean isRollover, boolean isSelected, int style) {
        Shape vButtonShape = null;
        
        if(style == ModernButton.BUTTONSTYLE_TOOLBAR_STANDALONE) {
            vButtonShape = new RoundRectangle2D.Double((double) x,
                    (double) y, (double) w, (double) h -2, (double) h * 0.2,
                    (double) h * 0.2);
        }else if(style == ModernButton.BUTTONSTYLE_TOOLBAR_LEFT) {
            vButtonShape = new RoundRectangle2D.Double((double) x, (double) y, (double) w + h, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
        }else if(style == ModernButton.BUTTONSTYLE_TOOLBAR_RIGHT) {
            vButtonShape = new RoundRectangle2D.Double((double) x - h - 1, (double) y, (double) w + h, (double) h - 2, (double) h * 0.2, (double) h * 0.2);
        }else if(style == ModernButton.BUTTONSTYLE_TOOLBAR_CENTER) {
            vButtonShape = new Rectangle2D.Double((double) x, (double) y, (double) w, (double) h - 2);
        }
        Shape vOldClip = g.getClip();

        g.setClip(x, y + h - (h / 2), w, y + h);
        g.setColor(BORDER_HIGHTLIGHT); // border highlight
        g.setStroke(new BasicStroke(1f)); // highlight stroke
        g.translate(0, 1);
        g.draw(vButtonShape);

        /*
         * g.setClip(vOldClip); g.setPaint(new GradientPaint(0, y + 1, new
         * Color(0x666666), 0, y + h / 2 - 1, new Color(255, 255, 255, 150)));
         * g.setColor(new Color(255, 255, 255, 150)); //border highlight
         * g.setStroke(new BasicStroke(1f)); //highlight stroke //g.translate(0,
         * 1); g.draw(vButtonShape);
         */

        /*
         * g.setClip(x,y+h-(h/2),w,y+h); g.setColor(new Color(255, 255, 255,
         * 150)); //border highlight g.setStroke(new BasicStroke(2f));
         * //highlight stroke //g.translate(0, 1); g.draw(vButtonShape);
         */

        // g.setClip(vOldClip);
        g.setClip(x, y - 1, w + 1, h + 1);
        // g.setColor(new Color(0x666666)); //border color
        g.setColor(BORDER_COLOR); // border color
        g.setStroke(new BasicStroke(1f)); // border stroke
        g.translate(0, -0.5);
        g.draw(vButtonShape);
        if(style == ModernButton.BUTTONSTYLE_TOOLBAR_LEFT) {
            g.drawLine(x + w, y, x + w, y + h - 3);
        }
        if(style == ModernButton.BUTTONSTYLE_TOOLBAR_RIGHT) {
            //g.drawLine(x, y, x, y + h - 3);
            g.drawLine(x, y + 1, x, y + h - 3);
        }
        
        g.setClip(vOldClip);
    }

    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect,
            Rectangle textRect, Rectangle iconRect) {
        return;
    }
    
    private void paintIcon(Graphics2D g2, AbstractButton b, boolean isRollover,
            boolean isSelected) {
        int x;
        int y;
        Icon vIcon = null;

        if (isSelected && isRollover)
        {
            vIcon = b.getRolloverSelectedIcon();
        }
        else if (isSelected)
        {
            vIcon = b.getSelectedIcon();
        }
        else if (isRollover)
        {
            vIcon = b.getRolloverIcon();
        }

        if (vIcon == null)
        {
            vIcon = b.getIcon();
        }

        if (vIcon != null)
        {
            x = (b.getWidth() - vIcon.getIconWidth()) / 2 + 1;
            y = (b.getHeight() - vIcon.getIconHeight()) / 2;

            Composite vComposite = g2.getComposite();
            if (!b.isEnabled())
            {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
            }

            vIcon.paintIcon(b, g2, x, y);

            if (!b.isEnabled())
            {
                g2.setComposite(vComposite);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //  Sizing Methods
    // ------------------------------------------------------------------------------------------------------------------

    public Dimension getMinimumSize(JComponent c)
    {
        return getPreferredSize(c);
    }

    public Dimension getPreferredSize(JComponent c)
    {
        AbstractButton b = (AbstractButton) c;

        int w = 0;
        int h = 0;
        javax.swing.Icon vIcon = b.getIcon();
        if (vIcon != null)
        {
            w = vIcon.getIconWidth();
            h = vIcon.getIconHeight() + 7;
        } else {
            return super.getPreferredSize(c);
        }

        w += (h * 0.5);

        return new Dimension(w, h);
    }

    public Dimension getMaximumSize(JComponent c)
    {
        return getPreferredSize(c);
    }
}
