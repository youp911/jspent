package org.ranjith.swing;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.ranjith.util.DataTypeUtil;
import org.ranjith.util.MathTool;

/**
 * A "Quick Table" Model that will create table model
 * based on collection of objects. The Column headers and types needs to be
 * passed while initializing the object. This class depends on Apache bean-utils
 * for bean/property methods. Currently this model is only &quote;Read only&quote;.
 * i.e; You can not edit resulting cells.
 * @author ranjith
 */
public class QTableModel extends AbstractTableModel {

    /** column headers to be displayed */
    private String[] colNames;

    /** array specifying bean properties in order to retrieve from bean */
    private String[] colProps;

    /** actual data */
    private List rows;

    /** a local cache to quickly return column class without inspecting */
    private Class[] colClassesCache;

    /**
     * Create a quick table model based on specified list of data, array of
     * column headers and underlying object properties.
     * @param colNames  column headers corresponding to each colProps element
     * @param colProps  an arary of fields of the underlying data object
     *                  that needs to appear in the table. This is the XXX
     *                  part in the getXXX method. For example: to display
     *                  value returned by getAge(), you may use "age" as the
     *                  colProps element.
     */
    public QTableModel(List rows, String[] colNames, String[] colProps) {
    	if(rows == null) {
    		throw new IllegalArgumentException("parameter for rows not initialized.");
    	}
        this.rows = rows;
        this.colNames = colNames;
        this.colProps = colProps;
        this.colClassesCache = new Class[colProps.length];
    }

    /**
     * Return number of columns
     * @return number of columns
     */
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * Return total data list size.
     * @return return number of rows
     */
    public int getRowCount() {
        return (rows == null?0:rows.size());
    }

    /**
     * Returns column name for specified column index.
     * @param column column index for which column name label is required.
     * @return       column name
     */
    public String getColumnName(int column) {
        return colNames[column];
    }

    /**
     * Returns column class.
     * @param columnIndex index for which column class is required.
     * @return            column class.
     */
    public Class getColumnClass(int columnIndex) {
        if (rows == null || rows.isEmpty()) {
            return super.getColumnClass(columnIndex);
        }
        if (colClassesCache[columnIndex] != null) {
            return colClassesCache[columnIndex];
        }
        return getPropertyType(columnIndex);
    }

    /**
     * Returns value in a cell
     * @param row row of the cell
     * @param col column of the cell
     * @return    cell value as an object.
     */
    public Object getValueAt(int row, int col) {
        Object value = null;
        if(rows != null) {
	        try {
	            value = PropertyUtils.getProperty(rows.get(row), colProps[col]);
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	            throw new IllegalArgumentException("getter for property for column: " + colProps[col] + " is not public");
	        } catch (InvocationTargetException e) {
	            e.printStackTrace();
	        } catch (NoSuchMethodException e) {
	            e.printStackTrace();
	            throw new IllegalArgumentException("getter for property for column: " + colProps[col] + " is not found.");
	        }
        }
        return value;
    }

    /**
     * Set value in a cell.
     * @param vlaue       value as an object
     * @param rowIndex    row index for the cll
     * @param columnIndex column index for the cell.
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            PropertyUtils.setProperty(rows.get(rowIndex), colProps[columnIndex], value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("setter for property for column: " + colProps[columnIndex] + "is not public");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("setter for property for column: " + colProps[columnIndex] + "is not found.");
        }
    }

    /**
     * Return the list of rows (underlying data)
     * @return   a list of rows.
     */
    public List getRows() {
        return rows;
    }

    /**
     * Set the list of rows (underlying objects)
     * @param rows list of rows
     */
    public void setRows(List rows) {
        this.rows = rows;
    }
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
    /**
     * Add values in a specified column.
     * @param columnIndex the column index of the column to be summed.
     * @return            sum as a Number
     */
    public Number sum(int columnIndex) {
        double result = 0;

        try {
            for (Iterator rowsItr = rows.iterator(); rowsItr.hasNext();) {
                Object property = PropertyUtils.getProperty(rowsItr.next(), colProps[columnIndex]);
                Number value = MathTool.toNumber(property);
                if (value != null) {
                    result += value.doubleValue();
                }
            }
            return new Double(result);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Removes a row from model and updates table.
     * @param selectedRow row index
     */
    public void removeRow(int rowIndex) {
        this.rows.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * gets the given property's Type. 
     * @param columnIndex
     * @return
     */
    private Class getPropertyType(int columnIndex) {
        try {
            PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(rows.get(0), colProps[columnIndex]);
            colClassesCache[columnIndex] = DataTypeUtil.getWrapperType(PropertyUtils.getPropertyType(rows.get(0), colProps[columnIndex]));
            return colClassesCache[columnIndex];
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return super.getColumnClass(columnIndex);
        }
    }



}
