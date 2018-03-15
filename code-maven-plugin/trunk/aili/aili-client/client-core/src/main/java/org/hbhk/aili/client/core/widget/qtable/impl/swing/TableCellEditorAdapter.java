package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.renderer.Renderer;

@SuppressWarnings("serial")
public class TableCellEditorAdapter<V> extends DefaultCellEditor {
	private QuickTable<JTable, V> qTable;
	
	public TableCellEditorAdapter(QuickTable<JTable, V> qTable, JTextField textField) {
		super(textField);
		this.qTable = qTable;
	}
	
	public TableCellEditorAdapter(QuickTable<JTable, V> qTable, JCheckBox checkBox) {
		super(checkBox);
		this.qTable = qTable;
	}
	
	public TableCellEditorAdapter(final QuickTable<JTable, V> qTable, final JComboBox comboBox) {
		super(comboBox);
		delegate = new EditorDelegate() {
			public void setValue(Object value) {
				comboBox.setSelectedItem(value);
			}
			
			public Object getCellEditorValue() {
				return comboBox.getSelectedItem();
			}
			
			public boolean shouldSelectCell(EventObject anEvent) { 
				if (anEvent instanceof MouseEvent) { 
					MouseEvent e = (MouseEvent)anEvent;
					return e.getID() != MouseEvent.MOUSE_DRAGGED;
				}
				return true;
			}
			public boolean stopCellEditing() {
				if (comboBox.isEditable()) {
					//Commit edited value.
					comboBox.actionPerformed(new ActionEvent(
							TableCellEditorAdapter.this, 0, ""));
				}
				
				qTable.updateCellValue();
				
				return super.stopCellEditing();
			}
		};
		
		this.qTable = qTable;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		IConverter inputToValueConverter = ((QuickTableSwingImpl<?>)qTable).getColumnConfiguration(column).getInputToValueConverter();
		if (inputToValueConverter != null) {
			try {
				value = inputToValueConverter.from(value);
			} catch (ConversionException e) {
				throw new RuntimeException("Can't convert value to cell editor input.",e);
			}
		}
		
		delegate.setValue(value);
		
		Renderer renderer = qTable.getConfiguration().getDefaultCellEditorRenderer();
		if (renderer != null) {
			if (renderer.getForeground() != null) {
				editorComponent.setForeground(renderer.getForeground());
			}
			
			if (renderer.getBackground() != null) {
				editorComponent.setBackground(renderer.getBackground());
			}
			
			if (renderer.getBorder() != null) {
				editorComponent.setBorder(renderer.getBorder());
			}
			
			if (renderer.getFont() != null) {
				editorComponent.setFont(renderer.getFont());
			}
		}
		
		return editorComponent;
	}
	
	public void setValueAt(Object value) {
		delegate.setValue(value);
	}
	
	public JComponent getComponent() {
		return editorComponent;
	}
}
