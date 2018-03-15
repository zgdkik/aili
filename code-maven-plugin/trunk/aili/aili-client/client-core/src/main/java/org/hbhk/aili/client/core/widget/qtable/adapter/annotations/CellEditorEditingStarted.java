package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hbhk.aili.client.core.widget.qtable.QuickTable;
import org.hbhk.aili.client.core.widget.qtable.editor.CellEditor;
import org.hbhk.aili.client.core.widget.qtable.element.Cell;
import org.hbhk.aili.client.core.widget.qtable.element.Row;
import org.hbhk.aili.client.core.widget.qtable.element.Table;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Signature(
	requiredParamTypes=CellEditor.class,
	optionalParamTypes={
		QuickTable.class,
		Table.class,
		Row.class,
		Cell.class
	}
)
public @interface CellEditorEditingStarted {
}
