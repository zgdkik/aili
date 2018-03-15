package org.hbhk.aili.client.core.widget.qtable.model;

public class ValueChangeAdapter implements ValueChangeListener {

	@Override
	public boolean valueChanging(Object source, Object oldValue, Object newValue) {
		return true;
	}

	@Override
	public void valueChanged(Object source, Object oldValue, Object newValue) {}

}
