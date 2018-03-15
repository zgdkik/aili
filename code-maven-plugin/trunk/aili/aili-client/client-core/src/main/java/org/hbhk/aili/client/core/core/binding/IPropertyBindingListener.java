package org.hbhk.aili.client.core.core.binding;

/**
 * 
 * <p>Description: 属性绑定监听器接口</p>
 */
public interface IPropertyBindingListener {
	/**
	 * 
	 * <p>Title: bindingTriggered</p>
	 * <p>Description: 触发绑定事件</p>
	 * @param event 绑定事件
	 */
	void bindingTriggered(BindingEvent event);
	
	/**
	 * 
	 * 是否允许vo.set***时也触发绑定事件
	 */
	boolean isFromVoTargetEnable();
}