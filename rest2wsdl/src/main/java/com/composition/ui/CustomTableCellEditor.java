package com.composition.ui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * @author alexg
 */
public final class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JComboBox editor;

    public CustomTableCellEditor(String[] values) {
        editor = new JComboBox(values);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int colIndex) {

        if (isSelected) {
            editor.setSelectedItem(value);
            TableModel model = table.getModel();
            model.setValueAt(value, rowIndex, colIndex);
        }

        return editor;
    }

    public Object getCellEditorValue() {
        return editor.getSelectedItem();
    }
}
