package IST261Project;


import java.sql.Time;

public class Timeslot {

    private int Time_ID;

    private Time Time_Start;

    private Time Time_End;

    private String Time_DayID;

    /**
     * @return the Time_ID
     */
    public int getTime_ID() {
        return Time_ID;
    }

    /**
     * @param Time_ID the Time_ID to set
     */
    public void setTime_ID(int Time_ID) {
        this.Time_ID = Time_ID;
    }

    /**
     * @return the Time_Start
     */
    public Time getTime_Start() {
        return Time_Start;
    }

    /**
     * @param Time_Start the Time_Start to set
     */
    public void setTime_Start(Time Time_Start) {
        this.Time_Start = Time_Start;
    }

    /**
     * @return the Time_End
     */
    public Time getTime_End() {
        return Time_End;
    }

    /**
     * @param Time_End the Time_End to set
     */
    public void setTime_End(Time Time_End) {
        this.Time_End = Time_End;
    }

    /**
     * @return the Time_DayID
     */
    public String getTime_DayID() {
        return Time_DayID;
    }

    /**
     * @param Time_DayID the Time_DayID to set
     */
    public void setTime_DayID(String Time_DayID) {
        this.Time_DayID = Time_DayID;
    }
    
}
