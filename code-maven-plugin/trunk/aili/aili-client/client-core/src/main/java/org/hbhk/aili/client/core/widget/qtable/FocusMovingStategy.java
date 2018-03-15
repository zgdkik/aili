package org.hbhk.aili.client.core.widget.qtable;

import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;

public interface FocusMovingStategy<K> {
	CellPosition previousCell(CellPosition current);
	CellPosition nextCell(CellPosition current);
	CellPosition previousRow(CellPosition current);
	CellPosition nextRow(CellPosition current);
}
