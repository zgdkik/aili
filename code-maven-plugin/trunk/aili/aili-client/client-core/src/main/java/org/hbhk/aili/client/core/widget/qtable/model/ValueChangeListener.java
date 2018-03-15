package org.hbhk.aili.client.core.widget.qtable.model;

public interface ValueChangeListener {
	boolean valueChanging(Object source, Object oldValue, Object newValue);
	void valueChanged(Object source, Object oldValue, Object newValue);
}
