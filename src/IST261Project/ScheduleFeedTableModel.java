/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 *
 * @author kds5314
 */

public class ScheduleFeedTableModel extends AbstractTableModel
{
    List feeds;
    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};;

    public ScheduleFeedTableModel(List feeds) 
    {
        this.feeds = feeds;
    }

    
    public Class getColumnClass(int columnIndex) { return ScheduleFeed.class; }
    public int getColumnCount() { return 5; }
    public String getColumnName(int columnIndex) { return days[columnIndex]; }
    public int getRowCount() { return (feeds == null) ? 0 : feeds.size(); }
    public Object getValueAt(int rowIndex, int columnIndex) { return (feeds == null) ? null : feeds.get(rowIndex); }
    public boolean isCellEditable(int columnIndex, int rowIndex) { return true; }
    
        
    
}
