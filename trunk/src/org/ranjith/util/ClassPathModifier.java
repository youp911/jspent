/**
 * 
 */
package org.ranjith.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * This method is heavily based on a protected method in URLClassLoader,
 * which is accessed by reflection. This might fail at some point. Then,
 * one must use a custom class loader to load classes. 
 * This is based on posts on java sun forum.
 * @author ranjith
 */
public class ClassPathModifier {
	private static final Class[] parameters = new Class[]{URL.class};
	 
	public static void addFile(String s) {
		File f = new File(s);
		addFile(f);
	}
	
	/**
	 * Adds a file to classpath
	 * @param f file to be added
	 */
	public static void addFile(File f) {
		try {
			addURL(f.toURI().toURL());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a URL to current classpath.
	 * @param url url
	 */
	public static void addURL(URL u) {	
		URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
		try {
			
			Method method = URLClassLoader.class.getDeclaredMethod("addURL",parameters);
			method.setAccessible(true);
			method.invoke(sysloader,new Object[]{u});
			System.out.println("Dynamically added " + u.toString() + " to classLoader");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
