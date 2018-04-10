/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Kyle
 */
public class ScheduleGUIArray extends JFrame
{
    public ScheduleGUIArray() 
    {
        super("Interactive Table Cell Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        
        ScheduleFeedCellComponent[][] courses = new ScheduleFeedCellComponent[10][5];
        
        courses[1][1] = new ScheduleFeedCellComponent("IST311", "Bowers","10:30");
        courses[1][2] = new ScheduleFeedCellComponent("IST261", "Xue","1:35");
        String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
        
        JTable table = new JTable(courses, days);

        //List feeds = new ArrayList();
        //feeds.add(new ScheduleFeed("IST311", "Bowers","10:30", "Monday"));
        //feeds.add(new ScheduleFeed("IST261", "Xue","1:35", "Wednesday"));

        //JTable table = new JTable(new ScheduleFeedTableModel(feeds));
        table.setDefaultRenderer(ScheduleFeed.class, new ScheduleFeedCellRenderer());
        table.setDefaultEditor(ScheduleFeed.class, new ScheduleFeedCellEditor());
        table.setRowHeight(60);
        add(new JScrollPane(table));
  }

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScheduleGUIArray().setVisible(true);
            }
        });
    }


}
