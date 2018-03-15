package org.hbhk.aili.client.core.core.binding.annotation;

import java.util.Map;

import org.hbhk.aili.client.core.commons.binding.IBindingArgsAware;

/**
 * 
 *   校验器（工厂），转换器（工厂）在工作过程中如果需要获得绑定参数，
 *   可以声明为此接口来获得绑定参数。
 */
public class BindingArgsAware implements IBindingArgsAware {
	protected Map<String, String> args;

	@Override
	public void setArgs(Map<String, String> args) {
		this.args = args;
	}

	
	
}