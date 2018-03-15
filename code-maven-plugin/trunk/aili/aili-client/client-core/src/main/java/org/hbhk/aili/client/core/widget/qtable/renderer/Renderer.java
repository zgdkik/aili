package org.hbhk.aili.client.core.widget.qtable.renderer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

public interface Renderer {
	Color getForeground();
	Color getBackground();
	Font getFont();
	Border getBorder();
}
