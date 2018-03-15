package org.hbhk.aili.client.core.core.workbench;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JTabbedPane;

import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.core.application.IApplication;

/**
 *	此接口表示的是一个工作空间，在此工作空间中分为两种不同的区域。
 *一部分事视图域，另一部分是编辑域。视图区域被分为三部分，分别为navigator(导航区位于工作空间的左侧)。
 *编辑区域用来显示IEditor对象，位于工作空间中的中心区域
 *output(输出区位于工作空间区域的下方)|properties(属性显示区域，位于工作空间右方)
 */
public interface IWorkbench extends IWindowFactory {
	
	/**
	 * 获取编辑区容器
	 * @return
	 */
	JTabbedPane getEditorContainer();
	/**
	 * 获取工作空间中所有的视图
	 * getViews
	 * @return
	 * @return List<IView>
	 * @since:0.6
	 */
	List<IView> getViews();
	
	/**
	 * 通过视图ID获取工作空间中的视图
	 * getView
	 * @param viewId 视图ID
	 * @return
	 * @return IView
	 * @since:0.6
	 */
	IView getView(String viewId);
	
	/**
	 * 获取当前被激活的IEditor对象
	 * getActivatedEditor
	 * @return
	 * @return IEditor
	 * @since:0.6
	 */
	IEditor getActivatedEditor();
	
	/**
	 * 获取工作空间中所有的IEditor对象
	 * getEditors
	 * @return
	 * @return List<IEditor>
	 * @since:0.6
	 */
	List<IEditor> getEditors();
	
	/**
	 * 设置是否保存当前的工作空间布局样式
	 * setSaveLayouts
	 * @param b 布尔值，true:保存，false:不保存
	 * @return void
	 * @since:0.6
	 */
	void setSaveLayouts(boolean b);
	
	/**
	 * 判断工作空间退出时是否需要记录
	 * isSaveLayouts
	 * @return
	 * @return boolean 布尔值，true:保存，false:不保存
	 * @since:0.6
	 */
	boolean isSaveLayouts();
	
	/**
	 * 恢复工作空间的布局，设置为默认的布局
	 * restoreLayouts
	 * @return void
	 * @since:0.6
	 */
	void restoreLayouts();
	/**
	 * 清除已经保存的工作空间布局
	 * clearLayoutsStore
	 * @return void
	 * @since:0.6
	 */
	void clearLayoutsStore();
	
	/**
	 * 设置工作空间布局
	 * setLayoutsStore
	 * @param store 保存文件
	 * @return void
	 * @since:0.6
	 */
	void setLayoutsStore(File store);
	
	/**
	 * 获取工作空间中文件中保存的布局信息
	 * getLayoutsStore
	 * @return
	 * @return File
	 * @since:0.6
	 */
	File getLayoutsStore();
	
	/**
	 * 获取应用对象
	 * getApplication
	 * @return
	 * @return IApplication 应用程序接口
	 * @since:0.6
	 */
	IApplication getApplication();
	
	/**
	 * 
	 * <p>Title: getFrame</p>
	 * <p>Description: 获得框架对象</p>
	 * @return
	 */
	Frame getFrame();
	
	/**
	 * 设置工作空间中的view或者editor是否可以被移动
	 * setFrozen
	 * @param frozen 布尔值，true:是；false:否
	 * @return void
	 * @since:0.6
	 */
	void setFrozen(boolean frozen);
	
	/**
	 * 判断工作空间中的view或者editor是否可以移动
	 * isFrozen
	 * @return
	 * @return boolean 布尔值，true:是；false:否
	 * @since:0.6
	 */
	boolean isFrozen();
	
	/**
	 * 
	 * <p>Title: addWorkbenchListener</p>
	 * <p>Description: 添加工作区监听器</p>
	 * @param listener 工作区监听器
	 */
	void addWorkbenchListener(IWorkbenchListener listener);
	
	/**
	 * 
	 * <p>Title: removeWorkbenchListener</p>
	 * <p>Description: 移除工作区监听器</p>
	 * @param listener 工作区监听器
	 */
	void removeWorkbenchListener(IWorkbenchListener listener);
	void registerKeyboardAction( ActionListener action, String keyText);
	
	/**
	 * 设置后台任务控制器
	 * @param taskService
	 * @describe zxy 20140504 MANA-2019 新增 
	 */
	void setTaskService(ITaskService taskService);
	
	/**
	 * 获取后台任务控制器
	 * @describe zxy 20140504 MANA-2018 新增 
	 */
	ITaskService getTaskService();
}