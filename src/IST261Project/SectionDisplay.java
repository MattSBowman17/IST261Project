/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.sql.Time;

/**
 *
 * @author kds5314
 * 
 */

/** Used to store ResultSet Data from SQL Query of Sections */
public class SectionDisplay {
      //"SELECT course.cName,  building.bName,  room.rNumber,  professor.pFName,  professor.pLName,  timeslot.srtTime,  timeslot.endTime,  timeslot.dayId "
    
    String CourseName;
    String RoomName;
    String ProfName;
    String Days;
    Time TimeStart;

    /**
     *
     * @param CourseName Course Name to be displayed on the first line.
     * @param RoomName Room Name to be used in determining the correct row to place the section in.
     * @param ProfName Professor Name to be displayed on the second line.
     * @param Days Days the section takes place used in determining the correct row.
     * @param TimeStart The 24HR time used to determine which row the section belongs in.
     */
    public SectionDisplay(String CourseName, String RoomName, String ProfName, String Days, Time TimeStart) {
        this.CourseName = CourseName;
        this.RoomName = RoomName;
        this.ProfName = ProfName;
        this.Days = Days;
        this.TimeStart = TimeStart;
    }
    
    
}
