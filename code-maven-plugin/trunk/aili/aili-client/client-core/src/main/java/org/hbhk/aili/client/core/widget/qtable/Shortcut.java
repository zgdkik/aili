package org.hbhk.aili.client.core.widget.qtable;

import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

public class Shortcut {
	private KeyStroke keyStroke;
	private ActionListener actionListener;
	
	public Shortcut() {
		this(null, null);
	}
	
	public Shortcut(KeyStroke keyStroke, ActionListener actionListener) {
		this.keyStroke = keyStroke;
		this.actionListener = actionListener;
	}
	
	public KeyStroke getKeyStroke() {
		return keyStroke;
	}
	
	public void setKeyStroke(KeyStroke keyStroke) {
		this.keyStroke = keyStroke;
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
}
