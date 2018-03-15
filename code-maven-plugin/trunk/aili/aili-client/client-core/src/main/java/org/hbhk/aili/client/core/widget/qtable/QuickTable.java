package org.hbhk.aili.client.core.widget.qtable;

import java.util.Collection;

import org.hbhk.aili.client.core.commons.validation.IValidationListener2;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorListener;
import org.hbhk.aili.client.core.widget.qtable.element.CellFocusListener;
import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;
import org.hbhk.aili.client.core.widget.qtable.model.BeanFactory;
import org.hbhk.aili.client.core.widget.qtable.model.Data;


public interface QuickTable<K, V> {
	K getControl();
	
	void setConfiguration(TableConfiguration<K, V> configuration);
	TableConfiguration<K, V> getConfiguration();
	
	int getRowCount();
	int getColumnCount();
	
	void focusOn(int row, int column);
	CellPosition getLastPosition();
	CellPosition getCurrentPosition();
	boolean isCellFocused();
	
	CellEditor getCellEditor();
	boolean isEditing();
	
	void editAt(CellPosition position);
	void editAt(int row, int column);
	
	void addCellEditorListener(CellEditorListener listener);
	void removeCellEditorListener(CellEditorListener listener);
	
	void addCellFocusListener(CellFocusListener<K, V> listener);
	void removeCellFocusListener(CellFocusListener<K, V> listener);
	
	void addValidationListener(IValidationListener2 listener);
	void removeValidationListener(IValidationListener2 listener);
	
	void addRow();
	void addRow(V bean);
	void insertRow(int index, V bean);
	void removeRow(int row);
	
	void setData(Collection<V> data);
	Data<V> getData();
	void setBeanFactory(BeanFactory<V> beanFactory);
	BeanFactory<V> getBeanFactory();
	
	void setFocusMovingStategy(FocusMovingStategy<K> focusMovingStategy);
	
	boolean updateCellValue();
	void moveToNextCell();
	
	int getColumnIndex(String propertyName);
	String getPropertyName(int columnIndex);
	
	boolean validate();
	
	void setMaxRows(int maxRows);
	int getMaxRows();
	
	void addOutOfRowsListener(OutOfRowsRangeListener listener);
	void removeOutOfRowsListener(OutOfRowsRangeListener listener);
}
