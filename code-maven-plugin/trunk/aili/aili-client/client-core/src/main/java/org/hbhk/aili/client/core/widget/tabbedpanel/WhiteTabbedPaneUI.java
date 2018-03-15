package org.hbhk.aili.client.core.widget.tabbedpanel;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.JDBasicTabbedPaneUI;
import javax.swing.text.View;

/**
 * 
 * GUI JTabbedPanel页面样式
 */
public class WhiteTabbedPaneUI extends JDBasicTabbedPaneUI {

	/**
	 * 加粗后文字
	 */
	private Font boldFont;

	/**
	 * 加粗后文字大小
	 */
	private FontMetrics boldFontMetrics;
	
	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		y = isSelected ? y+2:y;
		Graphics2D g2d = ( Graphics2D )g;
		Stroke oldStrock = g2d.getStroke();
	    BasicStroke stokeLine = new BasicStroke(3);
	    g2d.setStroke( stokeLine );
		
        g.setColor(lightHighlight);  
        
        g.drawLine(x, y, x, y+h); // left
        g.drawLine(x, y, x+w, y); // top
        g.drawLine(x+w, y, x+w, y+h); // right
        
        g2d.setStroke( oldStrock );
    }

	@Override
	protected int calculateTabHeight(int arg0, int arg1, int arg2) {
//		int oldHeight = super.calculateTabHeight(arg0, arg1, arg2);
//		//拉高Tab页
//		return oldHeight + 10;
		return 32;
	}

	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		int oldWidth = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
		//加宽Tab页
		return oldWidth + 60;
	}
    

	@Override
	protected void layoutLabel(int tabPlacement, FontMetrics metrics,
			int tabIndex, String title, Icon icon, Rectangle tabRect,
			Rectangle iconRect, Rectangle textRect, boolean isSelected) {
		textRect.x = textRect.y = iconRect.x = iconRect.y = 0;

		View v = getTextViewForTab(tabIndex);
		if (v != null) {
			tabPane.putClientProperty("html", v);
		}

		//修改后图片显示在文字右方
		SwingUtilities.layoutCompoundLabel((JComponent) tabPane, metrics,
				title, icon, SwingUtilities.CENTER, SwingUtilities.CENTER,
				SwingUtilities.CENTER, SwingUtilities.LEADING, tabRect,
				iconRect, textRect, textIconGap);

		tabPane.putClientProperty("html", null);

		int xNudge = getTabLabelShiftX(tabPlacement, tabIndex, isSelected);
		int yNudge = getTabLabelShiftY(tabPlacement, tabIndex, isSelected);
		iconRect.x += xNudge;
		iconRect.y += yNudge;
		textRect.x += xNudge;
		textRect.y += yNudge;
	}
	
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w+1, h, isSelected);
		//绘制按钮
		if(tabIndex>0){
			if(tabPane instanceof JClosableTabbedPane){
				CloseTabIcon closeIcon = ((JClosableTabbedPane)tabPane).icon;
				closeIcon.paintIcon(tabPane,
						g, x+w-closeIcon.width - 8, 6);
			}
		}
	}

	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font,
			FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		if (isSelected) {
			//选中后的文字加粗
			int vDifference = (int) (boldFontMetrics.getStringBounds(title, g)
					.getWidth()) - textRect.width;
			textRect.x -= (vDifference / 2);
			super.paintText(g, tabPlacement, boldFont, boldFontMetrics,
					tabIndex, title, textRect, isSelected);
		} else {
			super.paintText(g, tabPlacement, font, metrics, tabIndex, title,
					textRect, isSelected);
		}
	}

	@Override
	protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
			boolean isSelected) {
		return isSelected ? 1 : 0;
	}
	
	@Override
	protected void paintContentBorder(Graphics g, int tabPlacement,
			int selectedIndex) {
	}
	
	@Override
	protected void installDefaults() {
//		UIManager.put("TabbedPane.tabsOpaque", false);
//		UIManager.put("TabbedPane.opaque", false);
		

		//阴影（右侧背景色）
		UIManager.put("TabbedPane.shadow", new ColorUIResource(181, 181, 181));
		UIManager.put("TabbedPane.darkShadow", new ColorUIResource(181, 181,
				181));
		//高亮（左上角背景色）
		UIManager.put("TabbedPane.light", new ColorUIResource(181, 181, 181));
		UIManager.put("TabbedPane.highlight",
				new ColorUIResource(181, 181, 181));

		// 字体
//		UIManager.put("TabbedPane.font", new FontUIResource(new Font("宋体",
//				Font.PLAIN, 13)));
		// 未选中的背景色
		UIManager.put("TabbedPane.background", new ColorUIResource(245, 245,
				245));
		// 未选中的文字颜色
//		UIManager.put("TabbedPane.foreground", new ColorUIResource(181, 181,
//				181));
		UIManager.put("TabbedPane.foreground", new ColorUIResource(0xb5b5b5));

		// 焦点默认不开启
		UIManager.put("TabbedPane.selectionFollowsFocus", Boolean.FALSE);

		// 选中后背景色
		UIManager
				.put("TabbedPane.selected", new ColorUIResource(255, 255, 255));
		// 选中后文字颜色//selectedForeground
//		UIManager.put("TabbedPane.selectedForeground", new ColorUIResource(55,
//				60, 100));
		UIManager.put("TabbedPane.selectedForeground", new ColorUIResource(0x3c3764));
		// UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(181,
		// 181, 181));

		//下方内容不显示边框
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
		//Tab页只显示下方间距
		UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 10, 3, 260));
		
//		UIManager.put("TabbedPane.tabInsets", new Insets(0,10,5,20));

		UIManager.put("TabbedPane.tabBorderInsets", new Insets(0, 10, 3, 260));
		
		//文字与图标显示间隔
		UIManager.put("TabbedPane.textIconGap", new Integer(15));
		// UIManager.put("TabbedPane.tabAreaInsets", new Insets(0,0,0,0) );
		// UIManager.put("TabbedPane.selectedTabPadInsets", new
		// Insets(10,20,10,20) );

		super.installDefaults();

		//保留加粗字体
		boldFont = tabPane.getFont().deriveFont(Font.BOLD);
		boldFontMetrics = tabPane.getFontMetrics(boldFont);
	}
	
}
