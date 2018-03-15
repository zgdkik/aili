package org.hbhk.aili.client.core.widget.qtable.adapter;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public interface ConfigurationAdapter<K, V> {
	void configure();
	QuickTable<K, V> getTable();
}
