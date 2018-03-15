package org.hbhk.aili.client.core.widget.lookup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.util.ReflectionUtils;
import org.hbhk.aili.client.core.widget.lookup.action.JLookupEvent;
import org.hbhk.aili.client.core.widget.lookup.action.JLookupListener;
import org.hbhk.aili.client.core.widget.lookup.service.IJLookupData;
import org.hbhk.aili.client.core.widget.lookup.ui.JDataLookPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
/**
* Description:实现搜索功能
 */
@SuppressWarnings("rawtypes")
public class JLookup<T> extends JComponent {
	private static final long serialVersionUID = 7739545461048643029L;
	private JTextField          valueText;
	private JButton             button;
	private JDataLookPanel<T>   lookPanel;
	private String              bindField;
	private IJLookupData<T>     ijLookupData;
	// 事件源的新旧值
	
	private Vector repository = new Vector();
	T oldValue;
	T newValue;
	JLookupListener lookuplistener;
	
	/**
	 * <p>Title:</p>
	 * <p>Description:
	 * 根据搜索的列名称，构建一个搜索面板</p>
	 *
	 * @param columnNames
	 */
	
	public JLookup(String[] columnNames) {
		this.setLayout(new BorderLayout());
		valueText=new JTextField("");
		button=new JButton();
		ImageIcon imageIcon = ImageUtil.getImageIcon(this.getClass(), "form/tbtn_Find.gif");
		button.setIcon(imageIcon);
		
		FormLayout layout = new FormLayout("42dlu,10dlu","12dlu");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		CellConstraints cc = new CellConstraints();
		builder.add(valueText,cc.rc(1, 1));
		builder.add(button,cc.rc(1, 2));
		
		add(builder.getPanel(),BorderLayout.CENTER);
		
		lookPanel = new JDataLookPanel<T>(columnNames);
		lookPanel.setMinimumSize(new Dimension(500,360));
		lookPanel.setAlwaysOnTop(true);
		lookPanel.setLocationRelativeTo(null);   //出现在屏幕中央
		lookPanel.setModal(true);   //模态
		
		//监听查询事件
		createButtonListener();
		//监听值显示控件
		createValueListener();
		//表格事件
		createTableListener();
	}
	
	/**
	 * 
	 * @Title:setBindField
	 * @param @param field
	 * @return void
	 * @throws
	 */
	public void setBindField(String field) {
		bindField = field;
	}
	
	/**
	 * 
	 * @Title:getFieldValue
	 * @param @param obj
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getFieldValue(T obj) {
		if(bindField!=null &&!"".equals(bindField))
			return ReflectionUtils.getFieldValue(obj, bindField).toString();
		else
			return "";
	}
	
	/**
	 * 设置编辑状态
	 */
	@Override
	public void setEnabled(boolean enabled) {
		valueText.setEnabled(enabled);
		button.setEnabled(enabled);
	}
	public JTextField getValueText() {
		return valueText;
	}
	
	/**
	 * 获得列名
	 * @param t
	 * @return
	 */
	public Object[] getObjectName(T t) {
		Class c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		int j=0;
		Object[] objectName = new Object[fs.length];
		for(Field f:fs){
			objectName[j] = f.getName();
			j++;
		}
		return objectName;
	}
	
	/**
	 * 获得列值
	 * @param t
	 * @return
	 */
	public static Map<String, String> getFieldValueMap(Object bean) {
		Class<?> cls = bean.getClass();
		Map<String, String> valueMap = new HashMap<String, String>();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldType = field.getType().getSimpleName();
				String fieldGetName = parGetName(field.getName());
				if (!checkGetMet(methods, fieldGetName)) {
					continue;
				}
				Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
				Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
				String result = null;
				if ("Date".equals(fieldType)) {
					result = fmtDate((Date) fieldVal);
				} else {
					if (null != fieldVal) {
						result = String.valueOf(fieldVal);
					}
				}
				valueMap.put(field.getName(), result);
			} catch (Exception e) {
				continue;
			}
		}
		return valueMap;
	}
	
	/**
　　* 日期转化为String
　　* @param date
　　* @return date string
　　*/
	public static String fmtDate(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
　　* 判断是否存在某属性的 get方法
　　* @param methods
　　* @param fieldGetMet
　　* @return boolean
　　*/
	public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method met : methods) {
			if (fieldGetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	　　* 拼接某属性的 get方法
	　　* @param fieldName
	　　* @return String
	　　*/
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * 获取数据源
	 */
	public void setDataModel(IJLookupData<T> iJLookupData) {
		this.ijLookupData = iJLookupData;
	}
	
	/**
	 * 获取值
	 * @return
	 */
	public T getValue() {
		return newValue;
	}
	
	/**
	 * 设置值
	 * @param value
	 */
	public void setValue(T value) {
//		firePropertyChange("value", this.getValue(), value);
		newValue = value;
	}
	
	/**
	 * 数据改变事件
	 * @param listener
	 */
	@SuppressWarnings("unchecked")
	public void addDataChangedListener(JLookupListener lookuplistener) {
		repository.addElement(lookuplistener);
	}
	
	/**
	 * 数据改变出发事件源
	 */
	public void notifyDataChangedEvent() {
		Enumeration enum1 = repository.elements();
		while(enum1.hasMoreElements()) {
			lookuplistener = (JLookupListener)enum1.nextElement();
			lookuplistener.addDataChangedListener(new JLookupEvent(this));
		}
		setOldValue(getValue());
    }
	
	/**
	 * 监听查询事件
	 * 1.点击查询打开数据表格
	 */
	private void createButtonListener() {
		// 控件的查询按钮
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				lookPanel.getSelectText().setText(valueText.getText());
				lookPanel.getCombo().setSelectedItem(bindField);
				lookPanel.addTableValue(ijLookupData.getJLookupData(bindField, valueText.getText()));
				lookPanel.setVisible(true);
			}
		});
		
		// 选择框的查询按钮
		lookPanel.getSelectButton().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lookPanel.addTableValue(ijLookupData.getJLookupData((String)lookPanel.getCombo().getSelectedItem(), lookPanel.getSelectText().getText()));
			}
		});
		
	}
	
	/**
	 * 监听值显示控件
	 * 1.回车打开数据表格
	 * 2.光标移开打开数据表格
	 */
	private void createValueListener() {
		//回车打开数据表格
		valueText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(10 == e.getKeyCode()) {
					lookPanel.getSelectText().setText(valueText.getText());
					lookPanel.getCombo().setSelectedItem(bindField);
					lookPanel.addTableValue(ijLookupData.getJLookupData(bindField, valueText.getText()));
					lookPanel.setVisible(true);
				}
			}
		});
	}
	
	/**
	 * 表格事件
	 * 1.监听双击事件，赋值值显示控件
	 * 2.窗口关闭事件，判断是否值改变
	 */
	private void createTableListener() {
		//表格控件，监听双击事件
		lookPanel.getObjList().addMouseListener(new MouseAdapter() {			
			/**
			 * <p>Title:mousePressed
			 * <p>Description:按鼠标事件，如果是鼠标双击事件，则设置文本显示，发出数据改变事件</p>
			 * @param e
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				if(lookPanel.getObjList().getSelectedRow() != -1) {
					if(e.getClickCount() == 2) {
						T text = lookPanel.getValue();
						valueText.setText(getFieldValue(text));
						setValue(text);
						if(!valueEquals(newValue,oldValue)) {
							notifyDataChangedEvent();
						}
						lookPanel.setVisible(false);
					}
				}
			}
		});
		
		/**
		 * 窗口关闭事件，判断是否值改变
		 */
		/*lookPanel.addWindowListener(new WindowAdapter() {
			*//**
			 * 
			 * <p>Title:windowClosing
			 * <p>Description:窗口关闭事件，当窗口关闭时触发
			 * 窗口关闭时，发出数据修改事件</p>
			 * @param arg0
			 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
			 *//*
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(lookPanel.objList.getSelectedRow() != -1){
					T text = lookPanel.getValue();
					valueText.setText(getFieldValue(text));
					setValue(text);
					if(!valueEquals(newValue,oldValue)){
						notifyDataChangedEvent();
					}
					lookPanel.setVisible(false);
				}else{
					if(!valueEquals(newValue,oldValue)){
						notifyDataChangedEvent();
					}
				}
			}
		});*/
	}

	/**
	 * 判断两个对象是否相等
	 * @param t1
	 * @param t2
	 * @return
	 */
	private boolean valueEquals(T t1,T t2) {
		if(t1 == t2) {
			return true;
		}
		
		if(t1 == null || t2 == null) {
			return false;
		}
		
		if(getFieldValueMap(t1).equals(getFieldValueMap(t2))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:getOldValue
	 * @Description:获取old值
	 * @param @return
	 * @return T
	 * @throws
	 */
	public T getOldValue() {
		return oldValue;
	}
	
	/**
	 * 
	 * @Title:setOldValue
	 * @param @param oldValue
	 * @return void
	 * @throws
	 */
	private void setOldValue(T oldValue) {
		this.oldValue = oldValue;
	}
}