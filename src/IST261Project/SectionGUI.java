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
import static javax.swing.ScrollPaneConstants.*;

/**
 *
 * @author Kyle
 */
public class SectionGUI extends JFrame
{
    public SectionGUI(SectionFeed[][] SFIn, MySQLDBConnector myS) throws ClassNotFoundException, SQLException 
    {
        super("Interactive Table Cell Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        

        SectionFeed.getRooms(SFIn, myS);
        
        //SectionFeed.addSection(new SectionFeed("IST311", "Bowers","08:00", "MWF"),SFIn);
        //SectionFeed.addSection(new SectionFeed("IST301", "Maurer","10:10", "MW"),SFIn);


        JTable table = new JTable(new SectionFeedTableModel(SFIn));
        FrozenTablePane FTP = new FrozenTablePane(table, 1);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setRowHeight(60);
        table.setShowGrid(true);
        add(FTP);
        //add(new JScrollPane(table, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
 
        
    }
}
