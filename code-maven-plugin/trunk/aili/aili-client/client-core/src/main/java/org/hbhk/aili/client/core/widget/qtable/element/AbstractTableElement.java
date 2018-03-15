package org.hbhk.aili.client.core.widget.qtable.element;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public class AbstractTableElement<K, V> implements TableElement<K, V> {
	private QuickTable<K, V> qTable;
	private int row;
	private int column;
	
	public AbstractTableElement(QuickTable<K, V> qTable, int row, int column) {
		this.qTable = qTable;
		this.row = row;
		this.column = column;
	}

	@Override
	public QuickTable<K, V> getTable() {
		return qTable;
	}

	@Override
	public CellPosition getPosition() {
		return new CellPosition(row, column);
	}

	public int getTableRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	

}
