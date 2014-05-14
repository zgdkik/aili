package org.hbhk.aili.cache.server.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParameterKeyProvider {

	   /**
     * If more than one parameter in method declaration is annotated then this parameter tells about the order of params
     * in cache key.
     * @return order of the parameter in generated cache key
     */
    int order() default 0;
}
