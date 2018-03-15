package org.hbhk.aili.client.core.widget.lookup.action;

import java.util.EventListener;

/**
* Description:搜索监听器
 */
public interface JLookupListener extends EventListener {
	/**
	 * 
	 * <p>Title: addDataChangedListener</p>
	 * <p>Description: 添加数据更改监听器</p>
	 * @param dm 查找事件
	 */
	 public void addDataChangedListener(JLookupEvent dm);
}
