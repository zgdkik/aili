package org.hbhk.aili.client.core.widget.qtable.editor;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.TableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.model.Data;

public interface CellEditorFactory<K, V> {
	CellEditor create(QuickTable<K, V> qTable, Data<V> data,
			TableConfiguration<K, V> tableConfiguration, int columnIndex);
}
