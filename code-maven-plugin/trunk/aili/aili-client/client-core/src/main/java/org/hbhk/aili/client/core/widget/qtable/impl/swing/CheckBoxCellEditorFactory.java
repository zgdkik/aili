package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.TableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.model.Data;

public class CheckBoxCellEditorFactory<V> implements CellEditorFactory<JTable, V> {

	@Override
	public CellEditor create(final QuickTable<JTable, V> qTable, Data<V> data,
			TableConfiguration<JTable, V> tableConfiguration, int columnIndex) {
		JTable table = qTable.getControl();
		TableColumn column = table.getColumnModel().getColumn(columnIndex);

		@SuppressWarnings("serial")
		JCheckBox checkBox = new JCheckBox() {
			@Override
			protected void processComponentKeyEvent(KeyEvent e) {
				if (e.getKeyChar() == ' ') {
					setSelected(!isSelected());
					e.consume();
						
					return;
				}
			}
			
			@Override
			protected void processMouseEvent(MouseEvent e) {
				if (e.getID() == MouseEvent.MOUSE_CLICKED) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						setSelected(!isSelected());
					} else {
						if (qTable.updateCellValue()) {
							qTable.getCellEditor().stop();
						} else {
							qTable.getCellEditor().setErrorMode(true);
							qTable.getCellEditor().lock();
						}
					}
				}
				
				e.consume();
			}
		};
		
		TableCellEditorAdapter<V> realCellEditor = new TableCellEditorAdapter<V>(qTable, checkBox);
		column.setCellEditor(realCellEditor);
		
		return qTable.getCellEditor();
	}

}
