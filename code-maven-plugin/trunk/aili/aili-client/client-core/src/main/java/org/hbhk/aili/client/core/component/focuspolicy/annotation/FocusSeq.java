package org.hbhk.aili.client.core.component.focuspolicy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FocusSeq {

	
	
	int  ContainerSeq() default -1;;
	
	int  seq();
	
}
