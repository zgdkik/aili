package org.hbhk.aili.client.core.core.binding;

/**
 * 
 *	绑定参数，由Bind注解的value提供，当绑定的组件是RadioButton时，
 *  value会倍转化成这种类型的参数，对于其它组件，则绑定参数直接是
 *  表示绑定属性名的字符串。
 */
public class RadioButtonBindingArg {
	private String propertyName;
	private Object choice;
	
	public RadioButtonBindingArg(String propertyName, Object choice) {
		this.propertyName = propertyName;
		this.choice = choice;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public Object getChoice() {
		return choice;
	}
	
	public void setChoice(Object choice) {
		this.choice = choice;
	}
	
}
