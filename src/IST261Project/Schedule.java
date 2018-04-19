/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
    
    private ArrayList<Timeslot> ALTimeS = new ArrayList<>();
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
        scheduleProfessors();

      
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
        for(int i = 0; i <= 3; i++)
        {
           ALProf.add(new Professor(i, 9));
        }
        
        
        for(int i = 0; i < intTestingSize; i++)
        {
            ALTimeS.add(new Timeslot(i));
            ALProfC.add(new ProfessorCourse(i, myR.nextInt(3), i, i));
            //System.out.println(ALProfC.get(i).getProfessor_ProfessorID());
            ALRoomTAva.add(new RoomTime(i, i, 1));
            ALRoomTAva.add(new RoomTime(i+10, i, 2));
            ALCourse.add(new Course(i, ((myR.nextInt(4)+1)*10)));
            ALRoom.add(new Room(i, 30, myR.nextInt(2-1)));
            
            

        }
    }
    
    
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
        
        //Create Sections based on courses
        
        for(int i = 0; i<ALCourse.size(); i++)
        {
            //Create the size of the current Section
            int intSectionSize = ALCourse.get(i).getCourse_EstStudents();
            int intNumSections = 1;
            
            //Divide up the sections so that they are correctly sized       
            
            while(intSectionSize> intCapacity)
            {
               intNumSections++;
               intSectionSize = intSectionSize/intNumSections;               
            }
            
            while(intNumSections > 0)
            { 
                intSectionID++;
                
                ALSection.add(new Section(intSectionID, intSectionSize, ALCourse.get(i).getCourse_ID()));
                
                intNumSections--;
            }
        }
        
        if(bDebugging)
        {
            for(int i = 0; i < ALSection.size(); i++)
            {
                //System.out.println("Section ID: " + ALSection.get(i).getSection_ID() + ", " + ALSection.get(i).getSectNumStudents() + ", " + ALSection.get(i).getIntCourse());
            }
        }
    }
    
    /**Step 3 of Scheduling.
     * Uses professor data from professorCourse to add professors to all the
     * courses
     * 
     */
    public void scheduleProfessors()
    {
        //HashMap<ProfessorCourse, Section> HMPC = new HashMap<>();
       
        if(!ALSection.isEmpty())
        {
            for(int i = 0; i < ALSection.size(); i++)
            {
                ArrayList<ProfessorCourse> PCTemp = new ArrayList<>();
                
                //Create an arraylist of professors who can teach a certain class
                
                for(int j = 0; j <ALProfC.size(); j++)
                {
                    if(ALSection.get(i).getIntCourse() == ALProfC.get(j).getCourse_CourseID())
                    {
                        PCTemp.add(ALProfC.get(j));
                        //System.out.println(PCTemp.size());
                    }
                }
                
                if(!PCTemp.isEmpty())
                {
                    while(!PCTemp.isEmpty())
                    {
                        ProfessorCourse RandomPC = getRandomPC(PCTemp);
                        if(ALProf.get(RandomPC.getProfessor_ProfessorID()).getCoursesEnrolled() < ALProf.get(RandomPC.getProfessor_ProfessorID()).getProfessor_CourseLoad()) 
                        {
                            ALSection.get(i).setProfessorCourse_ProfessorCourseID(RandomPC.getProfessorCourseID());
                            ALProf.get(RandomPC.getProfessor_ProfessorID()).increaseCoursesEnrolled();
                            //System.out.println("Sechuled properly");
                            break;
                        }
                        else if(PCTemp.size() == 1)
                        {
                            ALSection.get(i).setProfessorCourse_ProfessorCourseID(RandomPC.getProfessorCourseID());
                            ALProf.get(RandomPC.getProfessor_ProfessorID()).increaseCoursesEnrolled();
                            ALSection.get(i).setProblem();
                            //System.out.println("Over-Scheduled");
                            PCTemp.remove(RandomPC);
                            break;
                        }
                        else
                        {
                            PCTemp.remove(RandomPC);
                        }
                    }
                }
                else
                {
                    ALSection.get(i).setProblem();
                }
            }   
        }
        else
        {
            System.out.println("Need sections");
        }
    }
    
    /**Step 4 of Scheduling.
     * Uses data from professorConstraint to make sure that professors can
     * work at that given timeslot
     * ALProf.get(ALProfC.get(ALSection.get(i).getProfessorCourse_ProfessorCourseID()).getProfessor_ProfessorID())
     */
    public void scheduleTimes()
    {
        if(!ALSection.isEmpty())
        {
            HashMap<Professor, ArrayList<Timeslot> > HMProfTS = new HashMap<>();
            
            
            /*
                HashMap of all professors and available timeslots. Assuming from the start that they have equal timeslots
            */
            for(int i = 0; i < ALProf.size(); i++)
            {
                ArrayList<Timeslot> ALTemp = new ArrayList<>();
                ALTemp.addAll(ALTimeS);
                HMProfTS.putIfAbsent(ALProf.get(i), ALTemp);
            }
            

            
            /*
                Removing Constraints from each professor's list of avaliable times
            */
            Iterator it = HMProfTS.entrySet().iterator();
            
            while (it.hasNext())
            {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                
                Professor tProf = (Professor) pair.getKey();                    //Temporary Current professor in the Iterator Pair
                ArrayList tSlot = (ArrayList) pair.getValue();                  //Temporary ArrayList of the Professor's timeslots            
                ArrayList<Timeslot> tUNAv = tProf.getProfessorOccupied();       //Temporary List of Professor's unavaliable timeslots
                
                ArrayList<ProfessorConstraint> PrConTemp = new ArrayList<>();
                
                //Create an arraylist of professors who can teach a certain class
                
                for(int i = 0; i < ALPrCon.size(); i++)
                {
                    if(ALPrCon.get(i).getProfessor_Professor_ID() == tProf.getProfessor_ID())
                    {
                        tUNAv.add(ALTimeS.get(ALPrCon.get(i).getTime_Time_ID()));
                    }
                }
                
                if(tSlot.size() > 0)
                {
                    for(int i = 0; i < tUNAv.size(); i++)
                    {
                        for(int j = 0; j< tSlot.size(); j++)
                        {
                            if(tSlot.get(j).equals(tUNAv.get(i)))
                            {
                                tSlot.remove(j);
                            }       
                        }
                    }
                                                                                // Create an arrayList of all of the professor's constraints.
                }
                  
                HMProfTS.put(tProf, tSlot); 
            }
            /*
            Where courses go to get scheduled in times
            */
            ArrayList<Timeslot> ALTSTemp = new ArrayList<>();               //Literally what even is this. I'm going to keep it just in case I need it in the future
            Iterator schedIT = HMProfTS.entrySet().iterator();              
                
                
            while(schedIT.hasNext())
            {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                    
                Professor tProf = (Professor) pair.getKey();                    //Temporary Current professor in the Iterator Pair
                ArrayList<Timeslot> tSlot = (ArrayList) pair.getValue();        //Temporary ArrayList of the Professor's timeslots 
                boolean bFlag = false;
                int counter = 0;
                int location = 0;
                                                                                //Timeslot tCurr;
                for(int i = 0; i < ALSection.size(); i++)
                {                                                            
                    if(ALProfC.get(ALSection.get(i).getProfessorCourse_ProfessorCourseID()).getProfessor_ProfessorID() == tProf.getProfessor_ID())
                    {
                        for(int j = 0; j < tSlot.size(); j++)
                        {
                            if(!bFlag)
                            {
                                for(int k = 0; k < ALRoomTAva.size(); k++)
                                {
                                    if(ALRoomTAva.get(k).getTime_Time_ID() == tSlot.get(i).getTime_ID())
                                    {
                                        bFlag = true;
                                        ALSection.get(i).setRoomTime_RoomTimeID(ALRoomTAva.get(k).getRoomTimeID());
                                        ALRoomTOcc.add(ALRoomTAva.get(k));
                                        ALRoomTAva.remove(k);
                                        break;
                                    }   
                                }
                            }
                            
                            else
                            {  
                                break;
                            }            
                        }
                    }    
                }
            }
        }
        else
        {
            System.out.println("Need Sections to be Created first.");
        }
    }
    
//    /**Step 5 of Scheduling. 
//     * Check if the schedule has any major conflicts
//     * Nevermind. Not important
//     * 
//     * @return True if the schedule is allowed to be pushed to database
//     */
//    public boolean checkSchedule()
//    {
//        return true;
//    }
    

    //Testing main, will be deleted
    public static void main(String[] args) 
    {
       ArrayList<Integer> arrInt = new ArrayList<>();
       
       for(int i = 0; i < 5; i++)
       {
           arrInt.add(i);
       }
       
       for(int i = 0; i < 5; i++)
       {
           if(arrInt.get(i) == 3)
           {
               arrInt.remove(i);
           }
           System.out.println(i);
       }
    }
    
    /**Return a random ProfessorCourse from ArrayList
     * 
     * @param ALTemp Temporary name of the inputted ArrayList
     * @return a random object from the inputted ArrayList of type ProfessorCourse
     */
    public ProfessorCourse getRandomPC(ArrayList<ProfessorCourse> ALTemp)
    {
	int index = myR.nextInt(ALTemp.size());
	return ALTemp.get(index);
	    
    }
    
    /**Return a random RoomTime from ArrayList
     * 
     * @param ALTemp Temporary name of the inputted ArrayList
     * @return a random object from the inputted ArrayList of type RoomTime
     */
    public RoomTime getRandomRT(ArrayList<RoomTime> ALTemp)
    {
	int index = myR.nextInt(ALTemp.size());
	return ALTemp.get(index);
	    
    }
    
     /**Return a random Timeslot from ArrayList
     * 
     * @param ALTemp Temporary name of the inputted ArrayList
     * @return a random object from the inputted ArrayList of type RoomTime
     */
    public Timeslot getRandomTS(ArrayList<Timeslot> ALTemp)
    {
	int index = myR.nextInt(ALTemp.size());
	return ALTemp.get(index);
	    
    }
    
    //For sort, use (putIfAbsent(K key, V value)) to determine if a key already has a value stored at it. 
}
