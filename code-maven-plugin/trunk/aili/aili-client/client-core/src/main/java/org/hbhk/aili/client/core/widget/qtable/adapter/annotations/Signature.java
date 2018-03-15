package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature {
	Class<?> returnType() default void.class;
	Class<?>[] requiredParamTypes() default {};
	Class<?>[] optionalParamTypes() default {};
}
