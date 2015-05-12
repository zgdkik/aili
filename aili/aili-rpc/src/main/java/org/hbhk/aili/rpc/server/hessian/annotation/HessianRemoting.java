package org.hbhk.aili.rpc.server.hessian.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.remoting.caucho.HessianServiceExporter;

@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HessianRemoting {
	String name() default "";
    @SuppressWarnings("rawtypes")
	Class serviceExporter() default HessianServiceExporter.class;
    @SuppressWarnings("rawtypes")
	Class serviceInterface() default Object.class;
}
