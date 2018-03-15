package org.hbhk.aili.client.core.commons.binding;

import java.util.Map;
/**
* 数据绑定的参数织入器
 */
public interface IBindingArgsAware {
	/**
	 * 
	 * @Title:setArgs
	 * @Description:实现此方法，设置参数
	 * @param @param args 参数映射
	 * @return void
	 * @throws
	 */
	void setArgs(Map<String, String> args);
}
