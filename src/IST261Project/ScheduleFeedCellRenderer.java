/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Kyle
 */
public class ScheduleFeedCellRenderer implements TableCellRenderer
{
    ScheduleFeedCellComponent feedComponent;

    public ScheduleFeedCellRenderer() 
    {
        feedComponent = new ScheduleFeedCellComponent();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        ScheduleFeed sections = (ScheduleFeed)value;
        feedComponent.updateData(sections, hasFocus, table, row, column);
        return feedComponent;
    }
}
