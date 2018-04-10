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

public class SectionFeedTableModel extends AbstractTableModel
{
    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    String[][] timeSlot = new String[][]{
        {"08:00","09:05","010:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40",""}
    };
    String[] columnNames;

    SectionFeed[][] sections = new SectionFeed[9][5];
    

    public SectionFeedTableModel(SectionFeed[][] sections) 
    {
        this.sections = sections;
    }
    
    

    
    public Class getColumnClass(int columnIndex) { return SectionFeed.class; }
    public int getColumnCount() { return DayTimeSlot.length; }
    public String getColumnName(int columnIndex) { return DayTimeSlot[columnIndex]; }
    public int getRowCount() { return 15; }
    public Object getValueAt(int rowIndex, int columnIndex) { return sections[rowIndex][columnIndex]; }
    public boolean isCellEditable(int columnIndex, int rowIndex) { return true; }
    
        
    
}
