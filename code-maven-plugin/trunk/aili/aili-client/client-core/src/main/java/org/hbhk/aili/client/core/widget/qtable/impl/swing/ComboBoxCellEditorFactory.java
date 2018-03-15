package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.TableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.model.Data;

public class ComboBoxCellEditorFactory<V> implements CellEditorFactory<JTable, V> {
	private Object[] items;
	private Object defaultItem;
	
	public ComboBoxCellEditorFactory(Object[] items, Object defaultItem) {
		this.items = items;
		this.defaultItem = defaultItem;
	}

	@Override
	public CellEditor create(QuickTable<JTable, V> qTable, Data<V> data,
			TableConfiguration<JTable, V> tableConfiguration, int columnIndex) {
		@SuppressWarnings("serial")
		JComboBox comboBox = new JComboBox() {
			@Override
			protected void processComponentKeyEvent(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT ||
						e.getKeyCode() == KeyEvent.VK_RIGHT) {
					e.consume();
					
					return;
				}
				
				super.processComponentKeyEvent(e);
			}
		};		
		if (items != null) {
			for (Object item : items) {
				comboBox.addItem(item);
			}
		}
		comboBox.setSelectedItem(defaultItem);
		
		TableCellEditorAdapter<V> realCellEditor = new TableCellEditorAdapter<V>(qTable, comboBox);
		
		JTable table = qTable.getControl();
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		
		column.setCellEditor(realCellEditor);

		return qTable.getCellEditor();
	}

}
