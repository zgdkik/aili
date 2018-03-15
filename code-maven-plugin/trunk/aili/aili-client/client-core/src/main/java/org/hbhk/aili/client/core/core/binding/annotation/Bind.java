package org.hbhk.aili.client.core.core.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *	绑定标注类，用于打在某个容器的某个控件属性上，
 *表示此空间将来会与某个JavaBean对象的某个属性进行绑定操作。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Bind {
	/**
	 * 运用的时候需要给标注传入一个字符串参数，表示需要绑定的JavaBean属性的名字。
	 * value
	 * @return String
	 * @since:0.6
	 */
	String value();
	
	String component() default "";
	
	String property() default "";
}
