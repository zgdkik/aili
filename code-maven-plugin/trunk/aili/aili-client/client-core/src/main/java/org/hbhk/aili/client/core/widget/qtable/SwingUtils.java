package org.hbhk.aili.client.core.widget.qtable;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public final class SwingUtils {
	
	private SwingUtils(){
		
	}
	public static void removeComponentDefaultBehaviors(JComponent component) {
		for (FocusListener listener : component.getFocusListeners()) {
			component.removeFocusListener(listener);
		}

		for (MouseListener listener : component.getMouseListeners()) {
			component.removeMouseListener(listener);
		}

		for (MouseMotionListener listener : component.getMouseMotionListeners()) {
			component.removeMouseMotionListener(listener);
		}
	}
}
