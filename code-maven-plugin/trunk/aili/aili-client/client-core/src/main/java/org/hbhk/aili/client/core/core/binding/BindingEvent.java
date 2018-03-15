package org.hbhk.aili.client.core.core.binding;
/**
 * 
 *    绑定事件,这个事件在绑定属性值发生变化的时候触发。
 */
public class BindingEvent {
	
	/**
	 * 绑定的bean。
	 */
	private Object source;
	/**
	 * 发生变化的绑定属性名（绑定bean的）
	 */
	private String propertyName;
	private Object oldValue;
	private Object newValue;
	
	public Object getSource() {
		return source;
	}
	
	public void setSource(Object source) {
		this.source = source;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String bindingArg) {
		this.propertyName = bindingArg;
	}
	
	public Object getOldValue() {
		return oldValue;
	}
	
	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}
	
	public Object getNewValue() {
		return newValue;
	}
	
	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}
	
}
