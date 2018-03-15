package org.hbhk.aili.client.core.widget.qtable.editor;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;

public interface CellEditor {
	void startEditing();
	void stop();
	void cancel();
	
	void setValue(Object value);
	Object getValue();
	
	Object getControl();
	
	void lock();
	void unlock();
	boolean isLocked();
	
	void setErrorMode(boolean errorMode);
	boolean isErrorMode();
	
	int getRow();
	int getColumn();
	
	QuickTable<?, ?> getTable();
}
