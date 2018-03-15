package org.hbhk.aili.client.core.widget.qtable.element;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public interface TableElement<K, V> {
	QuickTable<K, V> getTable();
	CellPosition getPosition();
}
