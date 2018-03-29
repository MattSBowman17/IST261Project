package IST261Project;

public class Course {

    private int Course_ID;

    private int Course_Credits;

    private int Course_EstStudents;

    private String Course_Name;

    private String Course_Major;

    private String Course_Level;

    /** Test version of the Course creator
     * 
     * @param Course_ID
     * @param Course_EstStudents 
     */
    
    public Course(int Course_ID, int Course_EstStudents)
    {
        this.Course_ID = Course_ID;
        this.Course_EstStudents = Course_EstStudents;
    }
    
    /**
     * @return the Course_ID
     */
    public int getCourse_ID() {
        return Course_ID;
    }

    /**
     * @param Course_ID the Course_ID to set
     */
    public void setCourse_ID(int Course_ID) {
        this.Course_ID = Course_ID;
    }

    /**
     * @return the Course_Credits
     */
    public int getCourse_Credits() {
        return Course_Credits;
    }

    /**
     * @param Course_Credits the Course_Credits to set
     */
    public void setCourse_Credits(int Course_Credits) {
        this.Course_Credits = Course_Credits;
    }

    /**
     * @return the Course_EstStudents
     */
    public int getCourse_EstStudents() {
        return Course_EstStudents;
    }

    /**
     * @param Course_EstStudents the Course_EstStudents to set
     */
    public void setCourse_EstStudents(int Course_EstStudents) {
        this.Course_EstStudents = Course_EstStudents;
    }

    /**
     * @return the Course_Name
     */
    public String getCourse_Name() {
        return Course_Name;
    }

    /**
     * @param Course_Name the Course_Name to set
     */
    public void setCourse_Name(String Course_Name) {
        this.Course_Name = Course_Name;
    }

    /**
     * @return the Course_Major
     */
    public String getCourse_Major() {
        return Course_Major;
    }

    /**
     * @param Course_Major the Course_Major to set
     */
    public void setCourse_Major(String Course_Major) {
        this.Course_Major = Course_Major;
    }

    /**
     * @return the Course_Level
     */
    public String getCourse_Level() {
        return Course_Level;
    }

    /**
     * @param Course_Level the Course_Level to set
     */
    public void setCourse_Level(String Course_Level) {
        this.Course_Level = Course_Level;
    }
    
}
