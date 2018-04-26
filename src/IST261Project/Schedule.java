/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ctg5117
 */
public class Schedule
{
    private int intTestingSize = 13;
    private int intProfSize = 12;
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
   
    MySQLDBConnector mySQL;
    
    
    Schedule(MySQLDBConnector sql)
    {
                
        mySQL = sql;
        //myHMap.putIfAbsent(myRT, myPC);
        getData();
        createSections();
        scheduleProfessors();
        scheduleTimes();
        insertSchedule();

      
    }
    
    /**Currently It Generates dummy data. 
     * In the future it will pull data from the MySQL server into the ArrayLists
     * 
     */
    private void getData()
    {
        /*TODO: Use SQL to generate the data for the Array Lists of Roomtimes and ProfessorCourses 
         *  Right now it will only generate dummy data
         */
        for(int i = 0; i <= intProfSize; i++)
        {
           ALProf.add(new Professor(i, 15));
        }
        
        
        for(int i = 0; i < intTestingSize; i++)
        {
            ALTimeS.add(new Timeslot(i));
            
            //System.out.println(ALProfC.get(i).getProfessor_ProfessorID());    
        }
        
        for(int i = 0; i < 60; i++)
        {
            ALProfC.add(new ProfessorCourse(i, myR.nextInt(intProfSize), i, i));
        }
           
        
        for(int i = 0; i < 38; i++)
        {
           ALCourse.add(new Course(i, ((myR.nextInt(5)+1)*10)));    
        }
        
        for(int i = 0; i < 14; i++)
        {
            ALRoom.add(new Room(i, 30, myR.nextInt(2-1)));
        }
        
        for(int i = 0; i < ALRoom.size(); i++)
        {
            for(int j = 0; j< ALTimeS.size(); j++)
            {
                ALRoomTAva.add(new RoomTime(i, j, 1));
            }
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
    private void createSections()
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
    private void scheduleProfessors()
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
                            break;
                        }
                        else if(PCTemp.size() == 1)
                        {
                            ALSection.get(i).setProfessorCourse_ProfessorCourseID(RandomPC.getProfessorCourseID());
                            ALProf.get(RandomPC.getProfessor_ProfessorID()).increaseCoursesEnrolled();
                            ALSection.get(i).setProblem();
                        }
                        PCTemp.remove(RandomPC);
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
    private void scheduleTimes()
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
            Where courses go to get scheduled in times
            */
            ArrayList<Timeslot> ALTSTemp = new ArrayList<>();                   //Literally what even is this. I'm going to keep it just in case I need it in the future
            Iterator schedIT = HMProfTS.entrySet().iterator();              
                
                
            while(schedIT.hasNext())
            {
                HashMap.Entry pair = (HashMap.Entry)schedIT.next();
                    
                Professor tProf = (Professor) pair.getKey();                    //Temporary Current professor in the Iterator Pair
                ArrayList<Timeslot> tSlot = (ArrayList) pair.getValue();        //Temporary ArrayList of the Professor's timeslots 
                                                                                //Timeslot tCurr;
                for(int i = 0; i < ALSection.size(); i++)
                {      
                    if(ALProfC.get(ALSection.get(i).getProfessorCourse_ProfessorCourseID()).getProfessor_ProfessorID() == tProf.getProfessor_ID())
                    {
                        for(int j = 0; j < tSlot.size(); j++)
                        {
                            ALSection.get(i).setRoomTime_RoomTimeID(ALRoomTAva.get(j).getRoomTimeID());
                            ALRoomTOcc.add(ALRoomTAva.get(j));
                            ALRoomTAva.remove(j);
                            break;                                               
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
    
    /**Step 5 of Scheduling. 
     * Put into database
     * Adapted from https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
     * https://www.javatpoint.com/PreparedStatement-interface
     * 
     * @return True if the schedule is allowed to be pushed to database
     */
    private void insertSchedule()
    {              
        PreparedStatement stmt;
        
        try
        {
            String sqlDelete = "Delete from section";
            stmt = mySQL.getConnection().prepareStatement(sqlDelete);
            stmt.execute();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

        for(int i = 0; i < ALSection.size(); i++)
        {
           
            try
            {
                String sql = "Insert into Section (ProfessorCourse_ProfessorCourseID, RoomTime_RoomTimeID, enrolledStd, studentsNum, sectionNum) values (?, ?, 30, 30, ?)";
                int b = ALSection.get(i).getProfessorCourse_ProfessorCourseID();
                int c = ALSection.get(i).getRoomTime_RoomTimeID();
                int d = ALSection.get(i).getSection_SectionNumber();
                
                stmt = mySQL.getConnection().prepareStatement(sql);
                stmt.setInt(1, b);
                stmt.setInt(2, c);
                stmt.setInt(3, d);
                
                stmt.execute();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
    

    //Testing main, will be deleted
    public static void main(String[] args) 
    {
        MySQLDBConnector mySQL = new MySQLDBConnector();
        try
        {
            mySQL.connectToDatabase("istdata.bk.psu.edu","3306","ctg5117","berks3900","ctg5117");
            Schedule s = new Schedule(mySQL);
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
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
