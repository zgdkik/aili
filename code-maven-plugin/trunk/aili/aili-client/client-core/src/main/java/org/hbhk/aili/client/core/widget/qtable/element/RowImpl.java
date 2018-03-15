package org.hbhk.aili.client.core.widget.qtable.element;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public class RowImpl<K, V> extends AbstractTableElement<K, V> implements Row<K, V> {
	public RowImpl(QuickTable<K, V> qTable, int row, int column) {
		super(qTable, row, column);
	}

	@Override
	public V getBean() {
		return getTable().getData().get(getTableRow());
	}

	@Override
	public int getIndex() {
		return super.getTableRow();
	}
}
