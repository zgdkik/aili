package org.hbhk.aili.client.core.core.workbench.impl.synthetica;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;

import org.hbhk.aili.client.core.commons.exception.NormalException;
import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.core.jpf.utils.JpfUtils;
import org.hbhk.aili.client.core.core.workbench.IInitializer;
import org.hbhk.aili.client.core.core.workbench.IWindow;
import org.hbhk.aili.client.core.core.workbench.IWindowListener;
import org.hbhk.aili.client.core.core.workbench.IWorkbench;
import org.java.plugin.Plugin;

/**
 * 
 * <p>Description: 窗口类</p>
 *
 */
public abstract class Window implements IWindow {
	// 插件
	protected Plugin plugin;
	
	// 控件
	protected JComponent component;
	
	// 是否绑定
	protected boolean bindToControl;
	
	// 窗口监听器列表
	protected List<IWindowListener> listeners;
	
	// 工作面板
	protected IWorkbench workbench;
	
	// 标题
	protected String title;
	
	/**
	 * 
	 * <p>Title: Window</p>
	 * <p>Description: 构造函数</p>
	 * @param plugin 插件
	 * @param workbench 工作面板
	 */
	public Window(Plugin plugin, IWorkbench workbench) {
		this.plugin = plugin;
		this.workbench = workbench;
		listeners = new ArrayList<IWindowListener>();
	}

	@Override
	public String getTitle() {
		if (title == null) {
			title = getWindowConfig().getTitle();
		}
		
		return title;
	}

	@Override
	public Icon getIcon() {
		String sIcon = getWindowConfig().getIcon();
		if (sIcon == null) {
			return null;
		}
		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
		return ImageUtil.getImageIcon(classLoader, sIcon);
	}
	
	@Override
	public void setComponentType(Class<? extends JComponent> componentClass) {
		String beanName = componentClass.getName();
		int packageLength = componentClass.getPackage().getName().length();
		if (packageLength != 0) {
			beanName = beanName.substring(packageLength + 1);
		}
		
		beanName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
		
		try {
			setComponentType(beanName);
		} catch (Exception e) {
			try {
				setComponent(componentClass.newInstance());
			} catch (Exception e1) {
				throw new NormalException(e1);
			}
		}
	}
	
	@Override
	public void setComponentType(String componentType) {
		try {
			JComponent cp = JpfUtils.createInstance(plugin,componentType);
			setComponent(cp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setComponent(JComponent component) {
		this.component = component;
		
		JpfUtils.injectObjects(plugin, component);

		if (component instanceof IInitializer) {
			((IInitializer)component).init();
		}
		this.getDockable().setLayout(new BorderLayout());
		getDockable().add(component);
	}

	@Override
	public JComponent getComponent() {
		return component;
	}
	
	@Override
	public void setVisible(boolean b) {
		if (!bindToControl) {
			bindToControl();
			bindToControl = true;
		}
		
		if (b == isVisible()){
			return;
		}
			
		if (!b) {
			this.getDockable().setVisible(false);
		} else {
			this.getDockable().setVisible(true);
		}
	}
	
	@Override
	public boolean isVisible() {
		return getDockable().isVisible();
	}
	
	@Override
	public void addWindowListener(IWindowListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeWindowListener(IWindowListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * 
	 * <p>Title: bindToControl</p>
	 * <p>Description: 绑定控件</p>
	 */
	protected abstract void bindToControl();
	
	/**
	 * 
	 * <p>Title: getDockable</p>
	 * <p>Description: 获取视图</p>
	 * @return
	 */
	protected abstract JComponent getDockable();
}
