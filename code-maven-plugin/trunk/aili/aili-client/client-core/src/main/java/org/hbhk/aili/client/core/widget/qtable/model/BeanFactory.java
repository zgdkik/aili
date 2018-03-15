package org.hbhk.aili.client.core.widget.qtable.model;

public interface BeanFactory<T> {
	T create();
	Class<T> getType();
}
