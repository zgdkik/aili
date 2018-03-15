package org.hbhk.aili.client.core.widget.qtable.renderer;

import org.hbhk.aili.client.core.widget.qtable.ColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.model.Data;

public interface CellRendererFactory<K, V> {
	void install(QuickTable<K, V> qTable, Data<V> data,
			ColumnConfiguration<K, V> configuration, int columnIndex);
}
