package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.element.Table;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Signature(
	returnType=List.class,
	requiredParamTypes=List.class,
	optionalParamTypes={
		QuickTable.class,
		Table.class
	}
)
public @interface TableValidator {
}
