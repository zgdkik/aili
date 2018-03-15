package org.hbhk.aili.client.core.widget.qtable.impl.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.CellRendererPane;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.validation.IValidationListener2;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationErrorsEvent;
import org.hbhk.aili.client.core.commons.validators.CompositeValidator;
import org.hbhk.aili.client.core.widget.qtable.ColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.FocusMovingStategy;
import org.hbhk.aili.client.core.widget.qtable.OutOfRowsRangeListener;
import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.Shortcut;
import org.hbhk.aili.client.core.widget.qtable.SimpleColumnConfiguration;
import org.hbhk.aili.client.core.widget.qtable.SimpleTableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.SwingUtils;
import org.hbhk.aili.client.core.widget.qtable.TableConfiguration;
import org.hbhk.aili.client.core.widget.qtable.converter.StringToBooleanConverter;
import org.hbhk.aili.client.core.widget.qtable.converter.StringToDoubleConverter;
import org.hbhk.aili.client.core.widget.qtable.converter.StringToFloatConverter;
import org.hbhk.aili.client.core.widget.qtable.converter.StringToIntegerConverter;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorFactory;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditorListener;
import org.hbhk.aili.client.core.widget.qtable.element.CellFocusListener;
import org.hbhk.aili.client.core.widget.qtable.element.CellImpl;
import org.hbhk.aili.client.core.widget.qtable.element.CellPosition;
import org.hbhk.aili.client.core.widget.qtable.element.RowImpl;
import org.hbhk.aili.client.core.widget.qtable.element.TableImpl;
import org.hbhk.aili.client.core.widget.qtable.model.BeanFactory;
import org.hbhk.aili.client.core.widget.qtable.model.Data;
import org.hbhk.aili.client.core.widget.qtable.model.ValueChangeListener;
import org.hbhk.aili.client.core.widget.qtable.renderer.CellRendererFactory;
import org.hbhk.aili.client.core.widget.qtable.renderer.DefaultCellEditorRenderer;
import org.hbhk.aili.client.core.widget.qtable.renderer.DefaultCellRenderer;
import org.hbhk.aili.client.core.widget.qtable.renderer.DefaultSelectedCellRenderer;
import org.hbhk.aili.client.core.widget.qtable.renderer.DefaultSelectedRowRenderer;
import org.hbhk.aili.client.core.widget.qtable.renderer.Renderer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class QuickTableSwingImpl<V> implements QuickTable<JTable, V> {
	private TableConfiguration<JTable, V> tableConfiguration;
	private JTable table;
	private Data<V> data;
	private int oldRow;
	private int oldColumn;
	private int row;
	private int column;
	private CellRendererPane cellRendererPane;
	private FocusMovingStategy<JTable> focusMovingStategy;
	private List<CellFocusListener<JTable, V>> cellFocusListeners;
	private List<CellEditorListener> cellEditorListeners;
	private CellEditor cellEditor;
	private KeyListener defaultTableKeyListener;
	private Map<Integer, String> columnToPropertyNames;
	private Map<String, Integer> propertyNameToColumns;
	private Map<Integer, IConverter> inputToValueConverters;
	private Map<Integer, IConverter> labelToValueConverters;
	private Map<Integer, Class<?>> columnToPropertyTypes;
	private BeanFactory<V> beanFactory;
	private List<IValidationListener2> validationListeners;
	private List<OutOfRowsRangeListener> outOfRowsRangeListeners;
	private int maxRows;
	
	private static final Renderer DEFAULT_CELL_RENDERER = new DefaultCellRenderer();
	private static final Renderer DEFAULT_SELECTED_ROW_RENDERER = new DefaultSelectedRowRenderer();
	private static final Renderer DEFAULT_SELECTED_CELL_RENDERER = new DefaultSelectedCellRenderer();
	private static final Renderer DEFAULT_CELL_EDITOR_RENDERER = new DefaultCellEditorRenderer();
	
	private static final IConverter NULL_CONVERTER = new NullConverter();
	private static final IConverter DEFAULT_BOOLEAN_CONVERTER = new StringToBooleanConverter();
	private static final IConverter DEFAULT_INTEGER_CONVERTER = new StringToIntegerConverter();
	private static final IConverter DEFAULT_FLOAT_CONVERTER = new StringToFloatConverter();
	private static final IConverter DEFAULT_DOUBLE_CONVERTER = new StringToDoubleConverter();
	public static final int NO_MAX_ROWS_LIMIT = -1;
	
	private enum MovingDirection {
		LEFT,
		RIGHT,
		UP,
		DOWN
	};

	public QuickTableSwingImpl() {
		this(null);
	}

	public QuickTableSwingImpl(TableConfiguration<JTable, V> tableConfiguration) {
		this(tableConfiguration, null);
	}
	
	public QuickTableSwingImpl(TableConfiguration<JTable, V> tableConfiguration, BeanFactory<V> beanFactory) {
		this(tableConfiguration, beanFactory, null);
	}

	public QuickTableSwingImpl(TableConfiguration<JTable, V> tableConfiguration, BeanFactory<V> beanFactory,
			Collection<V> cData) {
		this.tableConfiguration = tableConfiguration;
		this.beanFactory = beanFactory;
		this.data = createData(cData);
		cellRendererPane = new CellRendererPane();
		oldRow = -1;
		oldColumn = -1;
		row = -1;
		column = -1;
		maxRows = -1;
		cellFocusListeners = new ArrayList<CellFocusListener<JTable, V>>();
		cellEditorListeners = new ArrayList<CellEditorListener>();
		focusMovingStategy = new DefaultFocusMovingStategy<JTable>(this);
		cellEditor = new DefaultCellEditor();
		columnToPropertyNames = new HashMap<Integer, String>();
		propertyNameToColumns = new HashMap<String, Integer>();
		inputToValueConverters = new HashMap<Integer, IConverter>();
		labelToValueConverters = new HashMap<Integer, IConverter>();
		columnToPropertyTypes = new HashMap<Integer, Class<?>>();
		validationListeners = new ArrayList<IValidationListener2>();
		outOfRowsRangeListeners = new ArrayList<OutOfRowsRangeListener>();
	}

	private Data<V> createData(Collection<V> cData) {
		if (cData == null) {
			return getData();
		}
		
		Iterator<V> iter = cData.iterator();
		while (iter.hasNext()) {
			getData().add(iter.next());
		}

		return getData();
	}

	@Override
	public JTable getControl() {
		if (table == null) {
			if (beanFactory == null) {
				throw new IllegalStateException("Null bean factory.");
			}
			
			if (tableConfiguration.isRowNumberColumnVisible()) {
				SimpleColumnConfiguration<JTable, V> columnConfigration = new SimpleColumnConfiguration<JTable, V>();
				columnConfigration.setWidth(40);
				columnConfigration.setReadOnly(true);
				columnConfigration.setFocusable(false);
				columnConfigration.setResizable(false);
				
				((SimpleTableConfiguration<JTable, V>)tableConfiguration).getInternalColumnConfigurations(
						).add(0, columnConfigration);
			}
			
			TableModel tableModel = new TableModelImpl();
			TableColumnModel tableColumnModel = new TableColumnModelImpl();
			table = new JTable(tableModel, tableColumnModel);
			
			table = configureTable(table);
		}

		return table;
	}

	private JTable configureTable(JTable table) {		
		table.setAutoCreateColumnsFromModel(true);
		table.setRowHeight(tableConfiguration.getRowHeight());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		
		removeTableDefaultBehaviors(table);

		table.addMouseListener(new DefaultTableMouseListener());
		defaultTableKeyListener = new DefaultTableKeyListener();
		table.addKeyListener(defaultTableKeyListener);
		
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (isEditing()) {
					((JComponent)getCellEditor().getControl()).requestFocus();
				}
			}
		});

		return table;
	}
	
	private CellPosition getCellPositionAtPoint(Point point) {
		return new CellPosition(table.rowAtPoint(point), table.columnAtPoint(point));
	}
	
	private class DefaultTableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {			
			CellPosition newPosition = getCellPositionAtPoint(e.getPoint());
			if (!getColumnConfiguration(newPosition.getColumn()).isFocusable()) {
				e.consume();
				return;
			}
			
			if (e.getClickCount() == 1) {
				boolean editing = isEditing();
				if (isCellLoseAccepted(newPosition)) {
					if (!updateCellValue()) {
						processCellUpdatingFailed();
						return;
					} else {
						if (isEditing()) {
							getCellEditor().stop();
						}
					}
					
					focusOn(newPosition, true);
					if (editing) {
						editAt(newPosition, true);
					}
				}
			} else if (e.getClickCount() >= 2) {
				if (!isEditable(newPosition)){
					return;
				}
				
				if (!isCellLoseAccepted(newPosition)){
					return;
				}
				
				if (!updateCellValue()) {
					processCellUpdatingFailed();
					return;
				}
				
				focusOn(newPosition, true);
				editAt(newPosition, true);
			} 
			
			e.consume();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (!QuickTableSwingImpl.this.table.isFocusOwner()) {
				QuickTableSwingImpl.this.table.requestFocus();
			}
		}
	}
	
	private boolean isCellLoseAccepted(CellPosition newPosition) {
		if (!isValidCell(newPosition)){
			return false;
		}
		
		if (isEditing() && isValidCell(row, column)) {
			if (getCellEditor().isLocked() && !getCellEditor().isErrorMode()){
				return false;
			}
				
			if (!updateCellValue()) {
				processCellUpdatingFailed();
				return false;
			}
			
			if (row != newPosition.getRow()) {
				if (!validateRow(row)) {
					return false;
				}
			}
		}
		
		boolean accepted = true;
		for (CellFocusListener<JTable, V> listener : cellFocusListeners) {
			if (!listener.focusLosing(new CellImpl<JTable, V>(QuickTableSwingImpl.this, row, column), newPosition)) {
				accepted = false;
			}
		}
		
		return accepted;
	}
	
	private boolean validateRow(int row) {
		for (int i = 0; i < getColumnCount(true); i++) {
			if (i == 0 && tableConfiguration.isRowNumberColumnVisible()){
				continue;
			}
			
			List<IValidator> validators = getColumnConfiguration(i).getValidators();
			
			if (!validate(validators, getPropertyValue(getData().get(row), getPropertyNameFromColumn(i)),
					new CellImpl<JTable, V>(this, row, i))) {
				editAt(row, i, true);
				lockAndSetToError();
				
				return false;
			}
		}
		
		return validate(tableConfiguration.getRowValidators(), getData().get(row),
				new RowImpl<JTable, V>(this, row, column));
	}
	
	private boolean isEditable(CellPosition cellPosition) {
		int cellRow = cellPosition.getRow();
		int cellColumn = cellPosition.getColumn();
		
		if (cellRow != -1 && cellColumn != -1) {
			ColumnConfiguration<JTable, V> columnConfiguration = QuickTableSwingImpl.this.
					getColumnConfiguration(cellColumn);
			if (!columnConfiguration.isFocusable() || columnConfiguration.isReadOnly()){
				return false;
			}
				
		}
		
		return true;
	}
	
	private class DefaultTableKeyListener extends KeyAdapter {
		private JTextField dummyText;
		private DefaultEditorKit.DefaultKeyTypedAction keyTypedAction;
		
		public DefaultTableKeyListener() {
			dummyText = new JTextField();
			keyTypedAction = new DefaultEditorKit.DefaultKeyTypedAction();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (isEditing()) {
				if (processCellEditorShortcut(e) || processTableShortcut(e)) {
					e.consume();
					return;
				}
			} else {				
				if (processCellShortcut(e) || processTableShortcut(e)) {
					e.consume();
					return;
				}
			}
			
			processKeyEvent(e);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {			
			processKeyEvent(e);
		}
		
		private void processKeyEvent(KeyEvent e) {
			if (!isCellFocused()) {
				return;
			}
			
			if (isEditing()) {
				processCellEditorKeyEvent(e);
			} else {
				processCellKeyEvent(e);
			}
		}

		private void processCellKeyEvent(KeyEvent e) {
			if (isNavigateKey(e)) {				
				moveFocus(e);
			} else {
				processCellEditorInput(e);
			}
		}

		private boolean processTableShortcut(KeyEvent e) {
			if (!isValidCell(getCurrentPosition(true))){
				return false;
			}
				
			return processShortcut(e, tableConfiguration.getShortcuts(),
					QuickTableSwingImpl.this);
		}

		private boolean processShortcut(KeyEvent e, Shortcut[] shortcuts, Object source) {
			if (shortcuts == null || shortcuts.length == 0){
				return false;
			}
			
			for (Shortcut shortcut : shortcuts) {
				KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
				if (shortcut.getKeyStroke().getKeyCode() == keyStroke.getKeyCode() &&
						shortcut.getKeyStroke().getModifiers() == keyStroke.getModifiers()) {
					if (shortcut.getActionListener() != null) {
						shortcut.getActionListener().actionPerformed(new ActionEvent(
								source, ActionEvent.ACTION_PERFORMED, null));
						
						return true;
					}
				}
			}

			return false;
		}

		private void processCellEditorKeyEvent(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (getCellEditor().isLocked()) {
					getCellEditor().unlock();
				}
					
				getCellEditor().cancel();
				e.consume();
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (updateCellValue()) {
					getCellEditor().stop();
				} else {
					processCellUpdatingFailed();
				}
				e.consume();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				JTextField textField = getTextFieldEditor();
				if (textField != null) {
					if ((textField.getCaret().getDot() == textField.getCaret().getMark()) &&
								(textField.getCaret().getDot() == 0)) {
						updateAndMoveFocus(MovingDirection.LEFT);
						e.consume();
					}
				} else {
					updateAndMoveFocus(MovingDirection.LEFT);
					e.consume();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				JTextField textField = getTextFieldEditor();
				if (textField != null) {
					if ((textField.getCaret().getDot() == textField.getCaret().getMark()) &&
								(textField.getCaret().getDot() == textField.getText().length())) {
						updateAndMoveFocus(MovingDirection.RIGHT);
						e.consume();
					}
				} else {
					updateAndMoveFocus(MovingDirection.RIGHT);
					e.consume();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				updateAndMoveFocus(MovingDirection.UP);
				e.consume();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				updateAndMoveFocus(MovingDirection.DOWN);			
				e.consume();
			} else {
				processCharInput(e);
			}
		}

		private void processCharInput(KeyEvent e) {
			JComponent component = (JComponent)cellEditor.getControl();
			if (component instanceof JTextField) {
				JTextField realText = (JTextField)component;
				dummyText.setText(realText.getText());
				dummyText.getCaret().setDot(realText.getCaretPosition());
				dummyText.getCaret().moveDot(realText.getCaret().getMark());
				
				
				keyTypedAction.actionPerformed(new ActionEvent(dummyText,
						ActionEvent.ACTION_PERFORMED, String.valueOf(e.getKeyChar())));
				
				ColumnConfiguration<JTable, V> columnConfiguration = getColumnConfiguration(column);
				if (columnConfiguration.getValueChangeListeners() != null) {
					boolean cancelChange = false;
					for (ValueChangeListener listener : columnConfiguration.getValueChangeListeners()) {
						if (!listener.valueChanging(new CellImpl<JTable, V>(QuickTableSwingImpl.this, row, column), realText.getText(), dummyText.getText())) {
							cancelChange = true;
						}
					}
					
					if (cancelChange) {
						e.consume();
					}
				}
			}
		}

		private boolean processCellEditorShortcut(KeyEvent e) {
			if (!isValidCell(getCurrentPosition(true))){
				return false;
			}
				
			
			return processShortcut(e, getColumnConfiguration(column).getCellShortcuts(true),
					new CellImpl<JTable, V>(QuickTableSwingImpl.this, row, column));
		}

		private void processCellEditorInput(KeyEvent e) {
			if (e.getID() != KeyEvent.KEY_TYPED || e.getKeyChar() == '\b' ||
					e.getKeyChar() == '\t' || e.getKeyChar() == '\u0018' ||
					e.getKeyChar() == '\u001b' || e.getKeyChar() == '\u007f' ||
					e.getKeyChar() == 0xFFFF || e.getModifiers() != 0) {
				e.consume();
				return;
			}
			
			if (getColumnConfiguration(column).isReadOnly()) {
				return;
			}
			
			editAt(new CellPosition(row, column), true);
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				e.consume();
				return;
			}
			
			if (!isEditing()){
				return;
			}
				
			
			JComponent component = (JComponent)getCellEditor().getControl();
			if (component instanceof JTextField) {
				((JTextField)component).selectAll();
				e.setSource(component);
				processCharInput(e);
			} else {
				e.consume();
			}
		}

		private boolean processCellShortcut(KeyEvent e) {
			if (!isValidCell(getCurrentPosition(true))){
				return false;
			}
				
			
			return processShortcut(e, getColumnConfiguration(column).getCellShortcuts(false),
					new CellImpl<JTable, V>(QuickTableSwingImpl.this, row, column));
		}

		private void moveFocus(KeyEvent e) {
			if (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_LEFT) {
				QuickTableSwingImpl.this.moveFocus(MovingDirection.LEFT);
			} else if (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
				QuickTableSwingImpl.this.moveFocus(MovingDirection.RIGHT);
			} else if (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_UP) {
				QuickTableSwingImpl.this.moveFocus(MovingDirection.UP);
			} else if (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_DOWN) {
				QuickTableSwingImpl.this.moveFocus(MovingDirection.DOWN);
			} 
			
			e.consume();
		}

		private boolean isNavigateKey(KeyEvent e) {
			return (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_LEFT) ||
				(e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_RIGHT) ||
					(e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_UP) ||
						(e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_DOWN);
		}

		private boolean isValidCell(CellPosition current) {
			return current.getRow() >= 0 && current.getColumn() >= 0 && current.getRow() < getControl().getRowCount() &&
					current.getColumn() < getControl().getColumnCount();
		}
	}
	
	@Override
	public void moveToNextCell() {
		updateAndMoveFocus(MovingDirection.RIGHT);
	}
	
	private void updateAndMoveFocus(MovingDirection direction) {
		boolean editing = isEditing();
		if (editing && getCellEditor().isLocked() && !getCellEditor().isErrorMode()) {
			return;
		}
		
		if (updateCellValue()) {				
			moveFocus(direction);
			if (editing && !getColumnConfiguration(column).isReadOnly()) {
				editAt(new CellPosition(row, column), true);
			}
		} else {
			processCellUpdatingFailed();
		}
	}
	
	private void moveFocus(MovingDirection direction) {
		CellPosition current = new CellPosition(row, column);
		if (!isValidCell(current)){
			return;
		}
			
		
		CellPosition newPosition = null;
		if (direction == MovingDirection.LEFT) {
			newPosition = focusMovingStategy.previousCell(current);
		} else if (direction == MovingDirection.RIGHT) {
			newPosition = focusMovingStategy.nextCell(current);
		} else if (direction == MovingDirection.UP) {
			newPosition = focusMovingStategy.previousRow(current);
		} else if (direction == MovingDirection.DOWN) {
			newPosition = focusMovingStategy.nextRow(current);
		} 
		
		if (isCellLoseAccepted(newPosition)) {
			if (isEditing()) {
				getCellEditor().stop();
			}
			focusOn(newPosition, true);
		}
	}
	
	private void removeTableDefaultBehaviors(JTable table) {
		SwingUtils.removeComponentDefaultBehaviors(table);
		
		table.setUI(new BasicTableUI() {
			@Override
			public void installUI(JComponent c) {
				table = (JTable)c;
		        rendererPane = new CellRendererPane();
		        table.add(rendererPane);
		        installDefaults();
			}
		});
	}
	
	private void lockAndSetToError() {
		getCellEditor().lock();
		getCellEditor().setErrorMode(true);
	}

	private void processCellUpdatingFailed() {
		lockAndSetToError();
		JComponent component = (JComponent)getCellEditor().getControl();
		component.requestFocus();
		if (JTextComponent.class.isAssignableFrom(component.getClass())) {
			((JTextComponent)component).selectAll();
		}
	}

	@Override
	public boolean updateCellValue() {
		if (!isEditing()) {
			return true;
		}
		
		Object value = getCellEditor().getValue();
		IConverter inputToValueConverter = inputToValueConverters.get(column);
		if (inputToValueConverter != null) {
			try {
				value = inputToValueConverter.to(value);
			} catch (ConversionException e) {
				for (IValidationListener2 listener : validationListeners) {
					List<ValidationError> errors = new ArrayList<ValidationError>();
					errors.add(new ValidationError(e.getMessage(), new CellImpl<JTable, V>(this, row, column)));
					
					listener.validationError(new ValidationErrorsEvent(errors));
				}
				
				return false;
			}
		}
		
		if (!validate(getColumnConfiguration(column).getValidators(), value,
				new CellImpl<JTable, V>(QuickTableSwingImpl.this, row, column))) {
			return false;
		}
		
		try {
			setPropertyValue(getData().get(row), getPropertyNameFromColumn(column), value);
		} catch (Exception e) {
			throw new RuntimeException("Can't update cell value.", e);
		}
		
		if (getCellEditor().isLocked()) {
			getCellEditor().unlock();
		}
		
		return true;
	}
	
	private boolean validate(List<IValidator> validators, Object value, Object source) {
		if (validators == null){
			return true;
		}
			
		
		CompositeValidator compositeValidator = new CompositeValidator();
		for (IValidator validator : validators) {
			compositeValidator.addValidator(validator);
		}
		
		compositeValidator.setValue(value);
		List<ValidationError> errors = compositeValidator.validate();
		if (errors.size() == 0){
			return true;
		}
			
		
		if (source != null) {
			for (ValidationError error : errors) {
				error.setKey(source);
			}
		}
		
		ValidationErrorsEvent e = new ValidationErrorsEvent(errors);
		for (IValidationListener2 listener : validationListeners) {
			listener.validationError(e);
		}
		
		return false;
	}
	
	@SuppressWarnings("serial")
	private class TableModelImpl extends DefaultTableModel {
		public TableModelImpl() {
			super(0, ((SimpleTableConfiguration<JTable, V>)tableConfiguration).getInternalColumnConfigurations() == null ? 0 :
				((SimpleTableConfiguration<JTable, V>)tableConfiguration).getInternalColumnConfigurations().size());
		}

		@Override
		public int getRowCount() {
			if (data == null){
				return 0;
			}
				

			return data.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			if (!isValidCell(row, column)) {
				throw new IllegalStateException(String.format("Out of data range[%d, %d].", row, column));
			}
			
			if (column == 0 && tableConfiguration.isRowNumberColumnVisible()) {
				return tableConfiguration.getRowNumberColumnStart() + row;
			}
			
			return getPropertyValue(getData().get(row), getPropertyNameFromColumn(column));
		}
	}
	
	private boolean isValidCell(CellPosition position) {
		if (position == null) {
			return false;
		}
		
		return isValidCell(position.getRow(), position.getColumn());
	}
	
	private boolean isValidCell(int row, int column) {
		if (row < 0 || column < 0 || row > (getRowCount() - 1) || column > (getColumnCount(true) - 1)) {
			return false;
		}
		
		return true;
	}
	
	private String getPropertyNameFromColumn(int column) {
		return columnToPropertyNames.get(column);
	}
	
	ColumnConfiguration<JTable, V> getColumnConfiguration(int columnIndex) {
		return ((SimpleTableConfiguration<JTable, V>)tableConfiguration).getInternalColumnConfigurations(
				).get(columnIndex);
	}

	@SuppressWarnings("serial")
	private class TableColumnModelImpl extends DefaultTableColumnModel {
		
		@Override
		public void addColumn(TableColumn aColumn) {
			int index = aColumn.getModelIndex();
			
			ColumnConfiguration<JTable, V> configuration = getColumnConfiguration(index);
			
			if (configuration.isVisible()) {
				aColumn.setWidth(configuration.getWidth());
				aColumn.setPreferredWidth(configuration.getWidth());
				aColumn.setMinWidth(configuration.getMinWidth());
				aColumn.setMaxWidth(configuration.getMaxWidth());
				aColumn.setResizable(configuration.isResizable());
			} else {
				aColumn.setWidth(0);
				aColumn.setPreferredWidth(0);
				aColumn.setMinWidth(0);
				aColumn.setMaxWidth(0);
				aColumn.setResizable(false);
			}
			
			aColumn.setHeaderValue(configuration.getTitle());
			
			int columnIndex = aColumn.getModelIndex();
			if (columnIndex == 0 && tableConfiguration.isRowNumberColumnVisible()) {
				aColumn.setCellRenderer(table.getTableHeader().getDefaultRenderer());
				super.addColumn(aColumn);
				return;
			}
			
			if (configuration.getPropertyName() == null) {
				throw new IllegalArgumentException(String.format("Null column property name. column index %d",
						columnIndex));
			}
			columnToPropertyNames.put(columnIndex, configuration.getPropertyName());
			propertyNameToColumns.put(configuration.getPropertyName(), columnIndex);
			columnToPropertyTypes.put(columnIndex, getPropertyClass(configuration.getPropertyName()));
			
			if (configuration.getInputToValueConverter() != null) {
				inputToValueConverters.put(columnIndex, configuration.getInputToValueConverter());
			} else {
				if (configuration.getCellEditorFactory() == null) {
					inputToValueConverters.put(columnIndex, getDefaultInputToValueConverter(columnIndex));
				}
			}
			
			if (configuration.getValueToLabelConverter() != null) {
				labelToValueConverters.put(columnIndex, configuration.getValueToLabelConverter());
			} else {
				labelToValueConverters.put(columnIndex, getDefaultLabelToValueConverter(columnIndex));
			}
			
			super.addColumn(aColumn);

			CellRendererFactory<JTable, V> cellRendererFactory = configuration.getCellRendererFactory();
			if (cellRendererFactory == null) {
				cellRendererFactory = new DefaultCellRendererFactory<V>();
			}
			cellRendererFactory.install(QuickTableSwingImpl.this, getData(),
					configuration, aColumn.getModelIndex());
			
			CellEditorFactory<JTable, V> cellEditorFactory = configuration.getCellEditorFactory();
			if (cellEditorFactory == null) {
				cellEditorFactory = new GenericCellEditorFactory<V>();
			}
			cellEditorFactory.create(QuickTableSwingImpl.this, getData(),
					tableConfiguration, aColumn.getModelIndex());
		}
		
		private Class<?> getPropertyClass(String propertyName) {
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, beanFactory.getType());
				return propertyDescriptor.getPropertyType();
			} catch (IntrospectionException e) {
				throw new RuntimeException(String.format("Can't get type of property[%s]", propertyName), e);
			}
		}

		private IConverter getDefaultLabelToValueConverter(int columnIndex) {
			return NULL_CONVERTER;
		}

		private IConverter getDefaultInputToValueConverter(int columnIndex) {
			Class<?> propertyType = columnToPropertyTypes.get(columnIndex);
			if (propertyType == Boolean.class || propertyType == boolean.class) {
				return DEFAULT_BOOLEAN_CONVERTER;
			} else if (propertyType == Integer.class || propertyType == int.class) {
				return DEFAULT_INTEGER_CONVERTER;
			} else if (propertyType == Float.class || propertyType == float.class) {
				return DEFAULT_FLOAT_CONVERTER;
			} else if (propertyType == Double.class || propertyType == double.class) {
				return DEFAULT_DOUBLE_CONVERTER;
			} else {
				return NULL_CONVERTER;
			}
		}
	}
	
	private static class NullConverter implements IConverter {

		@Override
		public Object to(Object source) throws ConversionException {
			return source;
		}

		@Override
		public Object from(Object target) throws ConversionException {
			return target;
		}
		
	}
	
	private class DefaultCellRendererFactory<T> implements CellRendererFactory<JTable, T> {

		@Override
		public void install(QuickTable<JTable, T> quickTable, Data<T> data,
				ColumnConfiguration<JTable, T> configuration, int columnIndex) {
			TableColumn tableColumn = table.getColumnModel().getColumn(columnIndex);
			tableColumn.setCellRenderer(new TableCellRendererImpl());
		}
		
	}
	
	@SuppressWarnings("serial")
	private class TableCellRendererImpl extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
			if (row == QuickTableSwingImpl.this.row && column == QuickTableSwingImpl.this.column) {
				setSelectedCellUI(table, column);
			} else if (row == QuickTableSwingImpl.this.row) {
				setSelectedRowCellUI();
			} else if (column == QuickTableSwingImpl.this.column) {
				setSelectedColumnCellUI();
			} else {
				setDefaultCellUI();
			}
			
			if (getColumnConfiguration(column).getValueToLabelConverter() != null) {
				try {
					value = getColumnConfiguration(column).getValueToLabelConverter().to(value);
				} catch (ConversionException e) {
					throw new RuntimeException("Can't convert value to label", e);
				}
			}
			
			setValue(value);
			
			return this;
		}

		private void setCellUI(Renderer renderer) {
			if (renderer == null){
				renderer = DEFAULT_CELL_RENDERER;
			}
				
			setForeground(renderer.getForeground());
			setBackground(renderer.getBackground());
			setFont(renderer.getFont());
			setBorder(renderer.getBorder());
		}
		
		private void setDefaultCellUI() {
			Renderer renderer = tableConfiguration.getDefaultRenderer();
			if (renderer == null) {
				renderer = DEFAULT_CELL_RENDERER;
			}
			
			setCellUI(renderer);
		}

		private void setSelectedColumnCellUI() {
			setCellUI(tableConfiguration.getSelectedColumnRenderer());
		}

		private void setSelectedRowCellUI() {
			Renderer renderer = tableConfiguration.getSelectedRowRenderer();
			if (renderer == null) {
				renderer = DEFAULT_SELECTED_ROW_RENDERER;
			}
			
			setCellUI(renderer);
		}

		private void setSelectedCellUI(JTable table, int column) {
			Renderer renderer = tableConfiguration.getSelectedCellRenderer();
			if (renderer == null) {
				renderer = DEFAULT_SELECTED_CELL_RENDERER;
			}
			
			setCellUI(renderer);
		}
	}
	
	private class DefaultCellEditor implements CellEditor {
		private boolean locked;
		private boolean errorMode;
		
		public DefaultCellEditor() {
			locked = false;
			errorMode = false;
		}
		
		@Override
		public void startEditing() {
			if (isEditing()){
				return;
			}
				
			
			if (!table.editCellAt(row, column)){
				return;
			}
				
			
			JComponent component = getCellEditorComponent();
			if (component != null && (component instanceof JTextField) ||
						(component instanceof JCheckBox)) {
				component.addKeyListener(defaultTableKeyListener);
				component.requestFocus();
			}
			
			for (CellEditorListener listener : cellEditorListeners) {
				listener.editingStarted(this);
			}
		}

		private JComponent getCellEditorComponent() {
			TableCellEditor ceditor = table.getCellEditor();
			if (ceditor != null && ceditor instanceof TableCellEditorAdapter) {
				return ((TableCellEditorAdapter<?>)ceditor).getComponent();
			}
			
			return null;
		}

		@Override
		public void stop() {
			if (!doCancel()){
				return;
			}
				
			
			for (CellEditorListener listener : cellEditorListeners) {
				listener.stopped(this);
			}
		}
		
		@Override
		public void cancel() {
			if (!doCancel()){
				return;
			}
				
			
			for (CellEditorListener listener : cellEditorListeners) {
				listener.canceled(this);
			}
		}

		private boolean doCancel() {
			if (!isEditing()){
				return false;
			}
				
			if (isLocked()){
				return false;
			}
				
			
			if (isErrorMode()) {
				setErrorMode(false);
			}
			
			JComponent component = getCellEditorComponent();
			if (component != null && (component instanceof JTextField) ||
					(component instanceof JCheckBox)) {
				component.removeKeyListener(defaultTableKeyListener);
			}
			
			TableCellEditor realCellEditor = table.getCellEditor();
			if (realCellEditor != null) {
				realCellEditor.cancelCellEditing();
			}
			
			return true;
		}
		
		@Override
		public void setValue(Object value) {
			if (!isEditing()){
				return;
			}
			
			TableCellEditor ceditor = table.getCellEditor();
			if (ceditor != null && (ceditor instanceof TableCellEditorAdapter)) {
				((TableCellEditorAdapter<?>)ceditor).setValueAt(value);
			}
		}

		@Override
		public Object getValue() {
			if (!isEditing())
				return null;
			
			return table.getCellEditor().getCellEditorValue();
		}

		@Override
		public void lock() {
			locked = true;
			
			for (CellEditorListener listener : cellEditorListeners) {
				listener.locked(this);
			}
		}
		
		@Override
		public void unlock() {
			locked = false;
			
			for (CellEditorListener listener : cellEditorListeners) {
				listener.unlocked(this);
			}
		}

		@Override
		public boolean isLocked() {
			return locked;
		}

		@Override
		public JComponent getControl() {
			return getCellEditorComponent();
		}

		@Override
		public void setErrorMode(boolean errorMode) {
			if (this.errorMode == errorMode) {
				return;
			}
			
			this.errorMode = errorMode;
			Renderer renderer = null;
			if (errorMode) {
				renderer = tableConfiguration.getCellEditorErrorModeRenderer();
				if (renderer == null) {
					renderer = new DefaultCellEditorRenderer() {
						@Override
						public Color getBackground() {
							return Color.red;
						}
					};
				}
			} else {
				renderer = tableConfiguration.getDefaultCellEditorRenderer();
				if (renderer == null) {
					renderer = DEFAULT_CELL_EDITOR_RENDERER;
				}
			}
			
			JComponent component = getControl();
			
			if (renderer.getFont() != null) {
				component.setFont(renderer.getFont());
			}
			
			if (renderer.getForeground() != null) {
				component.setForeground(renderer.getForeground());
			}
			
			if (renderer.getBackground() != null) {
				component.setBackground(renderer.getBackground());
			}
			
			if (renderer.getBorder() != null) {
				component.setBorder(renderer.getBorder());
			}
			
			component.repaint();
		}

		@Override
		public boolean isErrorMode() {
			return errorMode;
		}

		@Override
		public int getRow() {
			return row;
		}

		@Override
		public int getColumn() {
			return adjustColumn(column, false);
		}

		@Override
		public QuickTable<?, ?> getTable() {
			return QuickTableSwingImpl.this;
		}
		
	}

	@Override
	public void setConfiguration(TableConfiguration<JTable, V> configuration) {
		this.tableConfiguration = configuration;
	}
	
	@Override
	public TableConfiguration<JTable, V> getConfiguration() {
		return tableConfiguration;
	}

	@Override
	public int getRowCount() {
		return getControl().getRowCount();
	}

	@Override
	public int getColumnCount() {
		return getColumnCount(false);
	}
	
	public int getColumnCount(boolean internal) {
		if (internal) {
			return getControl().getColumnCount();
		} else {
			return adjustColumn(getControl().getColumnCount(), false);
		}
	}
	
	private int adjustColumn(int column) {
		return adjustColumn(column, true);
	}
	
	private int adjustColumn(int column, boolean in) {
		if (in) {
			if (tableConfiguration.isRowNumberColumnVisible()) {
				column++;
			}
		} else {
			if (tableConfiguration.isRowNumberColumnVisible()) {
				column--;
			}
		}
		
		return column;
	}
	
	private void focusOn(CellPosition position, boolean internal) {
		if (position == null){
			return;
		}
			
		focusOn(position.getRow(), position.getColumn(), true);
	}
	
	@Override
	public void focusOn(int row, int column) {
		focusOn(row, column, false);
	}

	private void focusOn(int row, int column, boolean internal) {
		if (!internal) {
			column = adjustColumn(column);
		}
		if (!isValidCell(row, column)){
			return;
		}
		
		if (this.row == row && this.column == column) {
			return;
		}
		
		if (!isFocusable(row, column)){
			return;
		}
			
		if (isEditing()) {
			getCellEditor().cancel();
		}
		
		oldRow = this.row;
		oldColumn = this.column;
		
		for (CellFocusListener<JTable, V> listener : cellFocusListeners) {
			listener.focusLost(new CellImpl<JTable, V>(this, oldRow, oldColumn));
		}
		
		this.row = row;
		this.column = column;
		
		if (oldRow != -1 && oldColumn != -1) {
			for (int i = 0; i < table.getRowCount(); i++) {
				paintCell(i, oldColumn);
			}
			
			for (int i = 0; i < table.getColumnCount(); i++) {
				paintCell(oldRow, i);
			}
		}
		
		if (row != -1 && column != -1) {
			for (int i = 0; i < table.getRowCount(); i++) {
				paintCell(i, column);
			}
			
			for (int i = 0; i < table.getColumnCount(); i++) {
				paintCell(row, i);
			}
		}
		
		if (table.getAutoscrolls()) {
			Rectangle cellRect = table.getCellRect(row, column, false);
			if (cellRect != null) {
				table.scrollRectToVisible(cellRect);
			}
		}
		
		for (CellFocusListener<JTable, V> listener : cellFocusListeners) {
			listener.focusGained(new CellImpl<JTable, V>(this, row, column));
		}
	}
	
	private void paintCell(int row, int column) {
		if (!isValidCell(row, column)) {
			return;
		}
		
		Graphics g = table.getGraphics();
		TableCellRenderer renderer = getControl().getCellRenderer(row, column);
		Component component = getControl().prepareRenderer(renderer, row, column);
		Rectangle rect = table.getCellRect(row, column, false);
		cellRendererPane.paintComponent(g, component, table, rect.x, rect.y, rect.width, rect.height, true);
	}

	@Override
	public CellEditor getCellEditor() {
		if (isEditing()){
			return cellEditor;
		}
			
		
		return null;
	}

	@Override
	public boolean isEditing() {
		if (table == null){
			return false;
		}
		
		return table.isEditing();
	}

	@Override
	public void addCellEditorListener(CellEditorListener listener) {
		cellEditorListeners.add(listener);

	}

	@Override
	public void removeCellEditorListener(CellEditorListener listener) {
		cellEditorListeners.remove(listener);
	}

	@Override
	public void addCellFocusListener(CellFocusListener<JTable, V> listener) {
		cellFocusListeners.add(listener);
	}

	@Override
	public void removeCellFocusListener(CellFocusListener<JTable, V> listener) {
		cellFocusListeners.remove(listener);
	}

	@Override
	public void addRow() {
		addRow(beanFactory.create());
	}
	
	@Override
	public void addRow(V bean) {
		insertRow(getRowCount(), bean);		
	}
	
	@Override
	public void insertRow(int index, V bean) {
		data.insert(index, bean);
	}

	@Override
	public void removeRow(int row) {
		if (row < 0){
			return;
		}
			
		
		if (row > (getRowCount() -1)) {
			throw new RuntimeException(String.format("Out of row range[%d].", row));
		}
		
		getData().remove(row);
	}
	
	@Override
	public CellPosition getCurrentPosition() {
		return getCurrentPosition(false);
	}
	
	public CellPosition getCurrentPosition(boolean internal) {
		if (internal) {
			return new CellPosition(row, column);
		} else {
			return new CellPosition(row, adjustColumn(column, false));
		}
	}

	@Override
	public void editAt(CellPosition position) {
		editAt(row, column);
	}
	
	public void editAt(CellPosition position, boolean internal) {
		editAt(row, column, internal);
	}
	
	@Override
	public void editAt(int row, int column) {
		editAt(row, column, false);
	}
	
	public void editAt(int row, int column,boolean internal) {
		if (!internal) {
			column = adjustColumn(column);
		}
		if (isEditing()) {
			if (getCellEditor().isLocked()) {
				return;
			}
			
			getCellEditor().cancel();
		}
		
		focusOn(row, column, true);
		cellEditor.startEditing();
		
		if (!isEditing()){
			return;
		}
		
		JComponent component = (JComponent)getCellEditor().getControl();
		if (component instanceof JTextComponent) {
			((JTextComponent)component).selectAll();
		}
		component.requestFocus();
	}

	@Override
	public void setData(Collection<V> cData) {
		data = createData(cData);
		
		if (isEditing()) {
			getCellEditor().stop();
		}

		if (beanFactory != null) {
			getControl().repaint();
		}
	}

	@Override
	public boolean isCellFocused() {
		return !getCurrentPosition(true).equals(CellPosition.NONE);
	}

	@Override
	public void setFocusMovingStategy(FocusMovingStategy<JTable> focusMovingStategy) {
		this.focusMovingStategy = focusMovingStategy;
	}
	
	private boolean isFocusable(int row, int column) {
		if (!isValidCell(row, column)) {
			return false;
		}
		
		ColumnConfiguration<JTable, V> columnConfiguration = getColumnConfiguration(column);
		return columnConfiguration.isVisible() && columnConfiguration.isFocusable();
	}
	
	private JTextField getTextFieldEditor() {
		Object component = getCellEditor().getControl();
		if (component instanceof JTextField) {
			return (JTextField)component;
		}
		
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory<V> beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public void addValidationListener(IValidationListener2 listener) {
		validationListeners.add(listener);
	}

	@Override
	public void removeValidationListener(IValidationListener2 listener) {
		validationListeners.remove(listener);
	}

	@Override
	public Data<V> getData() {
		if (data == null) {
			data = new DataImpl<V>();
		}
		
		return data;
	}
	
	private class DataImpl<T> implements Data<T> {
		private List<T> lData;
		
		public DataImpl() {
			lData = new ArrayList<T>();
		}

		@Override
		public void add(T object) {
			if (outOfRowsRange()){
				return;
			}
				
			
			lData.add(object);
			if (table != null) {
				((AbstractTableModel)table.getModel()).fireTableRowsInserted(lData.size() - 1,
						lData.size() - 1);
			}
		}

		private boolean outOfRowsRange() {
			if (maxRows != NO_MAX_ROWS_LIMIT && lData.size() > maxRows) {
				for (OutOfRowsRangeListener listener : outOfRowsRangeListeners) {
					listener.maxRowsExceed(maxRows);
				}
				
				return true;
			}
			
			return false;
		}

		@Override
		public void remove(int i) {
			if (i < 0 || i > lData.size() - 1) {
				return;
			}
			
			if (isEditing()) {
				getCellEditor().setErrorMode(false);
				getCellEditor().unlock();
				getCellEditor().cancel();
			}
			
			lData.remove(i);
			if (table != null) {
				((AbstractTableModel)table.getModel()).fireTableRowsDeleted(i, i);
			}
			
			if (!isValidCell(row, column)) {
				if (lData.size() == 0) {
					row = -1;
					column = -1; 
				} else {
					row = row - 1;
				}
			}
		}

		@Override
		public int size() {
			return lData.size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public T get(int i) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(beanFactory.getType());
			enhancer.setCallback(new DataChangeInterceptor(lData.get(i)));

			return (T)enhancer.create();
		}

		@Override
		public List<T> toList() {
			return Collections.unmodifiableList(lData);
		}

		@Override
		public void insert(int index, T object) {
			if (outOfRowsRange()){
				return;
			}
				
			lData.add(index, object);
			((AbstractTableModel)table.getModel()).fireTableRowsInserted(index, index);
		}
	}
	
	public Object getPropertyValue(Object object, String propertyName) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
			Method method = propertyDescriptor.getReadMethod();
			
			return method.invoke(object, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can't read value of property[%s].", propertyName), e);
		}
	}
	
	private void setPropertyValue(V object, String propertyName, Object value) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
			Method method = propertyDescriptor.getWriteMethod();
			
			method.invoke(object, new Object[] {value});
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can't write value to property[%s].", propertyName), e);
		}
	}
	
	private class DataChangeInterceptor implements MethodInterceptor {
		private Object real;
		
		public DataChangeInterceptor(Object real) {
			this.real = real;
		}

		@Override
		public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
			Object result = null;
			
			result = method.invoke(real, args);
			
			if (method.getName().startsWith("set") && method.getName().length() >= 4 &&
					Character.isUpperCase(method.getName().charAt(3))) {
				String propertyName = method.getName().substring(4, method.getName().length());
				propertyName = Character.toLowerCase(method.getName().charAt(3)) + propertyName;
				if (propertyNameToColumns != null) {
					paintCell(row, propertyNameToColumns.get(propertyName));
				}
			}
			
			return result;
		}
		
	}

	@Override
	public int getColumnIndex(String propertyName) {
		Integer result = propertyNameToColumns.get(propertyName);
		if (result == null){
			return -1;
		}
		
		return adjustColumn(result, false);
	}

	@Override
	public String getPropertyName(int columnIndex) {
		columnIndex = adjustColumn(columnIndex);
		return columnToPropertyNames.get(columnIndex);
	}

	@Override
	public CellPosition getLastPosition() {
		return new CellPosition(oldRow, adjustColumn(oldColumn, false));
	}

	@Override
	public boolean validate() {
		for (int i = 0; i < getRowCount(); i++) {
			if (!validateRow(i)) {
				return false;
			}
		}
		
		if (!validate(tableConfiguration.getTableValidators(), getData().toList(),
				new TableImpl<JTable, V>(this, row, column))) {
			return false;
		}
		
		return true;
	}

	@Override
	public BeanFactory<V> getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	@Override
	public int getMaxRows() {
		return maxRows;
	}

	@Override
	public void addOutOfRowsListener(OutOfRowsRangeListener listener) {
		outOfRowsRangeListeners.add(listener);
	}

	@Override
	public void removeOutOfRowsListener(OutOfRowsRangeListener listener) {
		outOfRowsRangeListeners.remove(listener);
	}
}
