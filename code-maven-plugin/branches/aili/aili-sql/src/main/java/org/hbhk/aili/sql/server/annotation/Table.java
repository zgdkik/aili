package org.hbhk.aili.sql.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String value() ;
	boolean dynamicInsert() default false;
	boolean dynamicUpdate() default false;
}
