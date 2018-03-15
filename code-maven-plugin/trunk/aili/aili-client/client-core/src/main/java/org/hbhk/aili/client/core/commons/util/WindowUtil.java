package org.hbhk.aili.client.core.commons.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public final class WindowUtil {
	
	public static final Insets NONE_INSETS = new Insets(0, 0, 0, 0);
	
	public WindowUtil(){
		
	}
	
	/**
	 * 置于屏幕中间
	 * 
	 * @param aWindow
	 */
	public static void centerAndShow(Window aWindow) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension window = aWindow.getSize();
		if (window.height > screen.height) {
			window.height = screen.height;
		}
		if (window.width > screen.width) {
			window.width = screen.width;
		}
		int xCoord = (screen.width / 2 - window.width / 2);
		int yCoord = (screen.height / 2 - window.height / 2);
		aWindow.setLocation(xCoord, yCoord);
		aWindow.setVisible(true);
	}

	public static JDialog createDlg(Component parent, String title) {
		JFrame ancestor = null;
		if (parent != null)
			ancestor = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class,
					parent);

		JDialog ctnDlg = new JDialog(ancestor, title, true);
		ctnDlg.getContentPane().add(parent);
		return ctnDlg;
	}
	
	public static void center(JDialog dialog ){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int) (d.getWidth() - dialog.getWidth()) / 2, (int) (d.getHeight() - dialog.getHeight()) / 2);
	}
	
	
}
