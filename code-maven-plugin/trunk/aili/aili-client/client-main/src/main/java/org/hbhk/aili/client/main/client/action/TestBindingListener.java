package org.hbhk.aili.client.main.client.action;

import java.util.List;

import org.hbhk.aili.client.core.core.binding.BindingEvent;
import org.hbhk.aili.client.core.core.binding.IBindingListener;
import org.hbhk.aili.client.main.client.ui.TestUI;

public class TestBindingListener implements IBindingListener {

	public TestBindingListener(TestUI testUI) {
	}

	@Override
	public void bindingTriggered(List<BindingEvent> events) {
		
	}

	@Override
	public boolean isFromVoTargetEnable() {
		return false;
	}

}
