/**
 * "AS IS"
 */
package org.ranjith.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @author ranjith
 * QTable is a "Quick table" that can be used to display a List of homogenous
 * Objects on a Swing JTable. The table works based on underlying QTableModel
 * implementation and provides basic sorting features. Also provides a few
 * eyecandy features as alternate row coloring.
 */
public class QTable extends JTable {

    /** Model for the table */
    private QTableModel model = null;

    /** flag to drive alternate row hightlighting */
    private boolean isAlternateRowHightLighted = false;

    /** alternate row color.Defaults to SwingRConstants.ALTERNATE_ROW_COLOR */
    private Color alternateRowColor = SwingRConstants.ALTERNATE_ROW_COLOR;

    /**
     * Empty constructor. Do not use this
     */
    public QTable() {
    }

    /**
     * Create a quick data table with specified parameters.
     * @param rows      list of data objects to be populated in the table.
     * @param colNames  column headers corresponding to each colProps element
     * @param colProps  an arary of fields of the underlying data object
     *                  that needs to appear in the table. This is the XXX
     *                  part in the getXXX method. For example: to display
     *                  value returned by getAge(), you may use "age" as the
     *                  colProps element.
     */
    public QTable(List rows, String[] colNames, String[] colProps) {
        model = new QTableModel(rows, colNames, colProps);
        this.setModel(model);
        this.setAutoCreateColumnsFromModel(false);
        this.setAutoCreateRowSorter(true);
        this.setShowGrid(false);
        this.setGridColor(Color.LIGHT_GRAY);
        //this.setShowVerticalLines(true);
    }

    /**
     * Creates a quick table based on the filled model.
     * @param model
     */
    public QTable(QTableModel model) {
        this.model = model;
        this.setModel(model);
        this.setAutoCreateColumnsFromModel(false);
        this.setAutoCreateRowSorter(true);
        this.setShowGrid(false);
        this.setGridColor(Color.LIGHT_GRAY);
        //this.setShowVerticalLines(true);
    }   
    
    /**
     * Returns sum of values in specified column.
     * @param columnIndex column index of the column to be summed.
     * @return            Sum in a generic Number object.
     */
    public Number sum(int columnIndex) {
        return model.sum(columnIndex);
    }

    /**
     * Returns the alternate row hightlight color
     * @returns  alternate row highlight color
     */
    public Color getAlternateRowColor() {
        return alternateRowColor;
    }

    /**
     * Set the alternate row highlight color
     * @param alternateRowColor alternate row hightlight color
     */
    public void setAlternateRowColor(Color alternateRowColor) {
        this.alternateRowColor = alternateRowColor;
    }

    /**
     * Check if alternate row highlighting is required.
     * @return  true if alternate highlighting is enabled. false otherwise.
     */
    public boolean isIsAlternateRowHightLighted() {
        return isAlternateRowHightLighted;
    }

    /**
     * Set if alternate row highlighting is required.
     * @param isAlternateRowHightLighted true when alternate highlighting
     *                                   is required. false otherwise.
     */
    public void setIsAlternateRowHightLighted(boolean isAlternateRowHightLighted) {
        this.isAlternateRowHightLighted = isAlternateRowHightLighted;
    }
    
    /**
     * Set width of a column to specific integer value.
     * <b>Caution</b> This is implemented for convenience. Please resort to 
     * using getColumnModel(..).getColumn(..).setPreferredWidth(..) if required.
     * @param columnIndex index of the column for which width is specified.
     * @param width       integer value for width.
     */
    public void setPreferredWidth(int columnIndex, int width) {
       getColumnModel().getColumn(columnIndex).setPreferredWidth(width); 
    }
    
    /**
     * Set renderer for cells in a specified coloumn.
     * @param columnIndex index of the column ofr which custom renderer is set
     * @param renderer    custom renderer.
     */
    public void setCellRenderer(int columnIndex, DefaultTableCellRenderer renderer) {
       getColumnModel().getColumn(columnIndex).setCellRenderer(renderer); 
    }
    //--------------------------------------------------------------------------
    // Following code is for painting alternate row hightlight
    //--------------------------------------------------------------------------
    /**
     * Paints empty rows too, after letting the UI delegate do
     * its painting.
     */
    public void paint(Graphics g) {
        super.paint(g);
        paintEmptyRows(g);
    }

    /**
     * Paints the backgrounds of the implied empty rows when the
     * table model is insufficient to fill all the visible area
     * available to us. We don't involve cell renderers, because
     * we have no data.
     */
    protected void paintEmptyRows(Graphics g) {
        final int rowCount = getRowCount();
        final Rectangle clip = g.getClipBounds();
        if (rowCount * rowHeight < clip.height) {
            for (int i = rowCount; i <= clip.height / rowHeight; ++i) {
                g.setColor(colorForRow(i));
                g.fillRect(clip.x, i * rowHeight, clip.width, rowHeight);
            }
        }
    }

    /**
     * Changes the behavior of a table in a JScrollPane to be more like
     * the behavior of JList, which expands to fill the available space.
     * JTable normally restricts its size to just what's needed by its
     * model.
     */
    public boolean getScrollableTracksViewportHeight() {
        if (getParent() instanceof JViewport) {
            JViewport parent = (JViewport) getParent();
            return parent.getHeight() > getPreferredSize().height;
        }
        return false;
    }

    /**
     * Returns the appropriate background color for the given row.
     */
    protected Color colorForRow(int row) {
        return (row % 2 == 0 && isAlternateRowHightLighted) ? alternateRowColor : getBackground();
    }

    /**
     * Shades alternate rows in different colors.
     */
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (isCellSelected(row, column) == false) {
            c.setBackground(colorForRow(row));
            c.setForeground(UIManager.getColor("Table.foreground"));
        } else {
                c.setBackground(getSelectionBackground());
                c.setForeground(getSelectionForeground());
        }
        return c;
    }
    
}
