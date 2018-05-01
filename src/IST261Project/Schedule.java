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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ctg5117
 */
public class Schedule
{
    public final int intTestingSize = 13;      // Number of timeslots total. Used for generating dummy data equal to our database data.
    public final int intProfSize = 12;         // Number of total professors
    public final int intCapacity = 30;         // Overall room capacity, decided to be this since it would be a hassle to 
    public final boolean bDebugging = true;    // Debugging value, if enabled will print out statements to the console
    
    Random myR = new Random();
    
    //Used to store the actual class schedule
    HashMap<RoomTime, ProfessorCourse> HMSection = new HashMap<>();
    
    public ArrayList<RoomTime> ALRoomTOcc = new ArrayList<>();         //ArrayList of Occupied RoomTimes NOT USED YET
    public ArrayList<RoomTime> ALRoomTAva = new ArrayList<>();         //ArrayList of Avaliable RoomTimes 
    
    public ArrayList<Timeslot> ALTimeS = new ArrayList<>();            //ArrayList of Timeslots
    public ArrayList<Section> ALSection = new ArrayList<>();           //ArrayList of Sections
    public ArrayList<ProfessorCourse> ALProfC = new ArrayList<>();     //ArrayList of ProfessorCourses
    public ArrayList<Room> ALRoom = new ArrayList<>();                 //ArrayList of Rooms
    public ArrayList<Course> ALCourse = new ArrayList<>();             //ArrayList of Courses
    public ArrayList<Professor> ALProf = new ArrayList<>();            //ArrayList of Professors
    public ArrayList<Package> ALPack = new ArrayList<>();              //ArrayList of Packages NOT USED YET
    public ArrayList<ProfessorConstraint> ALPrCon = new ArrayList<>(); //ArrayList of ProfessorConstraints NOT USED YET
   
    MySQLDBConnector mySQL;
    
    
    
    /**Constructor for the Schedule.
     * Runs all of the private methods of the schedule, they are made public for
     * now so the JavaDoc has everything. But these should be private methods
     * 
     * @param sql The mySQL connection being used by the overall program
     */
    Schedule(MySQLDBConnector sql)
    {          
        mySQL = sql;
        
        getData();
        createSections();
        scheduleProfessors();
        scheduleTimes();
        insertSchedule();
    }
    
    /**Currently It Generates dummy data. 
     * In the future it will pull data from the MySQL server into the ArrayLists
     * 
     * At this point the code generates data that is almost exactly equal to the
     * data in the database. We already pull data from the database in another 
     * method
     * 
     * It would be a waste of our time to parse all of these result sets for a 
     * minimal bonus to our data. The ProfessorCourse data that we currently 
     * have isn't accurate to real life, but for the most part it should work.
     * 
     */
    public void getData()
    {
        //Generate Professors
        for(int i = 1; i <= intProfSize; i++)
        {
           ALProf.add(new Professor(i, 15));
        }
        //Generate timeslots
        for(int i = 1; i <= intTestingSize; i++)
        {
            ALTimeS.add(new Timeslot(i));
            
            //System.out.println(ALProfC.get(i).getProfessor_ProfessorID());    
        }
  
        //Generate Courses
        for(int i = 1; i < 39; i++)
        {
           ALCourse.add(new Course(i, ((myR.nextInt(5)+1)*10)));    
        }
        
        //Generate ProfessorCourses 
        for(int i = 1; i <= 80; i++)
        {
            ALProfC.add(new ProfessorCourse(i, myR.nextInt(intProfSize), i, i));
        }
        
        //Generate Rooms
        for(int i = 1; i < 14; i++)
        {
            ALRoom.add(new Room(i, 30, myR.nextInt(2-1)));
        }
        
        /*Generate Roomtimes, Counter is the overall number of roomtimes without
        going to a triple nested loop*/
        int counter = 1;
        
        for(int i = 1; i <= ALRoom.size(); i++)
        {
            for(int j = 1; j <= ALTimeS.size(); j++)
            {
                ALRoomTAva.add(new RoomTime(counter, j, i));
                counter++;
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
     * TODO: find a way to dynamically find capacity of room. This feature will
     * be created in Sprint 2
     * 
     * @author ctg5117
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
     * ALSection starts being modified to have a ProfessorCourse
     * 
     * @author ctg5117
     */
    public void scheduleProfessors()
    {
        //If Section isn't empty, start putting professors in Sections in the Section arraylist
       
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
                    //Pull random data out of the temporary PC array to be added to a class.
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
    public void scheduleTimes()
    {
        if(!ALSection.isEmpty())
        {
            for(int i = 0; i < ALSection.size(); i++)
            {      
                RoomTime myRT = getRandomRT(ALRoomTAva);
                ALSection.get(i).setRoomTime_RoomTimeID(myRT.getRoomTimeID());
                
                for(int j = 0; j < ALRoomTAva.size(); j++)
                {    
                    if(myRT.getRoomTimeID() == ALRoomTAva.get(j).getRoomTimeID())
                    {
                        ALRoomTAva.remove(j);
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
     */
    public void insertSchedule()
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
}
