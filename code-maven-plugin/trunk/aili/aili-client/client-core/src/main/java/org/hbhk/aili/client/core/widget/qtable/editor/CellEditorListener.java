package org.hbhk.aili.client.core.widget.qtable.editor;

public interface CellEditorListener {
	void editingStarted(CellEditor editor);
	void stopped(CellEditor editor);
	void canceled(CellEditor editor);
	void locked(CellEditor editor);
	void unlocked(CellEditor editor);
}
