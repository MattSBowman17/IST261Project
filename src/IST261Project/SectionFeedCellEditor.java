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
public class SectionFeedCellEditor extends AbstractCellEditor implements TableCellEditor
{
    SectionFeedCellComponent feedComponent;

    public SectionFeedCellEditor() 
    {
        feedComponent = new SectionFeedCellComponent();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {
        SectionFeed sections = (SectionFeed)value;
        feedComponent.updateData(sections, false, table, row, column);
        return feedComponent;
    }

    public Object getCellEditorValue() 
    {
        return null;
    }
}
