package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import org.hbhk.aili.client.core.widget.qtable.ColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.FocusMovingStategy;
import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;

public class DefaultFocusMovingStategy<K> implements FocusMovingStategy<K> {
	private QuickTable<K, ?> qTable;
	
	public DefaultFocusMovingStategy(QuickTable<K, ?> qTable) {
		this.qTable = qTable;
	}

	@Override
	public CellPosition previousCell(CellPosition current) {
		if (current.getRow() == 0 && current.getColumn() == 0){
			return null;
		}
			
		
		if (current.getColumn() == 0) {
			current.setRow(current.getRow() - 1);
			current.setColumn(qTable.getColumnCount() - 1);
		} else {
			current.setColumn(current.getColumn() - 1);
		}
		
		if (isFocusable(current.getRow(), current.getColumn())) {
			return current;
		} else {
			return previousCell(current);
		}
	}

	@Override
	public CellPosition nextCell(CellPosition current) {
		if ((current.getRow() == qTable.getRowCount() - 1) &&
				(current.getColumn() == ((QuickTableSwingImpl<?>)qTable).getColumnCount(true) - 1)){
			return null;
		}
			
		
		if (current.getColumn() == ((QuickTableSwingImpl<?>)qTable).getColumnCount(true) - 1) {
			current.setRow(current.getRow() + 1);
			current.setColumn(0);
		} else {
			current.setColumn(current.getColumn() + 1);
		}
		
		if (isFocusable(current.getRow(), current.getColumn())) {
			return current;
		} else {
			return nextCell(current);
		}
	}

	@Override
	public CellPosition previousRow(CellPosition current) {
		if (current.getRow() == 0){
			return null;
		}
			
		current.setRow(current.getRow() - 1);			
		return current;
	}

	@Override
	public CellPosition nextRow(CellPosition current) {
		if (current.getRow() == qTable.getRowCount() - 1){
			return null;
		}
			
		current.setRow(current.getRow() + 1);
		return current;
	}
	
	private boolean isFocusable(int row, int column) {
		if (!isValidCell(row, column)) {
			return false;
		}
		
		ColumnConfiguration<?, ?> columnConfiguration = ((QuickTableSwingImpl<?>)qTable).getColumnConfiguration(column);
		return columnConfiguration.isVisible() && columnConfiguration.isFocusable();
	}
	
	private boolean isValidCell(int row, int column) {
		if (row < 0 || column < 0 || row > (qTable.getRowCount() - 1) ||
				column > (((QuickTableSwingImpl<?>)qTable).getColumnCount(true) - 1)) {
			return false;
		}
		
		return true;
	}
	
}