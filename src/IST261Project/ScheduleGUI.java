/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.SQLException;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Kyle
 */
public class ScheduleGUI extends JFrame
{
    public ScheduleGUI(ScheduleFeed[][] SFIn, MySQLDBConnector myS) throws ClassNotFoundException, SQLException 
    {
        super("Interactive Table Cell Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);

//      ScheduleFeed.getTimeSlots(SFIn, myS);
        
        ScheduleFeed.addSection(new ScheduleFeed("IST311", "Bowers","08:00", "MWF"),SFIn);
        ScheduleFeed.addSection(new ScheduleFeed("IST301", "Maurer","10:10", "MW"),SFIn);


        JTable table = new JTable(new ScheduleFeedTableModel(SFIn));
        table.setDefaultRenderer(ScheduleFeed.class, new ScheduleFeedCellRenderer());
        table.setDefaultEditor(ScheduleFeed.class, new ScheduleFeedCellEditor());
        table.setRowHeight(60);
        table.setShowGrid(true);
        add(new JScrollPane(table));
 
        
    }
}
