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
    private int intTestingSize = 10;
    private int intCapacity = 30;
    private boolean bDebugging = true;
    
    
    Random myR = new Random();
    
//  ProfessorCourse myPC = new ProfessorCourse(1, 1, 1);
//  RoomTime myRT = new RoomTime(1, 1, 1);
    
    //Used to store the actual class schedule
    HashMap<RoomTime, ProfessorCourse> HMSection = new HashMap<>();
    
    //Occupied RoomTimes
    private ArrayList<RoomTime> ALRoomTOcc = new ArrayList<>();
    //Avaliable RoomTimes
    private ArrayList<RoomTime> ALRoomTAva = new ArrayList<>();
    
    
    private ArrayList<Section> ALSection = new ArrayList<>();
    private ArrayList<ProfessorCourse> ALProfC = new ArrayList<>(); 
    private ArrayList<Room> ALRoom = new ArrayList<>();    
    private ArrayList<Course> ALCourse = new ArrayList<>();       
    private ArrayList<Professor> ALProf = new ArrayList<>();
    private ArrayList<Package> ALPack = new ArrayList<>();
    private ArrayList<ProfessorConstraint> ALPrCon = new ArrayList<>();
   
    
    
    Schedule()
    {
        //myHMap.putIfAbsent(myRT, myPC);
        getData();
        createSections();
//        scheduleProfessors();

      
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
        for(int i = 1; i <= 4; i++)
        {
           ALProf.add(new Professor(i, 9));
        }
        
        
        for(int i = 0; i < intTestingSize; i++)
        {
            ALProfC.add(new ProfessorCourse(i, myR.nextInt(4)+1, i, i));
            System.out.println(ALProfC.get(i).getProfessor_ProfessorID());
            ALRoomTAva.add(new RoomTime(i, i, 1));
            ALCourse.add(new Course(i, ((myR.nextInt(4)+1)*10)));
            ALRoom.add(new Room(i, 30, myR.nextInt(2-1)));        
        }
    }
    
//    /**
//     * 
//     * 
//     * @return True if the section can be added to HashMap 
//     */
//    public boolean addSection()
//    {
//        return true;
//    }
    
    /**createSections.
     * Stage 2 of Scheduling. 
     * Uses data from database to create a list of all the required sections  
     * based on the class size that thinks is possible
     * 
     * 
     * For the most part complete
     * TODO: find a way to dynamically find capacity of room.
     */
    public void createSections()
    {
        int intSectionID = 0;
        
        for(int i = 0; i<ALProfC.size(); i++)
        {
            int intSectionSize = ALCourse.get(ALProfC.get(i).getCourse_CourseID()).getCourse_EstStudents();
            int intNumSections = 1;
                    
            while(intSectionSize> intCapacity)
            {
               intNumSections++;
               intSectionSize = intSectionSize/intNumSections;
            } 
            while(intNumSections > 0)
            {
                intSectionID++;
                ALSection.add(new Section(intSectionID, intSectionSize, ALProfC.get(i).getCourse_CourseID()));
                intNumSections--;
            }
        }
        if(bDebugging)
        {
            for(int i = 0; i < ALSection.size(); i++)
            {
                System.out.println("Section ID: " + ALSection.get(i).getSection_ID() + ", " + ALSection.get(i).getSectNumStudents() + ", " + ALSection.get(i).getProfessorCourse_ProfessorCourseID());
            }
        }
    }
    
    /**Step 3 of Scheduling.
     * Uses professor data from professorCourse to add professors to all the
     * courses
     * 
     * TODO:
     * Create copy of Schedule list. 
     * See what courses are scheduled in comparison to what professors can teach
     * Add professorID to section object
     * Hashmap to store
     * 
     * 
     */
    public void scheduleProfessors()
    {
        if(!ALSection.isEmpty())
        {
            HashMap<Section, ProfessorCourse> HMPC = new HashMap<>();
            int[] aIntSections = new int[ALCourse.size()];
            
            //Create list of courses and how many sections each course has
            for(int i = 0; i < ALSection.size();i++)
            {
                aIntSections[ALProfC.get(ALSection.get(i).getProfessorCourse_ProfessorCourseID()).getCourse_CourseID()]++;
            }              
            
            for(int i = 0; i < aIntSections.length; i++)
            {
                while(aIntSections[i] > 0)
                {
                    ALProfC.get(aIntSections[i]).getProfessorCourseID();
                    aIntSections[i]--;
                }
            }
            
        }
        else
        {
            
        }
    }
    
    /**Step 4 of Scheduling.
     * Uses data from professorConstraint to make sure that professors can
     * work at that given timeslot
     * 
     */
    public void scheduleTimes()
    {
        
    }
    
    /**Step 5 of Scheduling. 
     * Check if the schedule has any major conflicts
     *  
     * 
     * @return True if the schedule is allowed to be pushed to database
     */
    public boolean checkSchedule()
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
