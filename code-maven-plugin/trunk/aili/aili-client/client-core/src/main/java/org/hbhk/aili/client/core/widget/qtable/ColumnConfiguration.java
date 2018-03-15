package org.hbhk.aili.client.core.widget.qtable;

import java.util.List;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.model.ValueChangeListener;
import org.hbhk.aili.client.core.widget.qtable.renderer.CellRendererFactory;

public interface ColumnConfiguration<K, V> {
	int getWidth();
	int getMaxWidth();
	int getMinWidth();
	boolean isVisible();
	boolean isResizable();
	boolean isFocusable();
	boolean isReadOnly();
	CellEditorFactory<K, V> getCellEditorFactory();
	CellRendererFactory<K, V> getCellRendererFactory();
	IConverter getInputToValueConverter();
	IConverter getValueToLabelConverter();
	String getTitle();
	String getPropertyName();
	List<ValueChangeListener> getValueChangeListeners();
	void addValidator(IValidator validator);
	void removeValidator(IValidator validator);
	List<IValidator> getValidators();
	
	Shortcut[] getCellShortcuts(boolean editing);
}
