/**
 * 
 */
package org.ranjith.plugin;

/**
 * A plugin manager that will load all classes
 * that implement the Plugin interface.
 * @author ranjith
 *
 */
public class PluginManager {
	/** singleton instance */
	private static PluginManager pm = null;
	
	/**
	 * Returns the only instance of plugin manager.
	 * @return plugin manager.
	 */
	public static PluginManager getInstance() {
		if(pm == null) {
			pm = new PluginManager();
		}
		return pm;
	}
	
	/**
	 * Private Constructor.
	 */
	private PluginManager(){
		load();
	}
	
	/**
	 * Load the plugin data. Here, original classes are not loaded.
	 * Instead, go through the plugins directory, for each jar in 
	 * the directory, parse the manifest file, and create plugin
	 * meta data, store in hashmap against type. Add the jar to 
	 * the system classpath.
	 */
	private static synchronized void load() {
		
	}
    
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
