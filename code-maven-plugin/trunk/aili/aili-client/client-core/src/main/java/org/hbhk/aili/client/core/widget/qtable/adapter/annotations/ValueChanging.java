package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.element.Row;
import org.hbhk.aili.client.core.widget.qtable.element.Table;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Signature(
	returnType=boolean.class,
	requiredParamTypes={
			Object.class,
			Object.class,
			Object.class
	},
	optionalParamTypes={
		QuickTable.class,
		Table.class,
		Row.class
	}
)
public @interface ValueChanging {
	String value();
}
