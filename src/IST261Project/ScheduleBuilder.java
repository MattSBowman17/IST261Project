/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kylesmith
 */
public class ScheduleBuilder {
    
    MySQLDBConnector mySQL = new MySQLDBConnector();
    SectionFeed[][] mySF = new SectionFeed[15][15];

    public ScheduleBuilder() {
        try {
            mySQL.connectToDatabase("istdata.bk.psu.edu","3306","kds5314","berks6599","ctg5117");
            new SectionGUI(mySF,mySQL).setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ScheduleBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args) {
        ScheduleBuilder mySB = new ScheduleBuilder();
    }
}
