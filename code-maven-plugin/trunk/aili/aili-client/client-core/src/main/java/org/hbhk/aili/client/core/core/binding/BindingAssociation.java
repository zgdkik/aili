package org.hbhk.aili.client.core.core.binding;

import java.awt.Component;

/**
 * 
 *	绑定关联类，主要是封装需要绑定的控件component及需要绑定的Bean对象的属性名字。
 */
public class BindingAssociation {
	private Component component;
	private IBindingArgWrapper argWrapper;
	
	public BindingAssociation(Component component, IBindingArgWrapper arg) {
		this.component = component;
		this.argWrapper = arg;
	}
	/**
	 * 获取需要绑定的控件对象
	 * getComponent
	 * @return Component
	 * @since:0.6
	 */
	public Component getComponent() {
		return component;
	}
	/**
	 * 设置需要绑定的控件对象
	 * setComponent
	 * @param component void
	 * @since:0.6
	 */
	public void setComponent(Component component) {
		this.component = component;
	}
	
	/**
	 * 获取绑定关联类中需要绑定的Bean参数
	 * getArgWrapper
	 * @return IBindingArgWrapper
	 * @since:0.6
	 */
	public IBindingArgWrapper getArgWrapper() {
		return argWrapper;
	}
	/**
	 * 设置绑定参数
	 * setArgWrapper
	 * @param arg void
	 * @since:0.6
	 */
	public void setArgWrapper(IBindingArgWrapper arg) {
		this.argWrapper = arg;
	}
}
