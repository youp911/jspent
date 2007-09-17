/**
 * 
 */
package org.ranjith.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.ranjith.TestFrame;
import org.ranjith.plugin.PluginInfo;
import org.xml.sax.SAXException;

/**
 * @author XR1CTSO
 *
 */
public class PluginManager {
    private static List plugins;
    public PluginManager() {
    }
    public static List savingsPluginList(String file) {
        try{
            if(plugins == null)
                loadSavingsPlugins();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return plugins;
    }
    
    private static void loadSavingsPlugins() throws IOException, SAXException {
       plugins = new ArrayList();
       Digester digester = DigesterLoader.createDigester(PluginManager.class.getResource("../../../plugins-config/digester-rules.xml"));
       PluginManager pm = (PluginManager) digester.parse(PluginManager.class.getResource("../../../plugins-config/savings-plugins.xml").getFile());
    }
    public void addPlugin(PluginInfo p) {
        plugins.add(p);
    }
    
    public static void main(String[] args) {
        List l = PluginManager.savingsPluginList("");
        System.out.println(l);
    }
}
