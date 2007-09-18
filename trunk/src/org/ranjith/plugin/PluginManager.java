/**
 * 
 */
package org.ranjith.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.ranjith.util.ClassPathModifier;
import org.xml.sax.SAXException;

/**
 * A plugin manager that will load all classes
 * that implement the Plugin interface.
 * @author ranjith
 *
 */
public class PluginManager {
	public static String PLUGIN_TYPE_SAVINGS_KEY = "Savings";
	/** singleton instance */
	private static PluginManager pm = null;

	/** map to hold plugin data */
	private static Map pluginTypeInfoMap = new HashMap(1);

	/**
	 * Returns the only instance of plugin manager.
	 * @return plugin manager.
	 */
	public static PluginManager getInstance() {
		if (pm == null) {
			pm = new PluginManager();
		}
		return pm;
	}

	/**
	 * Private Constructor.
	 */
	private PluginManager() {
		load();
	}

	/**
	 * For each file in plugins dir
	 *     if file is File and ends with .jar
	 *        get manifest.xml from jar's root
	 *        parse to get plugin information.
	 *        create pluginInfo and add to MAP
	 *                                MAP {
	 *                                  key(string type) -> List of pluginInfo
	 *                                  key(string type) -> List of pluginInfo
	 *                                }
	 *        add the jar to classpath.
	 *      endif
	 * Load the plugin data. Here, original classes are not loaded.
	 * Instead, go through the plugins directory, for each jar in 
	 * the directory, parse the manifest file, and create plugin
	 * meta data, store in hashmap against type. Add the jar to 
	 * the system classpath.
	 */
	private static synchronized void load() {
		File pluginsDir = new File(System.getProperty("user.dir") + "/plugins");
		try {
			for (File file : pluginsDir.listFiles()) {
				if (file.getName().endsWith(".jar")) {
					savePluginInfo(file);
					addJarToClassPath(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	private static void addJarToClassPath(File file) {
		ClassPathModifier.addFile(file);
	}

	private static void savePluginInfo(File file) throws IOException,
			SAXException {
		List pluginInfoList = getPluginInfoList(file);

		for (Iterator iterator = pluginInfoList.iterator(); iterator.hasNext();) {
			PluginInfo pluginInfo = (PluginInfo) iterator.next();
			Object infoObject = pluginTypeInfoMap.get(pluginInfo.getType());
			List savedPluginInfoList = null; 
			if(infoObject == null) {
				savedPluginInfoList = new ArrayList();
				pluginTypeInfoMap.put(pluginInfo.getType(),savedPluginInfoList);
			}
			savedPluginInfoList.add(pluginInfo);
		}

	}

	private static List getPluginInfoList(File file) throws IOException,
			SAXException {
		JarFile jarFile = new JarFile(file);
		JarEntry jarEntry = jarFile.getJarEntry("plugin-manifest.xml");
		Digester digester = DigesterLoader.createDigester(PluginManager.class
				.getResource("../../../plugins-config/digester-rules.xml"));
		return (List) digester.parse(jarFile.getInputStream(jarEntry));
	}

	public static Map getPluginMap() {
		return pluginTypeInfoMap;
	}
	
	/**
	 * Returns plugin information of loaded plugins in a
	 * list. The returned list will have only specified
	 * "pluginType" of plugins
	 * @param pluginType plugin type.
	 * @return list of plugin information.
	 */
	public static List getPluginInfoList(String pluginType) {
		return (List) pluginTypeInfoMap.get(pluginType);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PluginManager pm = PluginManager.getInstance();
		Map pluginMap = pm.getPluginMap();
		System.out.println(pluginMap);
		
		List savingsPlugins = (List) pluginMap.get("Savings");
		PluginInfo pluginInfo = (PluginInfo) savingsPlugins.get(0);
		Class c = Class.forName(pluginInfo.getClassName());
		Object pluginObject = c.newInstance();
		System.out.println(pluginObject);
		
	}

}
