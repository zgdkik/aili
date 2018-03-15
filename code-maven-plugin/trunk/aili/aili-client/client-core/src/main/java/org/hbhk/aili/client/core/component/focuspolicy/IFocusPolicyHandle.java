package org.hbhk.aili.client.core.component.focuspolicy;

import java.awt.Component;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @内容：扩展接口
 */
public interface IFocusPolicyHandle<T extends Component> {
	/**
	 * 
	 * @内容：获取
	 */

	public KeyType getKeyCode();

	public Class<T> getType();

	public void handle(T component, Component[] components, int currentPoint);

}
