package org.hbhk.aili.client.core.widget.lookup.service;

import java.util.List;

/**
 * 
 * 获取数据源
 * @param <T>
 */
public interface IJLookupData<T> {
	/**
	 * 
	 * <p>Title: getJLookupData</p>
	 * <p>Description: 获取查找到的数据</p>
	 * @param fieldName 输入框名称
	 * @param fieldValue 输入框值
	 * @return
	 */
	public List<T> getJLookupData(String fieldName, String fieldValue);
}