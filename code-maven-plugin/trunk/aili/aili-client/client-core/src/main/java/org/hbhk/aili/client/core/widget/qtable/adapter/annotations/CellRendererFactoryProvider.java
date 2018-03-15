package org.hbhk.aili.client.core.widget.qtable.adapter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hbhk.aili.client.core.widget.qtable.renderer.CellRendererFactory;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Signature(
	returnType=CellRendererFactory.class
)
public @interface CellRendererFactoryProvider {
	String value();
}
