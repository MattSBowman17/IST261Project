/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import javax.swing.table.AbstractTableModel;



/**
 *
 * @author kds5314
 */

public class ScheduleFeedTableModel extends AbstractTableModel
{
    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    ScheduleFeed[][] sections = new ScheduleFeed[9][5];
    

    public ScheduleFeedTableModel(ScheduleFeed[][] sections) 
    {
        this.sections = sections;
    }

    
    public Class getColumnClass(int columnIndex) { return ScheduleFeed.class; }
    public int getColumnCount() { return 5; }
    public String getColumnName(int columnIndex) { return days[columnIndex]; }
    public int getRowCount() { return 9; }
    public Object getValueAt(int rowIndex, int columnIndex) { return sections[rowIndex][columnIndex]; }
    public boolean isCellEditable(int columnIndex, int rowIndex) { return true; }
    
        
    
}
