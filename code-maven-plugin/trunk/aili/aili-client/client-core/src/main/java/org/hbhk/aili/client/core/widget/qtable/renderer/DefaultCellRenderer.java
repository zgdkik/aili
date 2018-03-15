package org.hbhk.aili.client.core.widget.qtable.renderer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.border.Border;


public class DefaultCellRenderer implements Renderer {
	@Override
	public Color getForeground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.foreground");
	}

	@Override
	public Color getBackground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.background");
	}

	@Override
	public Font getFont() {
		return UIManager.getLookAndFeelDefaults().getFont("Table.font");
	}

	@Override
	public Border getBorder() {
		return null;
	}

}
