package org.hbhk.aili.client.core.component.focuspolicy;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Arrays;
import java.util.List;

public class TabFocusTraversPolicy extends FocusTraversalPolicy {
	private Component[] compFocus;
	private List list;

	public TabFocusTraversPolicy() {
	}

	public TabFocusTraversPolicy(Component[] compFocus) {
		this.compFocus = compFocus;
		list = Arrays.asList(compFocus);
	}

	public Component getFirstComponent(Container focusCycleRoot) {
		return compFocus[0];
	}

	public Component getLastComponent(Container focusCycleRoot) {
		return compFocus[compFocus.length - 1];
	}

	public Component getComponentAfter(Container focusCycleRoot,
			Component aComponent) {
		
		if(aComponent!=null){
		int index = list.indexOf(aComponent);
		try{
		if (compFocus[(index + 1) % compFocus.length].isEnabled()
				&& compFocus[(index + 1) % compFocus.length].isVisible()) {
			return compFocus[(index + 1) % compFocus.length];
		} else {
			return getComponentAfter(focusCycleRoot, compFocus[(index + 1)
					% compFocus.length]);
		}
		}catch (java.lang.StackOverflowError e) {
			return null;
		}
		}else{
			return null;
		}
	}

	public Component getComponentBefore(Container focusCycleRoot,
			Component aComponent) {
		int index = list.indexOf(aComponent);
		if (compFocus[(index - 1 + compFocus.length) % compFocus.length]
				.isEnabled()
				&& compFocus[(index - 1 + compFocus.length) % compFocus.length].isVisible()) {
			return compFocus[(index - 1 + compFocus.length) % compFocus.length];
		} else {
			return getComponentBefore(
					focusCycleRoot,
					compFocus[(index - 1 + compFocus.length) % compFocus.length]);
		}
	}

	public Component getDefaultComponent(Container focusCycleRoot) {
		return compFocus[0];
	}
	
}