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
public class ScheduleFeedCellComponent extends JPanel{
    ScheduleFeed feed;
    JLabel course;
    JLabel professor;
    JLabel time;

  public ScheduleFeedCellComponent() {
    course = new JLabel();
    professor = new JLabel();
    time = new JLabel();
    add(course);
    add(professor);
    add(time);
  }
  
  public ScheduleFeedCellComponent(String courseName, String professorName, String classTime) {
    course = new JLabel(courseName);
    professor = new JLabel(professorName);
    time = new JLabel(classTime);
    add(course);
    add(professor);
    add(time);
  }

  public void updateData(ScheduleFeed feed, boolean isSelected, JTable table) {
    this.feed = feed;

    course.setText("" + feed.courseName);
    professor.setText("" + feed.professorName);
    time.setText("" + feed.classTime);
    if (isSelected) {
      setBackground(table.getSelectionBackground());
    }else{
      setBackground(table.getBackground());
    }
  }
}
