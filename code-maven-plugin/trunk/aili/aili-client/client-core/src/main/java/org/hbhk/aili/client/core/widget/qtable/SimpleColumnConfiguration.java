package org.hbhk.aili.client.core.widget.qtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.model.ValueChangeListener;
import org.hbhk.aili.client.core.widget.qtable.renderer.CellRendererFactory;

public class SimpleColumnConfiguration<K, V> implements ColumnConfiguration<K, V> {
	private int width;
	private int minWidth;
	private int maxWidth;
	private boolean visible;
	private boolean resizable;
	private boolean readOnly;
	private CellEditorFactory<K, V> cellEditorFactory;
	private CellRendererFactory<K, V> cellRendererFactoy;
	private String title;
	private boolean focusable;
	private Shortcut[] cellShortcuts;
	private Shortcut[] cellEditorShortcuts;
	private String propertyName;
	private List<ValueChangeListener> valueChangeListeners;
	private IConverter inputToValueConverter;
	private IConverter valueToLabelConverter;
	private List<IValidator> validators;
	
	public static final int DEFAULT_WIDTH = 120;
	public static final int DEFAULT_MIN_WIDTH = 40;
	
	public SimpleColumnConfiguration() {
		width = DEFAULT_WIDTH;
		minWidth = DEFAULT_MIN_WIDTH;
		maxWidth = DEFAULT_WIDTH;
		visible = true;
		resizable = true;
		readOnly = false;
		focusable = true;
		valueChangeListeners = new ArrayList<ValueChangeListener>();
		validators = new ArrayList<IValidator>();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setCellEditorFactory(CellEditorFactory<K, V> cellEditorFactory) {
		this.cellEditorFactory = cellEditorFactory;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getMaxWidth() {
		return maxWidth;
	}

	@Override
	public int getMinWidth() {
		return minWidth;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public boolean isResizable() {
		return resizable;
	}

	@Override
	public boolean isFocusable() {
		return focusable;
	}
	
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public CellEditorFactory<K, V> getCellEditorFactory() {
		return cellEditorFactory;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	public void setShortcuts(Shortcut[] shortcuts, boolean editing) {
		if (editing) {
			//System.arraycopy(shortcuts, 0, cellEditorShortcuts, 0, shortcuts.length);
			cellEditorShortcuts = shortcuts;
		} else {
			//System.arraycopy(shortcuts, 0, cellShortcuts, 0, shortcuts.length);
			cellShortcuts = shortcuts;
		}
	}

	@Override
	public Shortcut[] getCellShortcuts(boolean editing) {
		if (editing) {
			return cellEditorShortcuts;
		} else {
			return cellShortcuts;
		}
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setCellRendererFactory(CellRendererFactory<K, V> cellRendererFactoy) {
		this.cellRendererFactoy = cellRendererFactoy;
	}

	@Override
	public CellRendererFactory<K, V> getCellRendererFactory() {
		return cellRendererFactoy;
	}
	
	@Override
	public List<ValueChangeListener> getValueChangeListeners() {
		return Collections.unmodifiableList(valueChangeListeners);
	}
	
	public void addValueChangeListener(ValueChangeListener listener) {
		valueChangeListeners.add(listener);
	}
	
	public void removeValueChangeListener(ValueChangeListener listener) {
		valueChangeListeners.remove(listener);
	}

	public void setInputToValueConverter(IConverter inputToValueConverter) {
		this.inputToValueConverter = inputToValueConverter;
	}

	public void setValueToLabelConverter(IConverter labelToValueConverter) {
		this.valueToLabelConverter = labelToValueConverter;
	}

	@Override
	public IConverter getInputToValueConverter() {
		return inputToValueConverter;
	}

	@Override
	public IConverter getValueToLabelConverter() {
		return valueToLabelConverter;
	}

	@Override
	public void addValidator(IValidator validator) {
		validators.add(validator);
	}

	@Override
	public List<IValidator> getValidators() {
		return Collections.unmodifiableList(validators);
	}

	@Override
	public void removeValidator(IValidator validator) {
		validators.remove(validator);
	}

}
