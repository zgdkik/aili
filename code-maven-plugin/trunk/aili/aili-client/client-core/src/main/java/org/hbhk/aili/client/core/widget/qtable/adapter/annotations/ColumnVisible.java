package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Signatures({
	@Signature(
		returnType=boolean.class,
		requiredParamTypes=int.class
	),
	@Signature(
		returnType=boolean.class,
		requiredParamTypes=String.class
	)
})
public @interface ColumnVisible {
}
