package org.hbhk.aili.client.boot.cache;

import org.hbhk.aili.client.boot.autorun.IAutoRunner;
import org.hbhk.aili.client.core.commons.task.ITaskContext;

/**
 * 缓存自动运行对象
 */
public class CacheAutorun implements IAutoRunner {

	@Override
	public void execute(ITaskContext context) throws Exception {
		/**
		 * 调用部门缓存对象的init方法
		 */
		DepartmentCache.init();
	}

}