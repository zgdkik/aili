package org.hbhk.aili.client.core.core.workbench.impl.synthetica;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.hbhk.aili.client.core.commons.exception.NormalException;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.I18nTemplate;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.core.jpf.utils.JpfUtils;
import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.core.core.workbench.IInitializer;
import org.hbhk.aili.client.core.core.workbench.IWindowListener;
import org.hbhk.aili.client.core.core.workbench.IWorkbench;
import org.hbhk.aili.client.core.core.workbench.WindowConfig;
import org.hbhk.aili.client.core.core.workbench.WindowEvent;
import org.java.plugin.Plugin;

/**
 * 
 * 编辑器类 

 */
public class Editor implements IEditor {
	// 编辑器配置
	private EditorConfig config;
	private JTabbedPane container;

	// 滚动面板
	private JComponent jDockable;

	// 窗口监听列表
	protected List<IWindowListener> listeners;

	// 标题
	private String title;

	// 组件
	private JComponent component;

	// 插件
	private Plugin plugin;

	// 是否绑定
	protected boolean bindToControl;

	// 工作面板
	protected IWorkbench workbench;

	/**
	 * 
	 * <p>
	 * Title: Editor
	 * </p>
	 * <p>
	 * Description: 构造函数
	 * </p>
	 * 
	 * @param workingArea
	 *            工作区
	 * @param plugin
	 *            所属插件
	 * @param workbench
	 *            工作面板
	 * @param config
	 *            编辑器配置
	 */
	public Editor(JTabbedPane workingArea, Plugin plugin,
			IWorkbench workbench, EditorConfig config) {
		this.container = workingArea;
		this.config = config;
		this.plugin = plugin;
		this.workbench = workbench;
		listeners = new ArrayList<IWindowListener>();
	}

	/**
	 * 
	 * <p>
	 * Title: getDockable
	 * </p>
	 * <p>
	 * Description: 获取滚动面板
	 * </p>
	 * 
	 * @return
	 */
	public JComponent getDockable() {
		if (jDockable == null) {
			jDockable = new EditorDockable(this, this.component);
		}
		return jDockable;
	}

	/**
	 * 
	 * <p>
	 * Title: bindToControl
	 * </p>
	 * <p>
	 * Description: 绑定控件
	 * </p>
	 */
	protected void bindToControl() {
		//first tab can't close
    	if(container.getTabCount() == 0){
    		//第一个页签不能关闭
    		container.addTab(getTitle(), getDockable());
    	}else{
    		container.addTab(getTitle(), getIcon(), getDockable());
    	}
		
	}

	@Override
	public void setConfig(EditorConfig config) {
		this.config = config;
	}

	@Override
	public EditorConfig getConfig() {
		return config;
	}

	@Override
	public WindowConfig getWindowConfig() {
		return config;
	}

	/**
	 * 
	 * 功能：toFront
	 * 
	 * @param:
	 * @return:
	 * @since:1.6
	 */
	@Override
	public void toFront() {
		final JComponent dockable = this.getDockable();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				container.setSelectedComponent(dockable);
			}
		});
	}

	@Override
	public boolean isFront() {
		return container.getSelectedComponent() == this.getDockable();
	}

	@Override
	public void close() {
		if (closing()) {
			container.remove(this.getDockable());
			this.closed();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: closing
	 * </p>
	 * <p>
	 * Description: 关闭中处理函数
	 * </p>
	 * 
	 * @return
	 */
	public boolean closing() {
		boolean doIt = true;
		for (IWindowListener listener : listeners) {
			doIt &= listener.windowClosing(new WindowEvent(this));
		}

		return doIt;
	}

	/**
	 * 
	 * <p>
	 * Title: closed
	 * </p>
	 * <p>
	 * Description: 关闭后处理函数
	 * </p>
	 */
	public void closed() {
		for (IWindowListener listener : listeners) {
			listener.windowClosed(new WindowEvent(this));
		}
	}

	@Override
	public boolean isClosed() {
		int size = this.container.getTabCount();
		for (int i = 0; i < size; i++) {
			if (this.container.getComponentAt(i) == this.getDockable()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getTitle() {
		if (title == null) {
			if (component != null) {
				// II18n i18n =
				// workbench.getApplication().getI18n(component.getClass());
				II18n i18n = I18nManager.getI18n(component.getClass());
				title = I18nTemplate.merge(i18n, getWindowConfig().getTitle());
			} else {
				title = getWindowConfig().getTitle();
			}
		}

		return title;
	}

	@Override
	public Icon getIcon() {
		String sIcon = getWindowConfig().getIcon();
		if (sIcon == null) {
			return null;
		}

		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(
				plugin.getDescriptor());
		URL iconUrl = classLoader.getResource(sIcon);
		if (iconUrl == null){
			return null;
		}
			
		return new ImageIcon(iconUrl);
	}

	@Override
	public void setComponentType(Class<? extends JComponent> componentClass) {
		String beanName = componentClass.getName();
		int packageLength = componentClass.getPackage().getName().length();
		if (packageLength != 0) {
			beanName = beanName.substring(packageLength + 1);
		}

		beanName = Character.toLowerCase(beanName.charAt(0))
				+ beanName.substring(1);

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
			JComponent component = JpfUtils.createInstance(plugin,
					componentType);
			setComponent(component);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setComponent(JComponent component) {
		this.component = component;

		JpfUtils.injectObjects(plugin, component);

		if (component instanceof IInitializer) {
			((IInitializer) component).init();
		}
	}

	@Override
	public JComponent getComponent() {
		return component;
	}

	@Override
	public void addWindowListener(IWindowListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeWindowListener(IWindowListener listener) {
		listeners.remove(listener);
	}

	@Override
	public boolean isVisible() {
		return !this.isClosed();
	}

	@Override
	public void setVisible(boolean b) {
		if (!bindToControl) {
			this.bindToControl();
			bindToControl = true;
		}
		if (b) {
			if (this.isClosed()) {
				this.bindToControl();
			} else {
				this.toFront();
			}
		} else {
			this.close();
		}
	}
}