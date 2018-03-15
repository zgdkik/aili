package org.hbhk.aili.client.core.widget.statusbar;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JToolBar;
/**
* <b style="font-family:微软雅黑"><small>Description:状态栏</small></b>   </br>
 */
public class StatusBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private final JPanel leftPanel;

	private final JPanel centerPanel;

	private final JPanel rightPanel;
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 */
	public StatusBar() {
		leftPanel = new JPanel();
		leftPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		centerPanel = new JPanel();
		centerPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		rightPanel = new JPanel();
		rightPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		
		setFloatable(false);
		setLayout(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	/**
	 * 
	 * @Title:addToLeft
	 * @Description:TODO
	 * @param @param c
	 * @return void
	 * @throws
	 */
	public void addToLeft(Component c) {
		leftPanel.add(c);
	}
	/**
	 * 
	 * @Title:addToCenter
	 * @Description:TODO
	 * @param @param c
	 * @return void
	 * @throws
	 */
	public void addToCenter(Component c) {
		centerPanel.add(c);
	}
	/**
	 * 
	 * @Title:addToRight
	 * @Description:TODO
	 * @param @param c
	 * @return void
	 * @throws
	 */
	public void addToRight(Component c) {
		rightPanel.add(c);
	}
	/**
	 * 
	 * @Title:getLeftPanel
	 * @Description:TODO
	 * @param @return
	 * @return JPanel
	 * @throws
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}
	/**
	 * 
	 * @Title:getRightPanel
	 * @Description:TODO
	 * @param @return
	 * @return JPanel
	 * @throws
	 */
	public JPanel getRightPanel() {
		return rightPanel;
	}
	/**
	 * 
	 * @Title:getCenterPanel
	 * @Description:TODO
	 * @param @return
	 * @return JPanel
	 * @throws
	 */
	public JPanel getCenterPanel() {
		return centerPanel;
	}

}
