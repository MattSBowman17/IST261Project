/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Kyle
 */
public class SectionGUI extends JPanel
{
    public SectionGUI(SectionFeed[][] SFIn, MySQLDBConnector myS) throws ClassNotFoundException, SQLException 
    {

        SectionFeed.getRooms(SFIn, myS);
        SectionFeed.fillSections(SFIn, myS);

        JTable table = new JTable(new SectionFeedTableModel(SFIn));
        FrozenTablePane FTP = new FrozenTablePane(table, 1);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setRowHeight(60);
        table.setShowGrid(true);
        add(FTP);
        //add(new JScrollPane(table, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
 
        
    }
}
