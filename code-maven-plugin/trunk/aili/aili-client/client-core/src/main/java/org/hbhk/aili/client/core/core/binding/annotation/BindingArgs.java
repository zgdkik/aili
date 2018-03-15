package org.hbhk.aili.client.core.core.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *   用来提供绑定参数。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface BindingArgs {
	
	/**
	 * 绑定参数列表
	 * @return
	 */
	BindingArg[] value();
}
