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
    String[] days = new String[]{"MWF","TR"};
    String[][] timeSlot = new String[][]
    {
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40"},
        {"09:05","10:35","13:35","15:05","16:35"}
    };
    
    public String[] columnNames = new String[15];

    SectionFeed[][] sections;
    
    public SectionFeedTableModel()
    {
        int counter = 1;
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < timeSlot[i].length; j++) {
                columnNames[counter] = "<html>" + days[i] + "<br>"+ timeSlot[i][j] + "</html>";
                counter++;
            }
            counter++;
        }
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
    
    

    
    @Override
    public Class getColumnClass(int columnIndex) { return SectionFeed.class; }
    @Override
    public int getColumnCount() { return columnNames.length; }
    @Override
    public String getColumnName(int columnIndex) { return columnNames[columnIndex]; }
    @Override
    public int getRowCount() { return 15; }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { return sections[rowIndex][columnIndex].Output; }
    @Override
    public boolean isCellEditable(int columnIndex, int rowIndex) { return true; }
    
        
    
}
