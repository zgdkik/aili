package org.hbhk.aili.client.core.core.workbench;
/**
 * 
 *绑定或者解除绑定某个IWindow对象到指定的行为。
 */
public interface IAction {
	void bind(IWindow window);
	void unbind(IWindow window);
}
