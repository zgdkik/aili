package org.hbhk.aili.client.core.widget.qtable.renderer;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.Border;

public class DefaultSelectedCellRenderer extends DefaultCellRenderer {
	@Override
	public Color getForeground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.focusCellForeground");
	}
	
	@Override
	public Color getBackground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.focusCellBackground");
	}
	
	@Override
	public Border getBorder() {
		return UIManager.getLookAndFeelDefaults().getBorder("Table.focusCellHighlightBorder");
	}
}
