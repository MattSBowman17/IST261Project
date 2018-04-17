package IST261Project;

import java.util.ArrayList;

public class Professor
{

    private int Professor_ID;

    private String Professor_FName;

    private String Professor_LName;

    private int Professor_CourseLoad;

    private String Professor_Username;

    private String Profesor_Department;
    
    private ArrayList<Timeslot> ProfessorOccupied;            //List of RoomtimeIDs that the professor is already teaching during
    
    private int CoursesEnrolled;

    /**TODO: Create Professor Creation method. 
     * 
     * 
     */
  
    Professor(int Professor_ID, int Professor_CourseLoad)
    {
        CoursesEnrolled = 0;
        this.Professor_ID= Professor_ID;

        this.Professor_CourseLoad = Professor_CourseLoad/3;
    }
    
    /**
     * @return the Professor_ID
     */
    public int getProfessor_ID() {
        return Professor_ID;
    }

    /**
     * @param Professor_ID the Professor_ID to set
     */
    public void setProfessor_ID(int Professor_ID) {
        this.Professor_ID = Professor_ID;
    }

    /**
     * @return the Professor_FName
     */
    public String getProfessor_FName() {
        return Professor_FName;
    }

    /**
     * @param Professor_FName the Professor_FName to set
     */
    public void setProfessor_FName(String Professor_FName) {
        this.Professor_FName = Professor_FName;
    }

    /**
     * @return the Professor_LName
     */
    public String getProfessor_LName() {
        return Professor_LName;
    }

    /**
     * @param Professor_LName the Professor_LName to set
     */
    public void setProfessor_LName(String Professor_LName) {
        this.Professor_LName = Professor_LName;
    }

    /**
     * @return the Professor_CourseLoad
     */
    public int getProfessor_CourseLoad() {
        return Professor_CourseLoad;
    }

    /**
     * @param Professor_CourseLoad the Professor_CourseLoad to set
     */
    public void setProfessor_CourseLoad(int Professor_CourseLoad) {
        this.Professor_CourseLoad = Professor_CourseLoad;
    }

    /**
     * @return the Professor_Username
     */
    public String getProfessor_Username() {
        return Professor_Username;
    }

    /**
     * @param Professor_Username the Professor_Username to set
     */
    public void setProfessor_Username(String Professor_Username) {
        this.Professor_Username = Professor_Username;
    }

    /**
     * @return the Profesor_Department
     */
    public String getProfesor_Department() {
        return Profesor_Department;
    }

    /**
     * @param Profesor_Department the Profesor_Department to set
     */
    public void setProfesor_Department(String Profesor_Department) {
        this.Profesor_Department = Profesor_Department;
    }
    
    /**Input a timeslot into the professor's personal schedule ArrayList. 
     * 
     * 
     * @param TSlotIn Timeslot to be put into professor Schedule
     * @return True if timeslot can be entered, false if the timeslot is occupied
     * @Author Chris Geissler
     */
    public boolean setProfessorOccupied(Timeslot TSlotIn)
    {
        boolean bOccupied = true;
        
        if(ProfessorOccupied.contains(TSlotIn))
        {
            bOccupied = false;
        }
        
        else
        {
            ProfessorOccupied.add(TSlotIn);
        }
        
        return bOccupied;
    }
    
    public ArrayList<Timeslot> getProfessorOccupied()
    {
        return ProfessorOccupied;
    }
    
        /**
     * @return the CoursesEnrolled
     */
    public int getCoursesEnrolled() {
        return CoursesEnrolled;
    }

   
    public void increaseCoursesEnrolled() 
    {
        CoursesEnrolled++;
    }
    
    public void decreaseCoursesEnrolled() 
    {
        CoursesEnrolled--;
    }
    
}
