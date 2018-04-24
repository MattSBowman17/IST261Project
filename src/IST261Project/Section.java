package IST261Project;

public class Section {

    private int Section_ID;

    private int ProfessorCourse_ProfessorCourseID;

    private int RoomTime_RoomTimeID;

    private int Section_SectionNumber;
    
    private int SectNumStudents;
   
    private int intCourse;
    
    private boolean bProblem = false;

    
      
   
    //Section Constructor
//    public Section(int Section_ID, int SectNumStudents, int ProfessorCourse_ProfessorCourseID)
//    {
//        this.Section_ID = Section_ID;
//        this.SectNumStudents = SectNumStudents;
//        this.ProfessorCourse_ProfessorCourseID = ProfessorCourse_ProfessorCourseID;
//    }
    
    public Section(int Section_ID, int SectNumStudents, int intCourse)
    {
        this.Section_ID = Section_ID;
        this.SectNumStudents = SectNumStudents;
        this.intCourse = intCourse; 
    }
    /**
     * @return the Section_ID
     */
    public int getSection_ID() {
        return Section_ID;
    }

    /**
     * @param Section_ID the Section_ID to set
     */
    public void setSection_ID(int Section_ID) {
        this.Section_ID = Section_ID;
    }

    /**
     * @return the ProfessorCourse_ProfessorCourseID
     */
    public int getProfessorCourse_ProfessorCourseID() {
        return ProfessorCourse_ProfessorCourseID;
    }

    /**
     * @param ProfessorCourse_ProfessorCourseID the ProfessorCourse_ProfessorCourseID to set
     */
    public void setProfessorCourse_ProfessorCourseID(int ProfessorCourse_ProfessorCourseID) {
        this.ProfessorCourse_ProfessorCourseID = ProfessorCourse_ProfessorCourseID;
    }

    /**
     * @return the RoomTime_RoomTimeID
     */
    public int getRoomTime_RoomTimeID() {
        return RoomTime_RoomTimeID;
    }

    /**
     * @param RoomTime_RoomTimeID the RoomTime_RoomTimeID to set
     */
    public void setRoomTime_RoomTimeID(int RoomTime_RoomTimeID) {
        this.RoomTime_RoomTimeID = RoomTime_RoomTimeID;
    }

    /**
     * @return the Section_SectionNumber
     */
    public int getSection_SectionNumber() {
        return Section_SectionNumber;
    }

    /**
     * @param Section_SectionNumber the Section_SectionNumber to set
     */
    public void setSection_SectionNumber(int Section_SectionNumber) {
        this.Section_SectionNumber = Section_SectionNumber;
    }

    /**
     * @return the SectNumStudents
     */
    public int getSectNumStudents() {
        return SectNumStudents;
    }

    /**
     * @param SectNumStudents the SectNumStudents to set
     */
    public void setSectNumStudents(int SectNumStudents) {
        this.SectNumStudents = SectNumStudents;
    }

    /**
     * @return the intCourse
     */
    public int getIntCourse() {
        return intCourse;
    }

    /**
     * @param intCourse the intCourse to set
     */
    public void setIntCourse(int intCourse) {
        this.intCourse = intCourse;
    }

    public void setProblem()
    {
        bProblem = !bProblem;
    }
}