package org.hbhk.aili.client.core.widget.qtable.element;

public interface Cell<K, V> extends TableElement<K, V> {
	String getPropertyName();
	Object getPropertyValue();
	int getColumnIndex();
	Row<K, V> getRow();
}
