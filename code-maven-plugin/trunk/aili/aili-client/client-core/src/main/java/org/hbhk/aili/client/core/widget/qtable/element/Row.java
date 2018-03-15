package org.hbhk.aili.client.core.widget.qtable.element;

public interface Row<K, V> extends TableElement<K, V> {
	V getBean();
	int getIndex();
}
