/**
 * 
 */
package org.ranjith.plugin;

import javax.swing.JComponent;

/**
 * @author XR1CTSO
 *
 */
public interface SavingsPlugin {
    public void setId(String id);
    public String getId();
    public JComponent getUIComponent();
    public String getDisplayName();
    public void setDisplayName(String displayName);
    public boolean onDone();
}
