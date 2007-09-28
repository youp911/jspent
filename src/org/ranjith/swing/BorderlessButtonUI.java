package org.ranjith.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * An implementation of a JButton UI delegate that supresses the button border.
 * <p/>
 * Copyright (C) 2005-2006 by Jon Lipsky
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software d
 * istributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class BorderlessButtonUI extends BasicButtonUI
{
    // ------------------------------------------------------------------------------------------------------------------
    //  Constructors and getter/setter methods
    // ------------------------------------------------------------------------------------------------------------------

    public BorderlessButtonUI()
    {
    }

    // ------------------------------------------------------------------------------------------------------------------
    //  Custom installation methods
    // ------------------------------------------------------------------------------------------------------------------

    protected void installDefaults(AbstractButton b)
    {
        super.installDefaults(b);

        b.setBorder(new EmptyBorder(0, 0, 0, 0));
        b.setOpaque(false);
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new BorderlessButtonUI();
    }

    // ------------------------------------------------------------------------------------------------------------------
    //  Custom painting methods
    // ------------------------------------------------------------------------------------------------------------------

    protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect)
    {
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2d = (Graphics2D) g;
        ButtonModel model = b.getModel();
        Icon icon = b.getIcon();
        Icon tmpIcon = null;

        if (icon == null)
        {
            return;
        }

        if (model.isPressed() && model.isArmed())
        {
            tmpIcon = (Icon) b.getPressedIcon();
            if (tmpIcon != null)
            {
                // revert back to 0 offset
                clearTextShiftOffset();
            }
        }
        else if (b.isRolloverEnabled() && model.isRollover())
        {
            if (model.isSelected())
            {
                tmpIcon = (Icon) b.getRolloverSelectedIcon();
            }
            else
            {
                tmpIcon = (Icon) b.getRolloverIcon();
            }
        }
        else if (model.isSelected())
        {
            tmpIcon = (Icon) b.getSelectedIcon();
        }

        if (tmpIcon != null)
        {
            icon = tmpIcon;
        }

        Composite vComposite = g2d.getComposite();
        if (!b.isEnabled())
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        }

        if (model.isPressed() && model.isArmed())
        {
            icon.paintIcon(c, g, iconRect.x + getTextShiftOffset(), iconRect.y + getTextShiftOffset());
        }
        else
        {
            icon.paintIcon(c, g, iconRect.x, iconRect.y);
        }

        if (!b.isEnabled())
        {
            g2d.setComposite(vComposite);
        }

    }
    // ------------------------------------------------------------------------------------------------------------------
    //  Overwridden methods from the superclass(es)
    // ------------------------------------------------------------------------------------------------------------------
}