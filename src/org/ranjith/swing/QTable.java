/**
 * "AS IS"
 */
package org.ranjith.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 * QTable is a &quote;Quick table&quote; that can be used to 
 * display a List of <b>homogeneous</b> Objects on a Swing JTable. 
 * The table works based on underlying QTableModel implementation 
 * and provides basic sorting features. Also provides a few
 * eye-candy features as alternate row coloring(Like in iTunes).
 * @author ranjith
 */
public class QTable extends JTable {

    /** Model for the table */
    private QTableModel model = null;

    /** flag to drive alternate row hightlighting */
    private boolean isAlternateRowHightLighted = false;

    /** alternate row color.Defaults to SwingRConstants.ALTERNATE_ROW_COLOR */
    private Color alternateRowColor = SwingRConstants.ALTERNATE_ROW_COLOR;
    
    /**
     * Empty constructor. Use this constructor only to initialize containers.
     * Provided for compatibility with actual swing component.
     * <b>This is not a favored way to construct Qtable</b>
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
        init(model);
    }
    
    /**
     * Creates a quick table based on the filled model.
     * @param model
     */
    public QTable(QTableModel model) {
        this.model = model;
        init(model);
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
    
	private void init(QTableModel model) {
		this.setModel(model);
        this.setAutoCreateColumnsFromModel(false);
        this.setAutoCreateRowSorter(true);
        this.setShowGrid(false);
	}

    //--------------------------------------------------------------------------
    // Following code is for painting alternate row highlight and column 
    // grid for all rows. Pure UI pleasure.
    //--------------------------------------------------------------------------
    /**
     * Paints empty rows too, after letting the UI delegate do
     * its painting.
     * @param g graphics object
     */
    public void paint(Graphics g) {
        super.paint(g);
        paintEmptyRows(g);
		g.setColor(gridColor);
		paintGridLines(g);
    }
    
    /**
     * To draw grid lines, even if there are no rows in the table.
     * @param g target graphics 
     */
	private void paintGridLines(Graphics g) {
		int x = 0;
		Rectangle clip = g.getClipBounds();
		TableColumnModel vModel = getColumnModel();
		//vModel.getColumnCount()-1 is a dirty hack to avoid
		//drawing line on last column's end.
		for (int i = 0; i < vModel.getColumnCount()-1; i++)
		{
			TableColumn vColumn = vModel.getColumn(i);
			x += vColumn.getWidth();
			if ((x >= clip.x) && (x <= clip.x + clip.width))
			{
				g.drawLine(x - 1, clip.y, x - 1, clip.y + clip.height);
			}
		}
	}

    /**
     * Paints the backgrounds for empty rows when the
     * table model does not have data to fill all the visible area
     * available. Cell renderers are not called as there is no data
     * to render.
     * @param g target graphics
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
     * @return scroll-able tracks 
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
     * @return color for odd or even row.
     */
    protected Color colorForRow(int row) {
        return (row % 2 == 0 && isAlternateRowHightLighted) ? alternateRowColor : getBackground();
    }

    /**
     * Prepares cell renderer by querying underlying data model.
     * We check value and selection state of the cell at row, column.
     * @return Componenet
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
    

    /**
     * @see javax.swing.JTable#changeSelection(int, int, boolean, boolean)
     * Prepare for editing when the cell gains focus.
     */
    public void changeSelection(int row, int column, boolean toggle,
            boolean extend) {
        super.changeSelection(row, column, toggle, extend);

        if (editCellAt(row, column))
            getEditorComponent().requestFocusInWindow();
    }
   
    /**
     * @see javax.swing.JTable#prepareEditor(javax.swing.table.TableCellEditor, int, int)
     * Select the text when the cell starts editing
     * a) text will be replaced when you start typing in a cell
     * b) text will be selected when you use F2 to start editing
     * c) caret is placed at end of text when double clicking to start
     * editing
     */
    public Component prepareEditor(TableCellEditor editor, int row,
            int column) {
        Component c = super.prepareEditor(editor, row, column);

        if (c instanceof JTextComponent) {
            ((JTextField) c).selectAll();
        }

        return c;
    }         
    
    /**
     * Get underlying QTable Model.
     * @return
     */
    public QTableModel getQTableModel() {
        return this.model;
    }
    
    /**
     * Set underlying data model. Fires data changed
     * on model and sets sorted column index to -1
     * @param model instance of QTableModel
     */
    public void setQTableModel(QTableModel model) {
        this.model = model;
        init(model);
        model.fireTableDataChanged();
    }    
    
}
