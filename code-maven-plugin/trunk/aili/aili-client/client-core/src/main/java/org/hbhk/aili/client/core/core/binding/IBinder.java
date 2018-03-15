package org.hbhk.aili.client.core.core.binding;

import java.util.List;

import org.hbhk.aili.client.core.commons.validation.IValidationListener;
import org.hbhk.aili.client.core.commons.validation.ValidationError;

/**
 * 
 *绑定器接口，绑定完成后会返回一个IBinder，程序员可以持有一个IBinder，
 *IBinder是一个PropertyChangeListener，
 *并且通过这个事件来通知ValidatorListener以及BindListener，从而完成和UI层的交互。
 */
public interface IBinder<T> {
	/**
	 * 向绑定器添加绑定监听器，在属性值进行传递的时候会调用绑定监听器，并且传入绑定事件
	 * addBindingListener
	 * @param listener void
	 * 绑定监听器
	 * @since:0.6
	 */
	void addBindingListener(IBindingListener listener);
	
	/**
	 * 移除绑定监听器
	 * removeBindingListener
	 * @param listener void 绑定监听器
	 * @since:0.6
	 */
	void removeBindingListener(IBindingListener listener);
	
	/**
	 * 添加校验监听器，在校验过程中会调用此监听器。
	 * addValidationListener
	 * @param listener void 校验监听器
	 * @since:0.6
	 */
	void addValidationListener(IValidationListener listener);
	
	/**
	 * 移除校验监听器
	 * removeValidationListener
	 * @param listener void 校验监听器
	 * @since:0.6
	 */
	void removeValidationListener(IValidationListener listener);
	
	/**
	 * 校验
	 * validate
	 * @return List<ValidationError>
	 * 校验失败后返回校验错误信息，信息包装在ValidationError里面通过List集合返回。
	 * @since:0.6
	 */
	List<ValidationError> validate();
	
	/**
	 * 返回绑定成功之后的JavaBean对象
	 * getBean
	 * @return T
	 * @since:0.6
	 */
	T getBean();
	
	/**
	 * 
	 * <p>Title: addPropertyBindingListener</p>
	 * <p>Description: 添加属性绑定监听器</p>
	 * @param property 属性
	 * @param listener 属性绑定监听器
	 */
	void addPropertyBindingListener(String property, IPropertyBindingListener listener);
	
	/**
	 * 
	 * <p>Title: removePropertyBindingListener</p>
	 * <p>Description: 根据属性名和属性绑定监听器移除属性绑定监听器</p>
	 * @param property 属性
	 * @param listener 属性绑定监听器
	 */
	void removePropertyBindingListener(String property, IPropertyBindingListener listener);
	
	/**
	 * 
	 * <p>Title: removePropertyBindingListener</p>
	 * <p>Description: 根据属性名移除属性绑定监听器</p>
	 * @param property 属性
	 */
	void removePropertyBindingListener(String property);
}