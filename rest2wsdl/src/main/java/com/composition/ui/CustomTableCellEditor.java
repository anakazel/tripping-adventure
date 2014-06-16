package com.composition.ui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by alexg on 16.06.2014.
 */
public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private JComboBox editor;

    public CustomTableCellEditor(String[] values)
    {
// Create a new Combobox with the array of values.
        editor = new JComboBox(values);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex)
    {

// Set the model data of the table
        if(isSelected)
        {
            editor.setSelectedItem(value);
            TableModel model = table.getModel();
            model.setValueAt(value, rowIndex, colIndex);
        }

        return editor;
    }

    public Object getCellEditorValue()
    {
        return editor.getSelectedItem();
    }
}
