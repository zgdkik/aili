package org.hbhk.aili.client.core.widget.qtable;

import java.util.List;

import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.widget.qtable.renderer.Renderer;


public interface TableConfiguration<K, V> {
	List<ColumnConfiguration<K, V>> getColumnConfigurations();
	
	int getRowHeight();
	
	Renderer getDefaultRenderer();
	Renderer getSelectedCellRenderer();
	Renderer getSelectedRowRenderer();
	Renderer getSelectedColumnRenderer();
	Renderer getDefaultCellEditorRenderer();
	Renderer getCellEditorErrorModeRenderer();
	
	boolean isRowNumberColumnVisible();
	int getRowNumberColumnStart();
	
	Shortcut[] getShortcuts();
	
	List<IValidator> getRowValidators();
	List<IValidator> getTableValidators();
}
