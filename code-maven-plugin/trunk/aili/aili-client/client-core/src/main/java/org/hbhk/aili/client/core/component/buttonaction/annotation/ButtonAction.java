package org.hbhk.aili.client.core.component.buttonaction.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hbhk.aili.client.core.component.buttonaction.IButtonActionListener;

/**
 * 
 * 
 * Description:用于按钮行为，主要有3个参数，1、需要绑定的快捷键
 * 2需要使用的action 3、指定图标的ICON名称</small></b> </br> <b
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ButtonAction {
	/**
	 * 需要绑定的快捷键 type
	 * 
	 * @return
	 * @return string
	 * @since:0.6
	 */
	String shortcutKey() default "";

	/**
	 * 用于快捷键调用和action事件notify的 type
	 * 
	 * @return
	 * @return Class<? extends IButtonActionListener>
	 * @since:0.6
	 */
	Class<? extends IButtonActionListener> type();

	/**
	 * 指定图标的ICON名称 type
	 * 
	 * @return
	 * @return string
	 * @since:0.6
	 */
	String icon() default "";



}
