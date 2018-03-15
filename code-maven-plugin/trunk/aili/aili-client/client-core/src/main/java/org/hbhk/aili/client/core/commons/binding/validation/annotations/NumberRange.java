package org.hbhk.aili.client.core.commons.binding.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface NumberRange {
	double min() default java.lang.Double.MIN_VALUE;
	double max() default java.lang.Double.MAX_VALUE;
}
