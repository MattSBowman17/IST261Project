/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IST261Project;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Kyle
 */
public class ScheduleFeedCellEditor extends AbstractCellEditor implements TableCellEditor
{
    ScheduleFeedCellComponent feedComponent;

    public ScheduleFeedCellEditor() 
    {
        feedComponent = new ScheduleFeedCellComponent();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {
        ScheduleFeed sections = (ScheduleFeed)value;
        feedComponent.updateData(sections, false, table, row, column);
        return feedComponent;
    }

    public Object getCellEditorValue() 
    {
        return null;
    }
}
