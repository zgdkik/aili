package org.hbhk.aili.client.core.widget.qtable.element;

import java.util.List;

public interface Table<K, V> extends TableElement<K, V> {
	List<V> getData();
}
