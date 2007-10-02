/**
 * 
 */
package org.ranjith.swing;

import javax.swing.Icon;

/**
 * A list Item with an icon and text.
 * Text can be  returned as the value.
 * @author ranjith
 */
public class IconListItem {
    private Icon icon;
    private String text;
    
    public IconListItem(Icon icon, String text) {
        super();
        this.icon = icon;
        this.text = text;
    }
    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }
    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    
}
