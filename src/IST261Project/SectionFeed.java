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
    String firstLine;
    String secondLine;
    String thirdLine;
    String fourthLine;
    String Output;
    static Room[] rooms;
    static SectionDisplay[] sections;
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
        this.firstLine = courseName;
        this.secondLine = professorName;
        this.thirdLine = classTime;
        this.fourthLine = classDay;
        setOutput();
    }
    
    public SectionFeed(String buildingName, int roomNumber, int seats, int roomPackage) 
    {
        this.firstLine = buildingName + " " + String.valueOf(roomNumber);
        this.secondLine = "Seats:" + String.valueOf(seats);
        this.thirdLine = "Package:" + String.valueOf(roomPackage);
        setOutput();
    }
    
    private void setOutput(){
        Output = "<html>" + firstLine + "<br>" + secondLine + "<br>" + thirdLine + "</html>";
    }
    
    public static void getRooms(SectionFeed[][] sectionTable, MySQLDBConnector myS) throws SQLException
    {
        Statement stmt = myS.myConnection.createStatement();
        String sql = "SELECT building.bName, room.rNumber, room.rSize, room.Package_PackageID " +
        "FROM ctg5117.room room, ctg5117.building building " +
        "WHERE room.Building_BuildingID = building.BuildingID " + 
        "ORDER BY room.rNumber ASC";
        ResultSet myRS = stmt.executeQuery(sql);
        int RSLength = 0;
        while(myRS.next())
        {
            RSLength++;
        }
        myRS.first();
        rooms = new Room[RSLength];
        for (int i = 0; i < RSLength; i++) {
            rooms[i] = new Room(myRS.getString(1), myRS.getInt(4), myRS.getInt(2), myRS.getInt(3));
            myRS.next();
        }
        for (int i = 0; i < rooms.length; i++) {
            sectionTable[i][0] = new SectionFeed(rooms[i].getBuilding_Name(), rooms[i].getRoom_Number(), rooms[i].getRoom_Size(), rooms[i].getPackage_Package_ID());
            for (int j = 1; j < 43; j++) {
                sectionTable[i][j] = new SectionFeed("","","","");
            }
        }
        
    }
    
    public static void fillSections(SectionFeed[][] sectionTable, MySQLDBConnector myS) throws SQLException
    {
        Statement stmt = myS.myConnection.createStatement();
        String sql = "SELECT course.cName,  building.bName,  room.rNumber,  professor.pFName,  professor.pLName,  timeslot.srtTime,  timeslot.dayId " +
            "FROM section " +
            "INNER JOIN professorcourse ON section.ProfessorCourse_ProfessorCourseID = professorcourse.ProfessorCourseID " +
            "INNER JOIN roomtime ON section.RoomTime_RoomTimeID = roomtime.RoomTimeID "+
            "INNER JOIN room ON roomtime.Room_RoomID = room.RoomID " + 
            "INNER JOIN building ON room.Building_BuildingID = building.BuildingID " +
            "INNER JOIN course ON professorcourse.Course_CourseID = course.CourseID " +
            "INNER JOIN professor ON professorcourse.Professor_ProfessorID = professor.ProfessorID " + 
            "INNER JOIN timeslot ON roomtime.Timeslot_TimeID = timeslot.TimeID";
        ResultSet myRS = stmt.executeQuery(sql);
        int RSLength = 0;
        while(myRS.next())
        {
            RSLength++;
        }
        myRS.first();
        sections = new SectionDisplay[RSLength];
        for (int i = 0; i < RSLength; i++) {
            sections[i] = new SectionDisplay(myRS.getString(1), myRS.getString(2) + myRS.getInt(3), myRS.getString(4) + myRS.getString(5), myRS.getString(7).toCharArray(), myRS.getTime(6));
            myRS.next();
        }
        for (int i = 0; i < sections.length; i++) {
            for (int j = 0; j < dayLetter.length; j++) {
                for (int k = 0; k < sections[i].Days.length; k++) {
                    if(sections[i].Days[k] == dayLetter[j])
                    {
                        
                    }
                }
                          
                
            }
            sectionTable[i][0] = new SectionFeed(rooms[i].getBuilding_Name(), rooms[i].getRoom_Number(), rooms[i].getRoom_Size(), rooms[i].getPackage_Package_ID());
            
        }
        
    }

    
//    public static void addSection(SectionFeed course, SectionFeed[][] sectionTable){
//        char[] courseDays = course.fourthLine.toCharArray();
//        for (int k = 0; k < courseDays.length; k++) {
//            for (int i = 0; i < 5; i++) 
//            { 
//                if(courseDays[k] == dayLetter[i])
//                {
//                    for (int j = 0; j < 9; j++) 
//                    {
//                        if(course.thirdLine.equals(DayTimeSlot[i][j]))
//                        {
//                            sectionTable[j][i] = new SectionFeed(course.firstLine, course.secondLine, DayTimeSlot[i][j], days[i]);
//                        }
//                    }
//                }
//            }
//        }
//        
//        
//    }
}
