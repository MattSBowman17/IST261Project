/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Kyle
 */
public class SectionFeedCellComponent extends JPanel{
    SectionFeed sections;
    JLabel course;
    JLabel professor;
    JLabel time;

  public SectionFeedCellComponent() {
    course = new JLabel();
    professor = new JLabel();
    time = new JLabel();
    add(time);
    add(course);
    add(professor);
    
  }
  
  public SectionFeedCellComponent(String courseName, String professorName, String classTime) {
    course = new JLabel(courseName);
    professor = new JLabel(professorName);
    time = new JLabel(classTime);
    add(time);
    add(course);
    add(professor);
    
  }

  public void updateData(SectionFeed sections, boolean hasFocus, JTable table, int row, int column) {
    this.sections = sections;
    if(sections != null)
    {
    course.setText("" + sections.courseName);
    professor.setText("" + sections.professorName);
    time.setText("" + sections.classTime);
    }
   
  }
}
