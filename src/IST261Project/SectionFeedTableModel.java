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
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00"},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00"},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40"}
    };
    public String[] columnNames = new String[43];

    SectionFeed[][] sections;
    
    public SectionFeedTableModel()
    {
        
    }

    public SectionFeedTableModel(SectionFeed[][] sections) 
    {
        this.sections = sections;
        int counter = 1;
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < timeSlot[i].length; j++) {
                columnNames[counter] = "<html>" + days[i] + "<br>"+ timeSlot[i][j] + "</html>";
                counter++;
            }
            counter++;
        }
    }
    
    

    
    public Class getColumnClass(int columnIndex) { return SectionFeed.class; }
    public int getColumnCount() { return columnNames.length; }
    public String getColumnName(int columnIndex) { return columnNames[columnIndex]; }
    public int getRowCount() { return 15; }
    public Object getValueAt(int rowIndex, int columnIndex) { return sections[rowIndex][columnIndex].Output; }
    public boolean isCellEditable(int columnIndex, int rowIndex) { return true; }
    
        
    
}
