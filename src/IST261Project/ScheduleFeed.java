/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

/**
 *
 * @author Kyle
 */
public class ScheduleFeed {
    String courseName;
    String professorName;
    String classTime;
    String classDay;
    static String[][] DayTimeSlot = new String[][]{
        {"8:00","9:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"9:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"8:00","9:05","10:10","11:15","13:25","14:30","15:45","16:40","18:00"},
        {"9:05","10:35","13:35","15:05","16:35","18:00","","",""},
        {"8:00","9:05","10:10","11:15","13:25","14:30","15:45","16:40",""}
    };
    static char[] dayLetter = new char[]{'M','T','W','R','F'};
    static String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    public ScheduleFeed(String courseName, String professorName, String classTime, String classDay) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.classTime = classTime;
        this.classDay = classDay;
    }
    
    
    public static void fillSchedule(ScheduleFeed[][] sections){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                    sections[i][j] = new ScheduleFeed("", "", DayTimeSlot[j][i], ""); 
            }
        }
    }
    
    public static void addSection(ScheduleFeed course, ScheduleFeed[][] sectionTable){
        char[] courseDays = course.classDay.toCharArray();
        for (int k = 0; k < courseDays.length; k++) {
            for (int i = 0; i < 5; i++) 
            { 
                if(courseDays[k] == dayLetter[i])
                {
                    for (int j = 0; j < 9; j++) 
                    {
                        if(course.classTime.equals(DayTimeSlot[i][j]))
                        {
                            sectionTable[j][i] = new ScheduleFeed(course.courseName, course.professorName, DayTimeSlot[i][j], days[i]);
                        }
                    }
                }
            }
        }
        
        
    }
}
