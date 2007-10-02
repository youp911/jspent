/**
 * 
 */
package org.ranjith.plugin;

/**
 * A Plugin meta-data class.
 * @author ranjith
 *
 */
public class PluginInfo {
    private String type;
    private String name;
    private String className;
    /**
     * @return the id
     */
    public String getType() {
        return type;
    }
    /**
     * @param id the id to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
