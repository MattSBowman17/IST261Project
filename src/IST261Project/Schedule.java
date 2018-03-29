/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 *
 * @author ctg5117
 */
public class Schedule
{
    private int count = 10;
    Random myR = new Random();
    
//  ProfessorCourse myPC = new ProfessorCourse(1, 1, 1);
//  RoomTime myRT = new RoomTime(1, 1, 1);
    
    //Used to store the actual class schedule
    HashMap<RoomTime, ProfessorCourse> HMSection = new HashMap<>();
    
    //Occupied RoomTimes
    ArrayList<RoomTime> ALRoomTOcc = new ArrayList<>();
    //Avaliable RoomTimes
    ArrayList<RoomTime> ALRoomTAva = new ArrayList<>();
    
    ArrayList<ProfessorCourse> ALProfC = new ArrayList<>(); 
    ArrayList<Room> ALRoom = new ArrayList<>();    
    ArrayList<Course> ALCourse = new ArrayList<>();       
    ArrayList<Professor> ALProf = new ArrayList<>();
    ArrayList<Package> ALPack = new ArrayList<>();
    ArrayList<ProfessorConstraint> ALPrCon = new ArrayList<>();
   
    
    
    Schedule()
    {
        //myHMap.putIfAbsent(myRT, myPC);
        getData();

      
    }
    
    /**Currently It Generates dummy data. 
     * In the future it will pull data from the MySQL server into the ArrayLists
     * 
     */
    public void getData()
    {
        /*TODO: Use SQL to generate the data for the Array Lists of Roomtimes and ProfessorCourses 
         *  Right now it will only generate dummy data
         */
        
        for(int i = 0; i < count; i++)
        {
            ALProfC.add(new ProfessorCourse(i, i, i));
            ALRoomTAva.add(new RoomTime(i, i, 1));
            ALCourse.add(new Course(i, (myR.nextInt(4-2)*10)));
            ALRoom.add(new Room(i, 30, myR.nextInt(2-1)));
           
        }
    }
    
    
    /**Add a section to the HashMap.
     * Assigns a ProfessorCourse
     * 
     * @return True if the section can be added to HashMap 
     */
    public boolean addSection()
    {
        return true;
    }
    
    
    //Testing main, will be deleted
    public static void main(String[] args) 
    {
        Schedule mySchedule = new Schedule();
    }
    
    //For sort, use (putIfAbsent(K key, V value)) to determine if a key already has a value stored at it. 
}
