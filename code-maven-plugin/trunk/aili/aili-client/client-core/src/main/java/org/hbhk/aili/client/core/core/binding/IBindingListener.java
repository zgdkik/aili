package org.hbhk.aili.client.core.core.binding;

import java.util.List;

/**
 * 
 *	绑定监听器，当一个绑定完成之后，数据变化发生之后触发。
 */
public interface IBindingListener {
	/**
	 * 绑定监听器，当一个绑定完成之后，数据变化发生之后触发该方法。
	 * bindingTriggered
	 * @param events void 绑定事件列表
	 * 绑定事件集合
	 * @since:0.6
	 */
	void bindingTriggered(List<BindingEvent> events);
	
	/**
	 * 
	 * 是否允许vo.set***时也触发绑定事件
	 * @author 102246-foss-shaohongliang
	 * @date 2013-1-10 上午7:59:56
	 */
	boolean isFromVoTargetEnable();
}
