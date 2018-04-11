/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kyle
 */

public class SectionFeed {
    String courseName;
    String professorName;
    String classTime;
    String classDay;
    static Room[] rooms;
    static String[][] DayTimeSlot = new String[][]{
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"09:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40",""}
    };

    
    static char[] dayLetter = new char[]{'M','T','W','R','F'};
    static String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};

            
        
    
    
    public SectionFeed(String courseName, String professorName, String classTime, String classDay) 
    {
        this.courseName = courseName;
        this.professorName = professorName;
        this.classTime = classTime;
        this.classDay = classDay;
    }
    
    public static void getRooms(SectionFeed[][] sectionTable, MySQLDBConnector myS) throws SQLException
    {
        Statement stmt = myS.myConnection.createStatement();
        String sql = "SELECT building.Building_Name, room.Room_Number, room.Room_Size, room.Package_Package_ID " +
        "FROM ctg5117.room room, ctg5117.building building " +
        "WHERE room.Building_Building_ID = building.Building_ID";
        ResultSet myRS = stmt.executeQuery(sql);
        int RSLength = 0;
        while(myRS.next())
        {
            RSLength++;
        }
        myRS.first();
        rooms = new Room[RSLength];
        for (int i = 0; i < RSLength; i++) {
            rooms[i] = new Room(myRS.getString(1), myRS.getInt(3), myRS.getInt(4), myRS.getInt(2));
            myRS.next();
        }
        for (int i = 0; i < rooms.length; i++) {
            sectionTable[i][0] = new SectionFeed(String.valueOf(rooms[i].getRoom_Size()),
                    String.valueOf(rooms[i].getRoom_Number()),
                    rooms[i].getBuilding_Name(), "");
            for (int j = 1; j < 39; j++) {
                sectionTable[i][j] = new SectionFeed("","","","");
            }
        }
        
    }
    
    
//    public static void getTimeSlots(SectionFeed[][] sectionTable, MySQLDBConnector myS) throws SQLException
//    { 
//        
//        //myS.connectToDatabase("istdata.bk.psu.edu","3306","kds5314","berks6599","ctg5117");
//        Statement stmt = myS.myConnection.createStatement();
//        String sql = "Select * from timeslot";
//        ResultSet myRS = stmt.executeQuery(sql);
//        int RSLength = 0;
//        while(myRS.next())
//        {
//            RSLength++;
//        }
//        myRS.first();
//        timeslots = new Timeslot[RSLength];
//        for (int i = 0; i < RSLength; i++) {
//            char[] timeSlotDays = myRS.getString(4).toCharArray();
//            timeslots[i] = new Timeslot(myRS.getInt(1), myRS.getTime(2), myRS.getTime(3), timeSlotDays);
//            myRS.next();
//        }
//        fillSection(sectionTable);
//
//    }
    
//    public static void fillSection(SectionFeed[][] sectionTable)
//    {
//        for (int h = 0; h < timeslots.length; h++) 
//        {
//            for (int k = 0; k < timeslots[h].getTime_DayID().length; k++) 
//            {
//                for (int i = 0; i < 5; i++) 
//                { 
//                    if(timeslots[h].getTime_DayID()[k] == dayLetter[i])
//                    {
//                        for (int j = 0; j < 9; j++) 
//                        {
//                            if(timeslots[h].getTime_Start().toString().substring(0, 5).equals(DayTimeSlot[i][j]))
//                            {
//                                sectionTable[j][i] = new SectionFeed("", "", DayTimeSlot[i][j], "");
//                            }
//                        }
//                    }
//                }
//            }
//        }

        





//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 5; j++) {
//                    sections[i][j] = new SectionFeed("", "", DayTimeSlot[j][i], ""); 
//            }
//        }
    
    
    public static void addSection(SectionFeed course, SectionFeed[][] sectionTable){
        char[] courseDays = course.classDay.toCharArray();
        for (int k = 0; k < courseDays.length; k++) {
            for (int i = 0; i < 5; i++) 
            { 
                if(courseDays[k] == dayLetter[i])
                {
                    for (int j = 0; j < 9; j++) 
                    {
                        if(course.classTime.equals(DayTimeSlot[i][j]))
                        {
                            sectionTable[j][i] = new SectionFeed(course.courseName, course.professorName, DayTimeSlot[i][j], days[i]);
                        }
                    }
                }
            }
        }
        
        
    }
}
