package org.hbhk.aili.client.core.component.focuspolicy.handle;

import java.awt.Component;

import javax.swing.JButton;

import org.hbhk.aili.client.core.component.focuspolicy.IFocusPolicyHandle;
import org.hbhk.aili.client.core.component.focuspolicy.KeyType;

public class ButtonFocusHandle implements IFocusPolicyHandle<JButton> {

	@Override
	public KeyType getKeyCode() {
		return KeyType.key_enter;
	}

	@Override
	public Class<JButton> getType() {
		return JButton.class;
	}

	@Override
	public void handle(JButton component, Component[] components,
			int currentPoint) {
		component.requestFocusInWindow();
	}

	

}
