package org.hbhk.aili.client.core.widget.qtable.element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public class CellImpl<K, V> extends AbstractTableElement<K, V> implements Cell<K, V> {
	private Row<K, V> tableRow;
	
	public CellImpl(QuickTable<K, V> qTable, int row, int column) {
		super(qTable, row, column);
	}
	
	@Override
	public String getPropertyName() {
		return getTable().getPropertyName(getColumn());
	}

	@Override
	public int getColumnIndex() {
		return super.getColumn();
	}

	@Override
	public Object getPropertyValue() {
		return getPropertyValue(getTable().getData().get(getTableRow()), getPropertyName());
	}
	
	private Object getPropertyValue(Object object, String propertyName) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
			Method method = propertyDescriptor.getReadMethod();
			
			return method.invoke(object, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can't read value of property[%s].", propertyName), e);
		}
	}

	@Override
	public Row<K, V> getRow() {
		if (tableRow == null) {
			tableRow = new RowImpl<K, V>(getTable(),getTableRow(),getColumn());
		}
		
		return tableRow;
	}
}
