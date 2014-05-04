package org.hbhk.module.framework.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)	// 运行时保留
@Target({ElementType.METHOD})		// 注解对象为方法
public @interface SecurityFilter {
	
	public String sec() default  "none";	// 参数名
}
