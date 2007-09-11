/**
 * 
 */
package org.ranjith.swing;

import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 * @author XR1CTSO
 *
 */
public class GlassToolBar extends JToolBar {

    public GlassToolBar() {
        super();
        setDefaults();
    }

    public GlassToolBar(int orientation) {
        super(orientation);
        setDefaults();
    }

    public GlassToolBar(String name, int orientation) {
        super(name, orientation);
        setDefaults();
    }

    public GlassToolBar(String name) {
        super(name);
        setDefaults();
    }
    
    private void setDefaults() {
        setOpaque(false);
        setFloatable(false);
        setBorder(new EmptyBorder(5,5,5,5));        
    }
}
