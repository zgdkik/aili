package org.hbhk.aili.client.boot.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 部门缓存对象
 */
public class DepartmentCache {

	/**
	 * 定义静态的DepartmentEntity类型的List集合allDepts
	 */
	private static List allDepts;

	/**
	 * 日志对象
	 */
	public static final Log LOG = LogFactory.getLog(DepartmentCache.class);

	static void init() {
		try {
			/**
			 * allDepts设置为空
			 */
			allDepts = null;
		} catch (Exception t) {
			/**
			 * 记录错误日志
			 */
			LOG.error("EXCEPTION", t);
			/**
			 * 创建一个DepartmentEntity类型的List集合
			 */
			allDepts = new ArrayList();
		}
	}

	/**
	 * 获取所有部门
	 * 
	 * @return
	 */
	public static List getAllDepartments() {
		/**
		 * 返回allDepts
		 */
		return allDepts;
	}

}