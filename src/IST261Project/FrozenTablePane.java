/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

/**
 *
 * @author kds5314
 *
 * Adapted from http://fahdshariff.blogspot.sg/2010/02/freezing-columns-in-jtable.html
 */
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
 
/**
 *
 * @author Kyle
 * 
 * 
 */

/** Freezes the First Column of a JTable */
public class FrozenTablePane extends JScrollPane{
 
    /**
     *
     * @param table The JTable to freeze columns from.
     * @param colsToFreeze The number of columns to freeze from the table.
     */
    public FrozenTablePane(JTable table, int colsToFreeze){
    super(table);
 
    TableModel model = table.getModel();
 
    //create a frozen model
    DefaultTableModel frozenModel = new DefaultTableModel(model.getRowCount(),colsToFreeze);
    
    //populate the frozen model
    for (int i = 0; i < model.getRowCount(); i++) {
      for (int j = 0; j < colsToFreeze; j++) {
        String value = (String) model.getValueAt(i, j);
        frozenModel.setValueAt(value, i, j);
      }
    }
    //frozenModel.setColumnIdentifiers(String[] rooms = new String{"Rooms"});
    //create frozen table
    JTable frozenTable = new JTable(frozenModel);
 
    //remove the frozen columns from the original table
    for (int j = 0; j < colsToFreeze; j++) {
      table.removeColumn(table.getColumnModel().getColumn(0));
    }
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 
    //format the frozen table
    JTableHeader header = table.getTableHeader();
    frozenTable.setBackground(header.getBackground());
    frozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    frozenTable.setEnabled(false);
    frozenTable.setRowHeight(60);
 
 
    //set frozen table as row header view
    JViewport viewport = new JViewport();
    viewport.setView(frozenTable);
    viewport.setPreferredSize(frozenTable.getPreferredSize());
    setRowHeaderView(viewport);
    setCorner(JScrollPane.UPPER_LEFT_CORNER, frozenTable.getTableHeader());
  }
}