package IST261Project;

public class ProfessorCourse {

    private int ProfessorCourseID;

    private int Professor_ProfessorID;

    private int Course_CourseID;

    private int Package_Package_ID;
    

    public ProfessorCourse(int ProfessorCourseID, int Professor_ProfessorID, int Course_CourseID, int Package_Package_ID)

    {
        this.ProfessorCourseID = ProfessorCourseID;
        this.Professor_ProfessorID = Professor_ProfessorID;
        this.Course_CourseID = Course_CourseID;
        this.Package_Package_ID = Package_Package_ID;
    }
    
    /**
     * @return the Professor_ProfessorID
     */
    public int getProfessor_ProfessorID()
    {
        return Professor_ProfessorID;
    }

    /**
     * @param Professor_ProfessorID the Professor_ProfessorID to set
     */
    public void setProfessor_ProfessorID(int Professor_ProfessorID)
    {
        this.Professor_ProfessorID = Professor_ProfessorID;
    }

    /**
     * @return the Course_CourseID
     */
    public int getCourse_CourseID()
    {
        return Course_CourseID;
    }

    /**
     * @param Course_CourseID the Course_CourseID to set
     */
    public void setCourse_CourseID(int Course_CourseID) 
    {
        this.Course_CourseID = Course_CourseID;
    }

    /**
     * @return the Package_Package_ID
     */
    public int getPackage_Package_ID()
    {
        
        return Package_Package_ID;
    }

    /**
     * @param Package_Package_ID the Package_Package_ID to set
     */
    public void setPackage_Package_ID(int Package_Package_ID) 
    {
        this.Package_Package_ID = Package_Package_ID;
    }

    /**
     * @return the ProfessorCourseID
     */
    public int getProfessorCourseID() {
        return ProfessorCourseID;
    }

    /**
     * @param ProfessorCourseID the ProfessorCourseID to set
     */
    public void setProfessorCourseID(int ProfessorCourseID) {
        this.ProfessorCourseID = ProfessorCourseID;
    }
    
    
    
}
