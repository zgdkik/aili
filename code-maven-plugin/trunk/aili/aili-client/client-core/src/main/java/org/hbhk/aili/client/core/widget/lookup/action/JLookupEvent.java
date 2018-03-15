package org.hbhk.aili.client.core.widget.lookup.action;

import java.util.EventObject;
/**
* Description:搜索事件
 */
public class JLookupEvent extends EventObject {
	private static final long serialVersionUID = 5925947997809816337L;
	
	// 事件来源
	private Object obj;
	
	/**
	 * 
	 * <p>Title: JLookupEvent</p>
	 * <p>Description: 构造函数</p>
	 * @param arg0 事件来源
	 */
	public JLookupEvent(Object arg0) {
		super(arg0);
		obj = source;
	}
	
	/*
	 * (non-Javadoc)
	 * <p>Title: getSource</p>
	 * <p>Description: 获取事件来源</p>
	 * @return
	 * @see java.util.EventObject#getSource()
	 */
	public Object getSource() {
         return obj;
	}
}