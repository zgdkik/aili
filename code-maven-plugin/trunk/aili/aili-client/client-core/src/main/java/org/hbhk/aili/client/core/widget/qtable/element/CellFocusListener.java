package org.hbhk.aili.client.core.widget.qtable.element;


public interface CellFocusListener<K, V> {
	boolean focusLosing(Cell<K, V> cell, CellPosition newPosition);
	void focusLost(Cell<K, V> cell);
	void focusGained(Cell<K, V> newCell);
}
