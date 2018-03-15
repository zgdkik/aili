package org.hbhk.aili.client.core.widget.qtable.element;

public class CellFocusAdapter<K, V> implements CellFocusListener<K, V> {

	@Override
	public boolean focusLosing(Cell<K, V> cell, CellPosition newPosition) {
		return true;
	}

	@Override
	public void focusLost(Cell<K, V> cell) {}

	@Override
	public void focusGained(Cell<K, V> newCell) {}

}
