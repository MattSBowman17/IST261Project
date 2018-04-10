/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

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
    public ScheduleGUI() 
    {
        super("Interactive Table Cell Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        
        
        //Add New 
        ScheduleFeed[][] sections = new ScheduleFeed[9][5];
        ScheduleFeed.getTimeSlots(sections);
        
        //ScheduleFeed.addSection(new ScheduleFeed("IST311", "Bowers","8:00", "MWF"),sections);
        //ScheduleFeed.addSection(new ScheduleFeed("IST301", "Maurer","10:10", "MW"),sections);
        //sections[8][0] = new ScheduleFeed("IST261", "Xue","1:35", "Wednesday");

        JTable table = new JTable(new ScheduleFeedTableModel(sections));
        table.setDefaultRenderer(ScheduleFeed.class, new ScheduleFeedCellRenderer());
        table.setDefaultEditor(ScheduleFeed.class, new ScheduleFeedCellEditor());
        table.setRowHeight(60);
        table.setShowGrid(true);
        add(new JScrollPane(table));
 
        
  }

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScheduleGUI().setVisible(true);
            }
        });
    }


}
