package org.ranjith.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicLabelUI;

public class EmbossedLabelUI extends BasicLabelUI {
	static {
		labelUI = new EmbossedLabelUI();
	}
	@Override
	protected void paintEnabledText(JLabel l, Graphics g, String s, int textX,
			int textY) {
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setColor(Color.WHITE);
        BasicGraphicsUtils.drawString(g2, s, 0, textX, textY+1);
        g2.setColor(SwingRConstants.EMBOSS_FOREGROUND_COLOR);
        BasicGraphicsUtils.drawString(g2, s, 0, textX, textY);
		g2.dispose();
	}

}
