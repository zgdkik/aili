package org.hbhk.aili.client.core.widget.qtable.element;

public class CellPosition {
	public final static CellPosition NONE = new CellPosition(-1, -1);
	private int row;
	private int column;
	
	public CellPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
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
	
	@Override
	public String toString() {
		return String.format("Cell Position[%d, %d]", row, column);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof CellPosition) {
			CellPosition other = (CellPosition)obj;
			return this.row == other.row && this.column == other.column;
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	
}
