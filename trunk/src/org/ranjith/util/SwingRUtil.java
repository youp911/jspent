package org.ranjith.util;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * A local swing utilities class
 * @author ranjith
 *
 */
public class SwingRUtil {

    public static void showErrorDialog(List errorStrigList, String title,
            Component parentComponent) {
        if (errorStrigList != null || !errorStrigList.isEmpty()) {
            StringBuffer errorMessage = new StringBuffer();
            for (Iterator iterator = errorStrigList.iterator(); iterator
                    .hasNext();) {
                errorMessage.append(iterator.next());
                errorMessage.append("\n");
            }
            JOptionPane.showMessageDialog(parentComponent, errorMessage, title,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
