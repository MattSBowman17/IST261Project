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
    static String[][] DayTimeSlot = new String[][]
    {
        {"08:00","09:05","10:10","11:15","13:25","14:30","15:45","16:40"},
        {"09:05","10:35","13:35","15:05","16:35"}
    };

    
    static String[] days = new String[]{"MWF","TR"};

            
        
    
    
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
            for (int j = 1; j < 15; j++) {
                sectionTable[i][j] = new SectionFeed("","","","");
            }
        }
        
    }
    
    public static void fillSections(SectionFeed[][] sectionTable, MySQLDBConnector myS) throws SQLException
    {
        int Room = 0;
        int TimeSlot = 0;
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
            sections[i] = new SectionDisplay(myRS.getString(1), myRS.getString(2) + " " + myRS.getInt(3), myRS.getString(4) + " " + myRS.getString(5), myRS.getString(7), myRS.getTime(6));
            myRS.next();
        }
        
        for (int i = 0; i < sections.length; i++) 
        {
            Room = roomSlot(sections[i]);
            for(int j = 0; j < days.length; j++) 
            {
                if(sections[i].Days.equals(days[j]))
                {
                    TimeSlot = (timeSlot(sections[i], j));
                    sectionTable[Room][TimeSlot] = new SectionFeed(sections[i].CourseName, sections[i].ProfName, "","");
                    break;
                }
            }             
        }      
    }
    
    public static int roomSlot(SectionDisplay sectionIn)
    {
        int roomID = 0;
        String room = sectionIn.RoomName;
        for (int i = 0; i < rooms.length; i++) {
            String roomName = rooms[i].getBuilding_Name() + " " + rooms[i].getRoom_Number();
            if (room.equals(roomName)) {
                roomID = i;
            }
        }
        return roomID;
    }
    
    public static int timeSlot(SectionDisplay sectionIn, int dayNumber)
    {
        int columnID = 0;
        SectionFeedTableModel SFTM = new SectionFeedTableModel();
        String timeStart = sectionIn.TimeStart.toString().substring(0, 5);
        String day = days[dayNumber];
        String sectionTime = "<html>" + day + "<br>" + timeStart + "</html>";
        for (int i = 0; i < 14; i++) {
            String columnDay = SFTM.getColumnName(i);
            boolean Correct = sectionTime.equals(columnDay);
            if(Correct)
            {
               columnID = i;
               break;
            }
        }
        return columnID;
    }

    
}
