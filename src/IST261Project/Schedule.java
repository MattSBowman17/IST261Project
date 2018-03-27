/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;
import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author ctg5117
 */
public class Schedule
{
    
    ProfessorCourse myPC = new ProfessorCourse(1, 1, 1);
    RoomTime myRT = new RoomTime();
 
    Map<RoomTime, ProfessorCourse> myHMap = new HashMap<>();
    
    Schedule()
    {
        myHMap.put(myRT, myPC);
    }
 
    
    //For sort, use (putIfAbsent(K key, V value)) to determine if a key already has a value stored at it. 
}
