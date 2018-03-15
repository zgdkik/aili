package org.hbhk.aili.client.core.widget.qtable.element;

import java.util.List;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public class TableImpl<K, V> extends AbstractTableElement<K, V> implements Table<K, V> {

	public TableImpl(QuickTable<K, V> qTable, int row, int column) {
		super(qTable, row, column);
	}

	@Override
	public List<V> getData() {
		return getTable().getData().toList();
	}

}
