package org.hbhk.aili.client.core.widget.qtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.widget.qtable.renderer.Renderer;

public class SimpleTableConfiguration<K, V> implements TableConfiguration<K, V> {
	private List<ColumnConfiguration<K, V>> columnConfigurations;
	private int rowHeight;
	private Renderer defaultRenderer;
	private Renderer selectedCellRenderer;
	private Renderer selectedRowRenderer;
	private Renderer selectedColumnRenderer;
	private Renderer defaultCellEditorRenderer;
	private Renderer errorStatusCellEditorRenderer;
	
	private boolean rowNumberColumnVisible;
	private int rowNumberColumnStart;
	private Shortcut[] shortcuts;
	
	private List<IValidator> rowValidators;
	private List<IValidator> tableValidators;
	
	public static final int DEFAULT_ROW_HEIGHT = 40;
	public static final int DEFAULT_ROW_START_NUMBER = 1;
	
	public SimpleTableConfiguration() {
		rowHeight = DEFAULT_ROW_HEIGHT;
		rowNumberColumnVisible = false;
		rowNumberColumnStart = DEFAULT_ROW_START_NUMBER;
		rowValidators = new ArrayList<IValidator>();
		tableValidators = new ArrayList<IValidator>();
	}

	public void setColumnConfigurations(List<ColumnConfiguration<K, V>> columnConfigurations) {
		this.columnConfigurations = columnConfigurations;
	}
	
	@Override
	public List<ColumnConfiguration<K, V>> getColumnConfigurations() {
		if (rowNumberColumnVisible) {
			return Collections.unmodifiableList(columnConfigurations.subList(1,
					columnConfigurations.size()));
		} else {
			return Collections.unmodifiableList(columnConfigurations);
		}
	}
	
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	@Override
	public int getRowHeight() {
		return rowHeight;
	}
	
	public void setDefaultRenderer(Renderer renderer) {
		this.defaultRenderer = renderer;
	}

	@Override
	public Renderer getDefaultRenderer() {
		return defaultRenderer;
	}
	
	public void setSelectedCellRenderer(Renderer renderer) {
		this.selectedCellRenderer = renderer;
	}

	@Override
	public Renderer getSelectedCellRenderer() {
		return selectedCellRenderer;
	}
	
	public void setSelectedRowRenderer(Renderer renderer) {
		this.selectedRowRenderer = renderer;
	}

	@Override
	public Renderer getSelectedRowRenderer() {
		return selectedRowRenderer;
	}

	public void setSelectedColumnRenderer(Renderer renderer) {
		this.selectedColumnRenderer = renderer;
	}
	
	@Override
	public Renderer getSelectedColumnRenderer() {
		return selectedColumnRenderer;
	}
	
	public void setRowNumberColumnStart(int rowStartNumber) {
		this.rowNumberColumnStart = rowStartNumber;
	}
	
	public void setRowNumberColumnVisible(boolean rowNumberColumnVisible) {
		this.rowNumberColumnVisible = rowNumberColumnVisible;
	}

	@Override
	public boolean isRowNumberColumnVisible() {
		return rowNumberColumnVisible;
	}

	@Override
	public int getRowNumberColumnStart() {
		return rowNumberColumnStart;
	}
	
	public void setShortcuts(Shortcut[] shortcuts) {
		this.shortcuts = shortcuts;
	}

	@Override
	public Shortcut[] getShortcuts() {
		return shortcuts;
	}
	
	public Renderer getDefaultCellEditorRenderer() {
		return defaultCellEditorRenderer;
	}

	public void setDefaultCellEditorRenderer(Renderer defaultCellEditorRenderer) {
		this.defaultCellEditorRenderer = defaultCellEditorRenderer;
	}

	public Renderer getCellEditorErrorModeRenderer() {
		return errorStatusCellEditorRenderer;
	}

	public void setErrorStatusCellEditorRenderer(
			Renderer errorStatusCellEditorRenderer) {
		this.errorStatusCellEditorRenderer = errorStatusCellEditorRenderer;
	}

	@Override
	public List<IValidator> getRowValidators() {
		return Collections.unmodifiableList(rowValidators);
	}
	
	public void addRowValidator(IValidator validator) {
		rowValidators.add(validator);
	}
	
	public void removeRowValidator(IValidator validator) {
		rowValidators.remove(validator);
	}
	
	public void addTableValidator(IValidator validator) {
		tableValidators.add(validator);
	}
	
	public void removeTableValidator(IValidator validator) {
		tableValidators.remove(validator);
	}

	@Override
	public List<IValidator> getTableValidators() {
		return Collections.unmodifiableList(tableValidators);
	}
	
	public List<ColumnConfiguration<K, V>> getInternalColumnConfigurations() {
		return columnConfigurations;
	}

}
