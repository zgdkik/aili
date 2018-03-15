package org.hbhk.aili.client.core.widget.qtable.adapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.KeyStroke;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.validation.IValidationListener2;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationErrorsEvent;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.widget.qtable.ColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.OutOfRowsRangeListener;
import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.Shortcut;
import org.hbhk.aili.client.core.widget.qtable.SimpleColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.SimpleTableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.BeanType;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorCanceled;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorEditingStarted;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorErrorModeRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorFactoryProvider;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorLocked;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorShortcut;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorStopped;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellEditorUnlocked;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellFocusGained;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellFocusLosing;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellFocusLost;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellRendererFactoryProvider;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellShortcut;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CellValidator;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnCount;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnFocusable;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnMaxWidth;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnMinWidth;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnPropertyName;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnReadOnly;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnResizable;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnTitle;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnVisible;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ColumnWidth;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.CreateBean;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.DataProvider;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.DefaultCellEditorRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.DefaultCellRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.InputToValueConverter;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.MaxRows;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.MaxRowsExceed;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.RowHeight;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.RowNumberColumnStart;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.RowNumberColumnVisible;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.RowValidator;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.SelectedCellRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.SelectedColumnRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.SelectedRowRenderer;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.Signature;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.Signatures;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.TableShortcut;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.TableValidator;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ValidationListener;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ValueChanged;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ValueChanging;
import org.hbhk.aili.client.core.widget.qtable.adapter.annotations.ValueToLabelConverter;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorListener;
import org.hbhk.aili.client.core.widget.qtable.element.Cell;
import org.hbhk.aili.client.core.widget.qtable.element.CellFocusListener;
import org.hbhk.aili.client.core.widget.qtable.element.CellImpl;
import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;
import org.hbhk.aili.client.core.widget.qtable.element.Row;
import org.hbhk.aili.client.core.widget.qtable.element.RowImpl;
import org.hbhk.aili.client.core.widget.qtable.element.Table;
import org.hbhk.aili.client.core.widget.qtable.element.TableImpl;
import org.hbhk.aili.client.core.widget.qtable.model.BeanFactory;
import org.hbhk.aili.client.core.widget.qtable.model.Data;
import org.hbhk.aili.client.core.widget.qtable.model.ValueChangeAdapter;
import org.hbhk.aili.client.core.widget.qtable.renderer.CellRendererFactory;
import org.hbhk.aili.client.core.widget.qtable.renderer.Renderer;

public class TableDescriptorAdapter<K, V> implements ConfigurationAdapter<K, V> {
	private QuickTable<K, V> qTable;
	private TableDescriptor<K, V> descriptor;
	private Map<Class<?>, List<AdapterMethod>> adapterMethods;
	private Map<String, Integer> propertyNameToColumns;
	
	public TableDescriptorAdapter(QuickTable<K, V> qTable, TableDescriptor<K, V> descriptor) {
		this.qTable = qTable;
		this.descriptor = descriptor;
		adapterMethods = new HashMap<Class<?>, List<AdapterMethod>>();
		propertyNameToColumns = new HashMap<String, Integer>();
	}

	@Override
	public void configure() {
		scanAdapterMethods();
		configureQTable();
	}

	private void configureQTable() {
		configureTable();
		configureTableConfiguration();
		configureColumnConfigurations();
		configureData();
	}


	private void configureTable() {
		configureBeanFactory();
		configureCellEditorListeners();
		configureCellFocusListeners();
		configureValidationListeners();
		configureMaxRows();
	}
	
	private void setRowNumberVisible(SimpleTableConfiguration<?, ?> tableConfiguration) {
		AdapterMethod rowNumberVisibleMethod = getSingleAdapterMethod(RowNumberColumnVisible.class);
		if (rowNumberVisibleMethod == null) {
			return;
		}
		
		tableConfiguration.setRowNumberColumnVisible((Boolean)rowNumberVisibleMethod.invoke(descriptor, new Object[0]));
		if (!tableConfiguration.isRowNumberColumnVisible()){
			return;
		}
		
		AdapterMethod rowNumberColumnStart = getSingleAdapterMethod(RowNumberColumnStart.class);
		if (rowNumberColumnStart == null){
			return;
		}
			
		
		tableConfiguration.setRowNumberColumnStart((Integer)rowNumberColumnStart.invoke(descriptor, new Object[0]));
	}

	private void configureMaxRows() {
		AdapterMethod maxRowsMethod = getSingleAdapterMethod(MaxRows.class);
		if (maxRowsMethod == null) {
			return;
		}
		
		qTable.setMaxRows((Integer)maxRowsMethod.invoke(descriptor, new Object[0]));
		final List<AdapterMethod> maxRowsExceedMethods = getAdapterMethods(MaxRowsExceed.class);
		if (maxRowsExceedMethods.size() > 0) {
			qTable.addOutOfRowsListener(new OutOfRowsRangeListener() {
				
				@Override
				public void maxRowsExceed(int maxRows) {
					for (AdapterMethod maxRowsExceedMethod : maxRowsExceedMethods) {
						maxRowsExceedMethod.invoke(descriptor, new Object[] {qTable.getMaxRows()});
					}
				}
			});
		}
	}

	private void configureCellEditorListeners() {
		qTable.addCellEditorListener(new CellEditorListener() {
			
			@Override
			public void unlocked(CellEditor editor) {
				for (AdapterMethod method : getAdapterMethods(CellEditorUnlocked.class)) {
					method.invoke(descriptor, getParameters(new Object[] {editor},
							method.getAdditionalParamTypes()));
				}
			}
			
			@Override
			public void stopped(CellEditor editor) {
				for (AdapterMethod method : getAdapterMethods(CellEditorStopped.class)) {
					method.invoke(descriptor, getParameters(new Object[] {editor},
							method.getAdditionalParamTypes()));
				}
			}
			
			@Override
			public void locked(CellEditor editor) {
				for (AdapterMethod method : getAdapterMethods(CellEditorLocked.class)) {
					method.invoke(descriptor, getParameters(new Object[] {editor},
							method.getAdditionalParamTypes()));
				}
				
			}
			
			@Override
			public void editingStarted(CellEditor editor) {
				for (AdapterMethod method : getAdapterMethods(CellEditorEditingStarted.class)) {
					method.invoke(descriptor, getParameters(new Object[] {editor},
							method.getAdditionalParamTypes()));
				}
			}
			
			@Override
			public void canceled(CellEditor editor) {
				for (AdapterMethod method : getAdapterMethods(CellEditorCanceled.class)) {
					method.invoke(descriptor, getParameters(new Object[] {editor},
							method.getAdditionalParamTypes()));
				}
			}
		});
	}
	
	protected Object[] getParameters(Object[] params, Class<?>[] additionalParamTypes) {
		if (additionalParamTypes.length == 0){
			return params;
		}
			
		
		Object[] allParams = new Object[params.length + additionalParamTypes.length];
		
		for (int i = 0; i < params.length; i++) {
			allParams[i] = params[i];
		}
		
		for (int i = params.length; i < allParams.length; i++) {
			allParams[i] = getParameter(additionalParamTypes[i - params.length]);
		}
		
		return allParams;
	}

	private Object getParameter(Class<?> paramType) {
		if (Cell.class.equals(paramType)) {
			return new CellImpl<K, V>(qTable, qTable.getCurrentPosition().getRow(),
					qTable.getCurrentPosition().getColumn());
		} else if(Row.class.equals(paramType)) {
			return new RowImpl<K, V>(qTable, qTable.getCurrentPosition().getRow(),
					qTable.getCurrentPosition().getColumn());
		} else if (Table.class.equals(paramType)) {
			return new TableImpl<K, V>(qTable, qTable.getCurrentPosition().getRow(),
					qTable.getCurrentPosition().getColumn());
		} else if (Data.class.equals(paramType)) {
			return qTable.getData();
		} else if (QuickTable.class.equals(paramType)) {
			return qTable;
		} else {
			throw new IllegalArgumentException(String.format("Unsupported optional paramter type %s", paramType));
		}
	}

	private void configureCellFocusListeners() {
		qTable.addCellFocusListener(new CellFocusListener<K, V>() {

			@Override
			public boolean focusLosing(Cell<K, V> cell, CellPosition newPosition) {
				boolean doit = true;
				for (AdapterMethod method : getAdapterMethods(CellFocusLosing.class)) {
					Boolean result = (Boolean)method.invoke(descriptor, getParameters(new Object[] {cell, newPosition},
							method.getAdditionalParamTypes()));
					if (Boolean.FALSE.equals(result)) {
						doit = false;
					}
				}
				
				return doit;
			}

			@Override
			public void focusLost(Cell<K, V> cell) {
				for (AdapterMethod method : getAdapterMethods(CellFocusLost.class)) {
					method.invoke(descriptor, getParameters(new Object[] {cell},
							method.getAdditionalParamTypes()));
				}
			}

			@Override
			public void focusGained(Cell<K, V> newCell) {
				for (AdapterMethod method : getAdapterMethods(CellFocusGained.class)) {
					method.invoke(descriptor, getParameters(new Object[] {newCell},
							method.getAdditionalParamTypes()));
				}
			}
			
		});
	}
	
	private void configureValidationListeners() {
		qTable.addValidationListener(new IValidationListener2() {
			
			@Override
			public void validationError(ValidationErrorsEvent e) {
				for (AdapterMethod method : getAdapterMethods(ValidationListener.class)) {
					method.invoke(descriptor, getParameters(new Object[] {e},
							method.getAdditionalParamTypes()));
				}
			}
		});
		
	}
	
	private void configureBeanFactory() {
		final AdapterMethod beanTypeMethod = getSingleAdapterMethod(BeanType.class);
		final AdapterMethod createBeanMethod = getSingleAdapterMethod(CreateBean.class);
		
		if (beanTypeMethod == null) {
			throw new RuntimeException("No bean type method found. Please provide one.");
		}
		
		qTable.setBeanFactory(new BeanFactory<V>() {

			@SuppressWarnings("unchecked")
			@Override
			public V create() {
				if (createBeanMethod != null) {
					return (V)createBeanMethod.invoke(descriptor, new Object[0]);
				} else {
					try {
						return (V)getType().newInstance();
					} catch (Exception e) {
						throw new RuntimeException("Can't create bean. Please provide a create bean method.", e);
					}
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public Class<V> getType() {
				return (Class<V>)beanTypeMethod.invoke(descriptor, new Object[0]);
			}
			
		});		
	}
	
	private AdapterMethod getSingleAdapterMethod(Class<?> annotationType) {
		List<AdapterMethod> methods = adapterMethods.get(annotationType);
		if (methods == null || methods.size() == 0){
			return null;
		}
			
		if (methods.size() > 1) {
			throw new RuntimeException(String.format("Reduplicated annotation type %s. Only once allowed.", annotationType));
		}
		
		return methods.get(0);
	}
	
	private List<AdapterMethod> getAdapterMethods(Class<?> annotationType) {
		List<AdapterMethod> adapterMethodList = adapterMethods.get(annotationType);
		if (adapterMethodList == null) {
			return new ArrayList<AdapterMethod>();
		}
		
		return adapterMethodList;
	}
	
	private void configureTableConfiguration() {
		SimpleTableConfiguration<K, V> tableConfiguration = new SimpleTableConfiguration<K, V>();
		
		setRowHeight(tableConfiguration);
		setTableShortcuts(tableConfiguration);
		setRowAndTableValidators(tableConfiguration);
		setRenderers(tableConfiguration);
		setRowNumberVisible(tableConfiguration);
		
		qTable.setConfiguration(tableConfiguration);
	}

	private void setRenderers(SimpleTableConfiguration<K, V> tableConfiguration) {
		tableConfiguration.setDefaultRenderer(getRenderer(DefaultCellRenderer.class));
		tableConfiguration.setDefaultCellEditorRenderer(getRenderer(DefaultCellEditorRenderer.class));
		tableConfiguration.setSelectedCellRenderer(getRenderer(SelectedCellRenderer.class));
		tableConfiguration.setSelectedRowRenderer(getRenderer(SelectedRowRenderer.class));
		tableConfiguration.setSelectedColumnRenderer(getRenderer(SelectedColumnRenderer.class));
		tableConfiguration.setErrorStatusCellEditorRenderer(getRenderer(CellEditorErrorModeRenderer.class));
	}

	private Renderer getRenderer(Class<? extends Annotation> annotationType) {
		AdapterMethod method = getSingleAdapterMethod(annotationType);
		if (method != null) {
			return (Renderer)method.invoke(descriptor, new Object[0]);
		} else {
			return null;
		}
	}

	private void setRowAndTableValidators(SimpleTableConfiguration<K, V> tableConfiguration) {
		tableConfiguration.addRowValidator(new ValidatorAdapter(RowValidator.class));
		tableConfiguration.addTableValidator(new ValidatorAdapter(TableValidator.class));
	}
	
	private class ValidatorAdapter extends ValueAcceptedValidator {
		private Class<? extends Annotation> annotationType;
		
		public ValidatorAdapter(Class<? extends Annotation> annotationType) {
			this.annotationType = annotationType;
		}

		@Override
		public List<ValidationError> validate() {
			List<ValidationError> errors = new ArrayList<ValidationError>();
			for (AdapterMethod method : getAdapterMethods(annotationType)) {
				@SuppressWarnings("unchecked")
				List<ValidationError> partOfErrors = (List<ValidationError>)method.invoke(descriptor, new Object[] {getValue()});
				if (partOfErrors != null) {
					errors.addAll(partOfErrors);
				}
			}
			
			return errors;
		}
		
	}

	private void setTableShortcuts(SimpleTableConfiguration<K, V> tableConfiguration) {
		List<AdapterMethod> shortcutMethods = getAdapterMethods(TableShortcut.class);
		Shortcut[] shortcuts = new Shortcut[shortcutMethods.size()];
		for (int i = 0; i < shortcutMethods.size(); i++) {
			final AdapterMethod method = shortcutMethods.get(i);
			TableShortcut shortcutAnnotation = (TableShortcut)method.getAnnotation();
			Shortcut shortcut = new Shortcut(KeyStroke.getKeyStroke(shortcutAnnotation.value()),
				new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						method.invoke(descriptor, getParameters(new Object[] {e},
								method.getAdditionalParamTypes()));
					}
			});
			
			shortcuts[i] = shortcut;
		}
		
		tableConfiguration.setShortcuts(shortcuts);
	}

	private void setRowHeight(SimpleTableConfiguration<K, V> tableConfiguration) {
		AdapterMethod method = getSingleAdapterMethod(RowHeight.class);
		if (method != null) {
			tableConfiguration.setRowHeight((Integer)method.invoke(descriptor, new Object[0]));
		}
	}
	
	private void configureColumnConfigurations() {
		int columnCount = getColumnCount();
		if (columnCount < 0) {
			throw new RuntimeException("Negative column count.");
		}
		
		// first: set column property names first. so we can find column configuration
		// by property name later.
		((SimpleTableConfiguration<K, V>)qTable.getConfiguration()).setColumnConfigurations(
				setPropertyNames(columnCount));
		
		// second: set other column properties from call back methods.
		setColumnPropertiesFromCallbackMethods(columnCount);
		
		// third: set input to value converter and value to label converter.
		setConverters();
		
		// fourth: set cell validators.
		setCellValidators();
		
		// fifth: set cell editor factories.
		setCellEditorFactories();
		
		// sixth: set cell renderer factories.
		setCellRendererFactories();
		
		// seventh: set cell and cell editor shortcuts
		setCellShortcuts(false);
		setCellShortcuts(true);
		
		// final: set value change listeners.
		setValueChangeListeners();
	}
	
	private void setCellShortcuts(boolean editing) {
		List<AdapterMethod> cellShortcutMethods;
		if (editing) {
			cellShortcutMethods = getAdapterMethods(CellEditorShortcut.class);
		} else {
			cellShortcutMethods = getAdapterMethods(CellShortcut.class);
		}
		
		Map<String, List<Shortcut>> allShortcuts = new HashMap<String, List<Shortcut>>();
		for (int i = 0; i < cellShortcutMethods.size(); i++) {
			final AdapterMethod method = cellShortcutMethods.get(i);
			String hotkey;
			String propertyName;
			
			if (editing) {
				CellEditorShortcut shortcutAnnotation = (CellEditorShortcut)method.getAnnotation();
				hotkey = shortcutAnnotation.hotkey();
				propertyName = shortcutAnnotation.propertyName();
			} else {
				CellShortcut shortcutAnnotation = (CellShortcut)method.getAnnotation();
				hotkey = shortcutAnnotation.hotkey();
				propertyName = shortcutAnnotation.propertyName();
			}
			
			Shortcut shortcut = new Shortcut(KeyStroke.getKeyStroke(hotkey),
				new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						method.invoke(descriptor, getParameters(new Object[] {e},
								method.getAdditionalParamTypes()));
					}
			});
			
			List<Shortcut> shortcuts = allShortcuts.get(propertyName);
			if (shortcuts == null) {
				shortcuts = new ArrayList<Shortcut>();
				allShortcuts.put(propertyName, shortcuts);
			}
			shortcuts.add(shortcut);
		}
		
		for (Entry<String, List<Shortcut>> shortcutsEntry : allShortcuts.entrySet()) {
			String propertyName = shortcutsEntry.getKey();
			List<Shortcut> shortcutsList = shortcutsEntry.getValue();
			Shortcut[] shortcuts = shortcutsList.toArray(new Shortcut[shortcutsList.size()]);
			getColumnConfigurationByPropertyName(propertyName).setShortcuts(shortcuts, editing);			
		}
	}

	private void setCellEditorFactories() {
		List<String> hasSet = new ArrayList<String>(); 
		for (final AdapterMethod method : getAdapterMethods(CellRendererFactoryProvider.class)) {
			CellRendererFactoryProvider cellRendererFactoryProvider = (CellRendererFactoryProvider)method.getAnnotation();
			String propertyName = cellRendererFactoryProvider.value();
			
			if (hasSet.contains(propertyName)) {
				throw new RuntimeException(String.format("Reduplicated cell renderer factory provider. Property %s.", propertyName));
			}
			hasSet.add(propertyName);
			
			@SuppressWarnings("unchecked")
			CellRendererFactory<K, V> cellRendererFactory = (CellRendererFactory<K, V>)method.invoke(descriptor, new Object[0]);
			getColumnConfigurationByPropertyName(propertyName).setCellRendererFactory(cellRendererFactory);
		}
	}
	
	private void setCellRendererFactories() {
		List<String> hasSet = new ArrayList<String>(); 
		for (final AdapterMethod method : getAdapterMethods(CellEditorFactoryProvider.class)) {
			CellEditorFactoryProvider cellEditorFactoryProvider = (CellEditorFactoryProvider)method.getAnnotation();
			String propertyName = cellEditorFactoryProvider.value();
			
			if (hasSet.contains(propertyName)) {
				throw new RuntimeException(String.format("Reduplicated cell editor factory provider. Property %s.", propertyName));
			}
			hasSet.add(propertyName);
			
			@SuppressWarnings("unchecked")
			CellEditorFactory<K, V> cellEditorFactory = (CellEditorFactory<K, V>)method.invoke(descriptor, new Object[0]);
			getColumnConfigurationByPropertyName(propertyName).setCellEditorFactory(cellEditorFactory);
		}
	}

	private void setValueChangeListeners() {
		List<AdapterMethod> valueChangingMethods = getAdapterMethods(ValueChanging.class);
		for (final AdapterMethod method : valueChangingMethods) {
			ValueChanging valueChanging = (ValueChanging)method.getAnnotation();
			String propertyName = valueChanging.value();
			getColumnConfigurationByPropertyName(propertyName).addValueChangeListener(new ValueChangeAdapter() {
				@Override
				public boolean valueChanging(Object source, Object oldValue,
						Object newValue) {
					return (Boolean)method.invoke(descriptor, getParameters(new Object[] {source, oldValue, newValue},
							method.getAdditionalParamTypes()));
				}
			});
		}
		
		List<AdapterMethod> valueChangedMethods = getAdapterMethods(ValueChanged.class);
		for (final AdapterMethod method : valueChangedMethods) {
			ValueChanged valueChanged = (ValueChanged)method.getAnnotation();
			String propertyName = valueChanged.value();
			getColumnConfigurationByPropertyName(propertyName).addValueChangeListener(new ValueChangeAdapter() {
				@Override
				public void valueChanged(Object source, Object oldValue, Object newValue) {
					method.invoke(descriptor, getParameters(new Object[] {source, oldValue, newValue},
							method.getAdditionalParamTypes()));
				}
			});
		}
	}

	private void setCellValidators() {
		List<AdapterMethod> validatorMethods = getAdapterMethods(CellValidator.class);
		for (final AdapterMethod method : validatorMethods) {
			CellValidator cellValidator = (CellValidator)method.getAnnotation();
			String propertyName = cellValidator.value();
			getColumnConfigurationByPropertyName(propertyName).addValidator(new ValueAcceptedValidator() {
				@SuppressWarnings("unchecked")
				@Override
				public List<ValidationError> validate() {
					return (List<ValidationError>)method.invoke(descriptor,
							getParameters(new Object[] {getValue()}, method.getAdditionalParamTypes()));
				}
				
			});
		}
	}

	private void setConverters() {
		List<String> hasSet = new ArrayList<String>(); 
		for (final AdapterMethod method : getAdapterMethods(InputToValueConverter.class)) {
			InputToValueConverter inputToValueConverter = (InputToValueConverter)method.getAnnotation();
			String propertyName = inputToValueConverter.value();
			
			if (hasSet.contains(propertyName)) {
				throw new RuntimeException(String.format("Reduplicated input to value converter. Property %s.", propertyName));
			}
			hasSet.add(propertyName);
			
			IConverter converter = (IConverter)method.invoke(descriptor, new Object[0]);
			getColumnConfigurationByPropertyName(propertyName).setInputToValueConverter(converter);
		}
		
		hasSet.clear();
		for (final AdapterMethod method : getAdapterMethods(ValueToLabelConverter.class)) {
			ValueToLabelConverter valueToLabelConverter = (ValueToLabelConverter)method.getAnnotation();
			String propertyName = valueToLabelConverter.value();
			if (hasSet.contains(propertyName)) {
				throw new RuntimeException(String.format("Reduplicated value to label converter. Property %s.", propertyName));
			}
			hasSet.add(propertyName);
			
			IConverter converter = (IConverter)method.invoke(descriptor, new Object[0]);
			getColumnConfigurationByPropertyName(propertyName).setValueToLabelConverter(converter);
		}
	}

	private void setColumnPropertiesFromCallbackMethods(int columnCount) {
		List<ColumnConfiguration<K, V>> columnConfigurations = ((SimpleTableConfiguration<K, V>)qTable.getConfiguration(
				)).getInternalColumnConfigurations();
		AdapterMethod widthMethod = getSingleAdapterMethod(ColumnWidth.class);
		AdapterMethod minWidthMethod = getSingleAdapterMethod(ColumnMinWidth.class);
		AdapterMethod maxWidthMethod = getSingleAdapterMethod(ColumnMaxWidth.class);
		AdapterMethod readOnlyMethod = getSingleAdapterMethod(ColumnReadOnly.class);
		AdapterMethod focusableMethod = getSingleAdapterMethod(ColumnFocusable.class);
		AdapterMethod visibleMethod = getSingleAdapterMethod(ColumnVisible.class);
		AdapterMethod titleMethod = getSingleAdapterMethod(ColumnTitle.class);
		AdapterMethod resizableMethod = getSingleAdapterMethod(ColumnResizable.class);
		
		
		for (int i = 0; i < columnCount; i++) {
			SimpleColumnConfiguration<K, V> columnConfiguration = (SimpleColumnConfiguration<K, V>)columnConfigurations.get(i);
			
			String propertyName = qTable.getPropertyName(i);
			if (widthMethod != null) {
				columnConfiguration.setWidth((Integer)callColumnConfigurationAdapterMethod(widthMethod, i, propertyName));
			}
			
			if (minWidthMethod != null) {
				columnConfiguration.setMinWidth((Integer)callColumnConfigurationAdapterMethod(minWidthMethod, i, propertyName));
			}
			
			if (maxWidthMethod != null) {
				columnConfiguration.setMaxWidth((Integer)callColumnConfigurationAdapterMethod(maxWidthMethod, i, propertyName));
			}
			
			if (readOnlyMethod != null) {
				columnConfiguration.setReadOnly((Boolean)callColumnConfigurationAdapterMethod(readOnlyMethod, i, propertyName));
			}
			
			if (focusableMethod != null) {
				columnConfiguration.setFocusable((Boolean)callColumnConfigurationAdapterMethod(focusableMethod, i, propertyName));
			}
			
			if (visibleMethod != null) {
				columnConfiguration.setVisible((Boolean)callColumnConfigurationAdapterMethod(visibleMethod, i, propertyName));
			}
			
			if (titleMethod != null) {
				columnConfiguration.setTitle((String)callColumnConfigurationAdapterMethod(titleMethod, i, propertyName));
			}
			
			if (resizableMethod != null) {
				columnConfiguration.setResizable((Boolean)callColumnConfigurationAdapterMethod(resizableMethod, i, propertyName));
			}
		}
	}

	private List<ColumnConfiguration<K, V>> setPropertyNames(int columnCount) {
		AdapterMethod propertyNameMethod = getSingleAdapterMethod(ColumnPropertyName.class);
		if (propertyNameMethod == null) {
			throw new RuntimeException("No property name method found. Please provide one.");
		}
		
		List<ColumnConfiguration<K, V>> columnConfigurations = new ArrayList<ColumnConfiguration<K, V>>();
		
		for (int i = 0; i < columnCount; i++) {
			String propertyName = (String)propertyNameMethod.invoke(descriptor, new Object[] {i});
			propertyNameToColumns.put(propertyName, i);
			SimpleColumnConfiguration<K, V> columnConfiguration = new SimpleColumnConfiguration<K, V>();
			columnConfiguration.setPropertyName(propertyName);
			
			columnConfigurations.add(columnConfiguration);
		}
		
		return columnConfigurations;
	}
	
	private SimpleColumnConfiguration<K, V> getColumnConfigurationByPropertyName(String propertyName) {
		List<ColumnConfiguration<K, V>> columnConfigurations = ((SimpleTableConfiguration<K, V>)qTable.getConfiguration()).getInternalColumnConfigurations();
		return (SimpleColumnConfiguration<K, V>)columnConfigurations.get(propertyNameToColumns.get(propertyName));
	}
	
	@SuppressWarnings("unchecked")
	private <T> T callColumnConfigurationAdapterMethod(AdapterMethod method, int i, String propertyName) {
		if (method.getMethod().getParameterTypes()[0] == int.class) {
			return (T)method.invoke(descriptor, new Object[] {i});
		} else {
			return (T)method.invoke(descriptor, new Object[] {propertyName});
		}
	}

	private int getColumnCount() {
		AdapterMethod method = getSingleAdapterMethod(ColumnCount.class);
		if (method == null) {
			throw new RuntimeException("No column count method found. Please provide one.");
		}
		
		int columnCount = (Integer)method.invoke(descriptor, new Object[0]);
		return columnCount;
	}
	
	private void configureData() {
		AdapterMethod method = getSingleAdapterMethod(DataProvider.class);
		if (method != null) {
			@SuppressWarnings("unchecked")
			Collection<V> data = (Collection<V>)method.invoke(descriptor, new Object[0]);
			if (data != null) {
				qTable.setData(data);
			}
		}
	}

	private void scanAdapterMethods() {
		Class<?> descriptClass = descriptor.getClass();
		Method[] methods = descriptClass.getMethods();
		
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			
			for (Annotation annotation : annotations) {
				for (Annotation annotationTypeAnnotation : annotation.annotationType().getAnnotations()) {
					if (annotationTypeAnnotation.annotationType().equals(Signatures.class)) {
						if (!processSignaturesAnnotationedMethod(annotation, method, (Signatures)annotationTypeAnnotation)) {
							throw new IncorrectMethodSignatureException(String.format("method: %s, annotation: %s, supported signatures: %s.",
								method.toString(), annotation.toString(), annotationTypeAnnotation.toString()));
						}
						
						break;
					} else if (annotationTypeAnnotation.annotationType().equals(Signature.class)) {
						if (!processSignatureAnnotationedMethod(annotation, method, (Signature)annotationTypeAnnotation)) {
							throw new IncorrectMethodSignatureException(String.format("method: %s, annotation: %s, supported signatures: %s.",
									method.toString(), annotation.toString(), annotationTypeAnnotation.toString()));
						}
						break;
					} else {
						continue;
					}
				}
			}
		}
	}

	private boolean processSignaturesAnnotationedMethod(Annotation annotation, Method method, Signatures signatures) {
		for (Signature signature : signatures.value()) {
			if (processSignatureAnnotationedMethod(annotation, method, signature)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean processSignatureAnnotationedMethod(Annotation annotation, Method method, Signature signature) {
		if (!signature.returnType().isAssignableFrom(method.getReturnType())) {
			return false;
		}
		
		Class<?>[] methodParamTypes = method.getParameterTypes();
		if (signature.requiredParamTypes().length > methodParamTypes.length) {
			return false;
		}
		
		for (int i = 0; i < signature.requiredParamTypes().length; i++) {
			Class<?> requiredParamType = signature.requiredParamTypes()[i];
			if (!requiredParamType.isAssignableFrom(methodParamTypes[i])) {
				return false;
			}
		}
		
		Class<?>[] additionalParamTypes = new Class<?>[methodParamTypes.length - signature.requiredParamTypes().length];
		nextParamType:
		for (int i = signature.requiredParamTypes().length; i < methodParamTypes.length; i++) {
			for (Class<?> paramType : signature.optionalParamTypes()) {
				if (paramType.isAssignableFrom(methodParamTypes[i])) {
					additionalParamTypes[methodParamTypes.length - i - 1] = methodParamTypes[i];
					continue nextParamType;
				}
			}
			
			return false;
		}
		
		List<AdapterMethod> adapterMethodList = adapterMethods.get(annotation.annotationType());
		if (adapterMethodList == null) {
			adapterMethodList = new ArrayList<AdapterMethod>();
		}
		adapterMethodList.add(new AdapterMethod(annotation, method, additionalParamTypes));
		adapterMethods.put(annotation.annotationType(), adapterMethodList);
		
		return true;
	}

	@Override
	public QuickTable<K, V> getTable() {
		return qTable;
	}
	
	private class AdapterMethod {
		private Method method;
		private Annotation annotation;
		private Class<?>[] additionalParamTypes;
		
		public AdapterMethod(Annotation annotation, Method method, Class<?>[] additionalParamTypes) {
			this.annotation = annotation;
			this.method = method;
			this.additionalParamTypes = additionalParamTypes;
		}
		
		public Object invoke(Object obj, Object[] args) {
			try {
				return method.invoke(obj, args);
			} catch (Exception e) {
				throw new RuntimeException(String.format("Can't call method %s[%s, %s].", method,
						obj, getArgsString(args)), e);
			}
		}

		private Object getArgsString(Object[] args) {
			StringBuilder sb = new StringBuilder();
			for (Object arg : args) {
				sb.append(arg).append(", ");
			}
			
			if (", ".equals(sb.substring(sb.length() - 2))) {
				sb.delete(sb.length() - 2, sb.length());
			}
			
			return sb.toString();
		}
		
		public Class<?>[] getAdditionalParamTypes() {
			return additionalParamTypes;
		}
		
		public Annotation getAnnotation() {
			return annotation;
		}
		
		public Method getMethod() {
			return method;
		}
	}
}
