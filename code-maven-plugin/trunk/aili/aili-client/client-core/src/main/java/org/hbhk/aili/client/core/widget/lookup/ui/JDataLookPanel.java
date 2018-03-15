package org.hbhk.aili.client.core.widget.lookup.ui;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.util.ReflectionUtils;
/**
* Description:数据搜索面板
 */
public class JDataLookPanel<T> extends JDialog {
	
	private static final long serialVersionUID = 943603454888487805L;

	private JTable objList;
	
	private JPanel searchPane;
	
	private JComboBox combo;
	
	private JTextField selectText;
	
	private JButton selectButton;
	
	Map<Integer,T> values;
	
	String[] columnNames;
	
	
	public JDataLookPanel(String[] columnNames){
		
		values = new HashMap<Integer, T>();
		
		this.columnNames = columnNames;
		
		//search
		setLayout(new BorderLayout());
		
		//查询条件
		selectText = new JTextField();
		selectText.setColumns(6);
		//查询按钮
		selectButton = new JButton("查询");
		ImageIcon imageIcon = ImageUtil.getImageIcon(this.getClass(), "form/tbtn_Find.gif");
		selectButton.setIcon(imageIcon);
		
		//查询
		searchPane = new  JPanel();
		add(searchPane,BorderLayout.NORTH);
		
		//表格
		//设置为不可以编辑表格
		objList = new JTable(){
			/**
			 * 版本
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		objList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(objList);
		add(scrollPane,BorderLayout.CENTER);
		
		//button
		JPanel buttons=new JPanel();
		add(buttons,BorderLayout.SOUTH);
		
		combo = new JComboBox();
		for(Object o:columnNames){
			combo.addItem(o);
		}
		searchPane.add(combo);
		//查询条件
		searchPane.add(selectText);
		//查询按钮
		searchPane.add(selectButton);
		//listener
	}
	
	/**
	 * 选择对象
	 * @return
	 */
	public T getValue(){
		int row = objList.getSelectedRow();
		if (row>=0) {
			return values.get(row);
		}
		return null;
	}	
	
	/**
	 * 表格赋值
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public void addTableValue(List<T> list) {
		//获取数据
		Object[][] data = new Object[list.size()][columnNames.length];
		values.clear();
		int i=0;
		for(T t:list){
			values.put(i, t);
			Class c = t.getClass();
			Field[] fs = c.getDeclaredFields();
			int j=0;
			for(Field f:fs){
				for(String columnName:columnNames){
					if(f.getName().equals(columnName)){
						data[i][j++] = ReflectionUtils.getFieldValue(t, f.getName());
					}
				}
			}
			i++;
		}
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(data, columnNames);
		objList.setModel(tableModel);
		if(objList.getRowCount()==1){
			objList.setRowSelectionInterval(0, 0);
		}
	}

	public JTable getObjList() {
		return objList;
	}

	public void setObjList(JTable objList) {
		this.objList = objList;
	}

	public JPanel getSearchPane() {
		return searchPane;
	}

	public void setSearchPane(JPanel searchPane) {
		this.searchPane = searchPane;
	}

	public JComboBox getCombo() {
		return combo;
	}

	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}

	public JTextField getSelectText() {
		return selectText;
	}

	public void setSelectText(JTextField selectText) {
		this.selectText = selectText;
	}

	public JButton getSelectButton() {
		return selectButton;
	}

	public void setSelectButton(JButton selectButton) {
		this.selectButton = selectButton;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	
	
	
}
