package org.hbhk.aili.client.core.widget.qtable.renderer;

import java.awt.Color;

import javax.swing.UIManager;

public class DefaultSelectedRowRenderer extends DefaultCellRenderer {

	@Override
	public Color getForeground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.selectionForeground");
	}

	@Override
	public Color getBackground() {
		return UIManager.getLookAndFeelDefaults().getColor("Table.selectionBackground");
	}

}
