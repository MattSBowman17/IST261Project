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
 */
public class SectionDisplay {
      //"SELECT course.cName,  building.bName,  room.rNumber,  professor.pFName,  professor.pLName,  timeslot.srtTime,  timeslot.endTime,  timeslot.dayId "
    
    String CourseName;
    String RoomName;
    String ProfName;
    String Days;
    Time TimeStart;

    public SectionDisplay(String CourseName, String RoomName, String ProfName, String Days, Time TimeStart) {
        this.CourseName = CourseName;
        this.RoomName = RoomName;
        this.ProfName = ProfName;
        this.Days = Days;
        this.TimeStart = TimeStart;
    }
    
    
}
