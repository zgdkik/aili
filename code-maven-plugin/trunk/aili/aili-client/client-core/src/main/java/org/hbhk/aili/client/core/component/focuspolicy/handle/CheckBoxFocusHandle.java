package org.hbhk.aili.client.core.component.focuspolicy.handle;

import java.awt.Component;

import javax.swing.JCheckBox;

import org.hbhk.aili.client.core.component.focuspolicy.IFocusPolicyHandle;
import org.hbhk.aili.client.core.component.focuspolicy.KeyType;

/**
 * 
 * @内容：ComboxFocusHandle
 * @作者：林文升
 * @时间：2012-5-9
 */
public class CheckBoxFocusHandle implements IFocusPolicyHandle<JCheckBox> {

	@Override
	public KeyType getKeyCode() {
		return KeyType.key_enter;
	}

	@Override
	public Class<JCheckBox> getType() {
		return JCheckBox.class;
	}

	@Override
	public void handle(JCheckBox cb,Component[] components,int currentPoint) {
		if (cb.isSelected()) {
			cb.setSelected(false);
		} else {
			cb.setSelected(true);
		}
	}



}
