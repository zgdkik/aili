package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.TableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.element.CellImpl;
import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;
import org.hbhk.aili.client.core.widget.qtable.model.Data;
import org.hbhk.aili.client.core.widget.qtable.model.ValueChangeListener;

public class GenericCellEditorFactory<V> implements CellEditorFactory<JTable, V> {
	@Override
	public CellEditor create(QuickTable<JTable, V> qTable, Data<V> data,
			TableConfiguration<JTable, V> tableConfiguration, int columnIndex) {
		JTable table = qTable.getControl();
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		
		TableCellEditorAdapter<V> realCellEditor = new TableCellEditorAdapter<V>(qTable, new ValueChangeNotifiedTextField<V>(qTable));
		column.setCellEditor(realCellEditor);
		
		return qTable.getCellEditor();
	}
	
	@SuppressWarnings("serial")
	private class ValueChangeNotifiedTextField<T> extends JTextField {
		private QuickTable<JTable, T> qTable;
		
		public ValueChangeNotifiedTextField(QuickTable<JTable, T> qTable) {
			this.qTable = qTable;
		}
		@Override
		protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
					int condition, boolean pressed) {
			String oldText = getText();
			boolean processed = super.processKeyBinding(ks, e, condition, pressed);
			
			if (e.getID() == KeyEvent.KEY_TYPED) {
				List<ValueChangeListener> listeners = qTable.getConfiguration().getColumnConfigurations().get(
						qTable.getCurrentPosition().getColumn()).getValueChangeListeners();
				CellPosition position = qTable.getCurrentPosition();
				for (ValueChangeListener listener : listeners) {
					listener.valueChanged(new CellImpl<JTable, T>(qTable, position.getRow(), position.getColumn()),
							oldText, getText());
				}
			}
			
			return processed;
		}
	}
}
