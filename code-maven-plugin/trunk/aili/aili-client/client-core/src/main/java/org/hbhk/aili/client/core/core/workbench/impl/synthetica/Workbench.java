package org.hbhk.aili.client.core.core.workbench.impl.synthetica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.util.KeyUtils;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.core.core.workbench.IView;
import org.hbhk.aili.client.core.core.workbench.IWorkbench;
import org.hbhk.aili.client.core.core.workbench.IWorkbenchListener;
import org.hbhk.aili.client.core.core.workbench.ViewConfig;
import org.hbhk.aili.client.core.core.workbench.ViewPosition;
import org.hbhk.aili.client.core.widget.tabbedpanel.JClosableTabbedPane;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.PluginManager;

/**
 * 
 * Description: 工作面板类
 * 
 */
@SuppressWarnings({ "unused" })
public class Workbench extends WindowAdapter implements IWorkbench {
	// 插件管理者
	private PluginManager pluginManager;

	// 视图
	private Map<String, IView> views;

	// 界面锁
	private Lock viewsLock = new ReentrantLock();

	// 保存文件
	private File store;

	// 依靠框架
	private DockableFrame frame;

	// 是否保存布局
	private boolean saveLayouts;

	// 应用程序
	private IApplication application;

	// 工作面板监听器列表
	private List<IWorkbenchListener> listeners;

	// 主视图
	private JPanel mainView;
	
	// 编辑器容器
	private JTabbedPane editorContainer ;
	
	// 日志对象
	private static final Log log = LogFactory.getLog(Workbench.class);
	
	private II18n i18n = I18nManager.getI18n(Workbench.class);

	private JPanel backgroundPanel;

	private JPanel frontPanel; 
	
	/**
	 * taskService 服务对象
	 */
	private transient ITaskService taskService;

	/**
	 * 
	 * <p>
	 * Title: Workbench
	 * </p>
	 * <p>
	 * Description: 构造函数
	 * </p>
	 * 
	 * @param application
	 *            应用程序
	 * @param pluginManager
	 *            插件管理者
	 * @param frame
	 *            可停靠框架
	 */
	public Workbench(IApplication application, PluginManager pluginManager, DockableFrame frame) {
		this.application = application;
		this.pluginManager = pluginManager;
		this.frame = frame;
		frame.setWorkbench(this);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		JPanel jPanel = new JPanel();
		Container co = frame.getContentPane();
		SpringLayout springLayout = new SpringLayout(); //使jp1使用OverlayLayout布局
		co.setLayout(springLayout); 
		backgroundPanel = createBackgroundPanel();
		frontPanel = createFrontPanel();
		co.add(frontPanel, new SpringLayout.Constraints(Spring.constant(0), Spring.constant(0),
				springLayout.getConstraint(SpringLayout.WIDTH, co), springLayout.getConstraint(SpringLayout.HEIGHT, co)));
		co.add(backgroundPanel, new SpringLayout.Constraints(Spring.constant(0), Spring.constant(0),
				springLayout.getConstraint(SpringLayout.WIDTH, co), springLayout.getConstraint(SpringLayout.HEIGHT, co)));

		editorContainer = new JClosableTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

		mainView = new JPanel();
		mainView.setOpaque(false);
		mainView.setLayout(new BorderLayout());
		mainView.add(editorContainer, BorderLayout.CENTER);
		frontPanel.add(BorderLayout.CENTER, editorContainer);
		views = new HashMap<String, IView>();
		saveLayouts = false;
		store = new File(System.getProperty("user.home"), "foss-workbench-store.xml");
		listeners = new ArrayList<IWorkbenchListener>();
	}
	
	/**
	 * 
	 * 前景页面
	 * @author 102246-foss-shaohongliang
	 * @date 2013-3-7 下午2:42:01
	 */
	private JPanel createFrontPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		panel.setAlignmentX(0);
		panel.setAlignmentY(0);
		return panel;
	}

	/**
	 * 
	 * 背景页面
	 */
	private JPanel createBackgroundPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setAlignmentX(0);
		panel.setAlignmentY(0);
		return panel;
	}


	/**
	 * <p>
	 * Title: close
	 * </p>
	 * <p>
	 * Description: 关闭处理函数
	 * </p>
	 */
	private void close() {
		List<IEditor> editors = getEditors();
		for (IEditor editor : editors) {
				editor.close();
			if (!editor.isClosed()) {
				return;
			}
		}

		save();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		for (IWorkbenchListener listener : listeners) {
			listener.workbenchOpened();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int ret = JOptionPane.showConfirmDialog(null, i18n.get("JOptionPane.ui.isExit"), "",
				JOptionPane.OK_CANCEL_OPTION);

		if (ret == JOptionPane.OK_OPTION) {
		close();

		for (IWorkbenchListener listener : listeners) {
			listener.workbenchClosed();
		}

		application.exit();
		}
		
	}

	/**
	 * 
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description: 保存处理函数
	 * </p>
	 */
	private void save() {
		if (!saveLayouts) {
			return;
		}

		if (!isVisible()){
			return;
		}
	}

	@Override
	public void restoreLayouts() {
		if (!saveLayouts){
			return;
		}

		if (!store.exists()) {
			return;
		}

	}

	@Override
	public IView createView(ViewConfig config) {
		return null;
	}

	@Override
	public IEditor createEditor(EditorConfig config) {
		try {
			IEditor editor = new Editor(this.editorContainer, pluginManager.getPlugin(config.getPluginId()), this, config);
			return editor;
		} catch (PluginLifecycleException e) {
			throw new RuntimeException(String.format("Can't find or activate plugin %s", config.getPluginId()), e);
		}
	}

	@Override
	public IView getView(String viewId) {
		try {
			viewsLock.lock();
			return views.get(viewId);
		} finally {
			viewsLock.unlock();
		}
	}

	@Override
	public IEditor getActivatedEditor() {
		Component comp = this.editorContainer.getSelectedComponent();
		if (comp != null) {
			return ((EditorDockable) comp).getEditor();
		}
		return null;
	}

	@Override
	public void setLayoutsStore(File store) {
		this.store = store;
	}

	@Override
	public File getLayoutsStore() {
		return store;
	}

	/**
	 * 
	 * <p>
	 * Title: setVisible
	 * </p>
	 * <p>
	 * Description: 设置是否可见
	 * </p>
	 * 
	 * @param b
	 */
	public void setVisible(boolean b) {
		// status doesn't change
		if (isVisible() == b) {
			return;
		}

		if (b) {
			frame.getContentPane().setLayout(new BorderLayout());
			frame.getContentPane().add(mainView);
			mainView.setVisible(true);
		} else {
			mainView.setVisible(false);
			frame.getContentPane().remove(mainView);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: isVisible
	 * </p>
	 * <p>
	 * Description: 返回是否可见
	 * </p>
	 * 
	 * @return
	 */
	public boolean isVisible() {
		return mainView.isVisible();
	}

	@Override
	public List<IView> getViews() {
		return new ArrayList<IView>(views.values());
	}

	@Override
	public List<IEditor> getEditors() {
		int size = this.editorContainer.getTabCount();
		if (size == 0) {
			return new ArrayList<IEditor>();
		} else {
			List<IEditor> editors = new ArrayList<IEditor>();

			for (int i = 0; i < size; i++) {
				editors.add(((EditorDockable) this.editorContainer.getComponentAt(i)).getEditor());
			}

			return editors;
		}
	}

	@Override
	public void setSaveLayouts(boolean b) {
		this.saveLayouts = b;
	}

	@Override
	public boolean isSaveLayouts() {
		return saveLayouts;
	}

	@Override
	public void clearLayoutsStore() {
		if (store != null && store.exists()) {
			store.delete();
		}
	}

	@Override
	public DockableFrame getFrame() {
		return frame;
	}

	@Override
	public IApplication getApplication() {
		return application;
	}

	@Override
	public void setFrozen(boolean frozen) {
		
	}

	@Override
	public boolean isFrozen() {
		return true;
	}

	@Override
	public void addWorkbenchListener(IWorkbenchListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeWorkbenchListener(IWorkbenchListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void registerKeyboardAction(ActionListener action, String keyText) {
		((JComponent) frame.getContentPane()).registerKeyboardAction(action, keyText, KeyUtils.explainKey(keyText), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	@Override
	public JTabbedPane getEditorContainer() {
		return editorContainer;
	}

	public JPanel getFrontPanel() {
		return frontPanel;
	}

	
	/**
	 * @return the backgroundPanel
	 */
	public JPanel getBackgroundPanel() {
		return backgroundPanel;
	}

	@Override
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
		
	}

	@Override
	public ITaskService getTaskService() {
		return this.taskService;
		
	}
}