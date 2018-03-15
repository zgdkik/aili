package org.hbhk.aili.client.core.core.binding;

/**
 * 
 *	绑定参数包装类，包装了JavaBean对象需要绑定的属性的名字
 */
public interface IBindingArgWrapper {
	/**
	 * 获取属性名字
	 * getPropertyName
	 * @return String
	 * @since:0.6
	 */
	String getPropertyName();
	
	/**
	 * 此参数在必要的时候也用来包装属性名字和其他一些信息
	 * getArg
	 * @return Object
	 * @since:0.6
	 */
	Object getArg();
}