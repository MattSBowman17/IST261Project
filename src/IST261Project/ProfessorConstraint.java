package IST261Project;

public class ProfessorConstraint {

    private int ProfessorConstraint_ID;

    private int Professor_Professor_ID;

    private int Time_Time_ID;

    // ProfessorConstraint Constructor
    public ProfessorConstraint(int Professor_Professor_ID, int Time_Time_ID)
    {
        this.Professor_Professor_ID = Professor_Professor_ID;
        this.Time_Time_ID = Time_Time_ID;
    }
    
    /**
     * @return the ProfessorConstraint_ID
     */
    public int getProfessorConstraint_ID() {
        return ProfessorConstraint_ID;
    }

    /**
     * @param ProfessorConstraint_ID the ProfessorConstraint_ID to set
     */
    public void setProfessorConstraint_ID(int ProfessorConstraint_ID) {
        this.ProfessorConstraint_ID = ProfessorConstraint_ID;
    }

    /**
     * @return the Professor_Professor_ID
     */
    public int getProfessor_Professor_ID() {
        return Professor_Professor_ID;
    }

    /**
     * @param Professor_Professor_ID the Professor_Professor_ID to set
     */
    public void setProfessor_Professor_ID(int Professor_Professor_ID) {
        this.Professor_Professor_ID = Professor_Professor_ID;
    }

    /**
     * @return the Time_Time_ID
     */
    public int getTime_Time_ID() {
        return Time_Time_ID;
    }

    /**
     * @param Time_Time_ID the Time_Time_ID to set
     */
    public void setTime_Time_ID(int Time_Time_ID) {
        this.Time_Time_ID = Time_Time_ID;
    }
    
}
