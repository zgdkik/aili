package org.hbhk.aili.client.core.core.application;

import java.util.Map;

import javax.swing.JComponent;

import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.core.core.workbench.IWorkbench;

/**
 *应用接口，描述了应用一些行为及属性。
 */
public interface IApplication {
	/**
	 * 应用程序目录
	 */
	public static final String APP_HOME = "app_home";
	
	/**
	 * 插件目录
	 */
	public static final String APP_PLUGIN_HOME = "plugin_home";
	
	/**
	 * 启动目录
	 */
	public static final String APP_LAUNCHER_HOME = "app_launcher_home";
	
	/**
	 * 存储某个值到应用对象中具有一个共享的功能
	 * setAttribute
	 * @param key
	 * @param value
	 * @return void
	 * @since:0.6
	 */
	void setAttribute(Object key, Object value);
	
	/**
	 * 从应用对象中获取指定key的变量
	 * getAttribute
	 * @param <T>
	 * @param key
	 * @return
	 * @return T
	 * @since:0.6
	 */
	<T> T getAttribute(Object key);
	
	/**
	 * 获取应用对象中存储的所有属性值，以Map对象返回
	 * getAttributes
	 * @return
	 * @return Map<Object,Object>
	 * @since:0.6
	 */
	Map<Object, Object> getAttributes();
	
	/**
	 * 获取应用主目录的字符串表示
	 * getHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getHome();
	
	/**
	 * 重启应用
	 * restart
	 * @return void
	 * @since:0.6
	 */
	void restart();
	
	/**
	 * 退出应用
	 * exit
	 * @return void
	 * @since:0.6
	 */
	void exit();
	
	/**
	 * 判断当前应用启动之前是否允许有自动运行的程序存在
	 * isAutoRunsEnabled
	 * @return
	 * @return boolean 布尔值，true:是，false:否
	 * @since:0.6
	 */
	boolean isAutoRunsEnabled();
	
	/**
	 * 设置应用启动之前是否有自动运行功能
	 * setEnableAutoRuns
	 * @param enableAutoRuns 布尔值，true:是，false:否
	 * @return void
	 * @since:0.6
	 */
	void setEnableAutoRuns(boolean enableAutoRuns);
	
	
	/**
	 * 
	 * @param workbench
	 */
	void setWorkbench(IWorkbench workbench);
	
	/**
	 * 获取应用所代表的IWorkbench对象及工作空间
	 * getWorkbench
	 * @return
	 * @return IWorkbench 工作区接口
	 * @since:0.6
	 */
	IWorkbench getWorkbench();
	
	/**
	 * 新创建或者激活编辑区中某个IEditor对象
	 * openEditor
	 * @param config 编辑器配置
	 * @param editorClass 编辑器类全路径
	 * @return void
	 * @since:0.6
	 */
	void openEditor(EditorConfig config, String editorClass);
	
	
	/**
	 * 新创建或者激活编辑区中某个IEditor对象,返回IEditor对象
	 * openEditor
	 * @param config 编辑器配置
	 * @param editorClass 编辑器类全路径
	 * @return void
	 * @since:0.6
	 */
	IEditor openEditorAndRetrun(EditorConfig config, String editorClass);
	
	
	
	/**
	 * 向应用对象中添加监听器
	 * addApplicationListener
	 * @param listener 应用程序监听器
	 * @return void
	 * @since:0.6
	 */
	void addApplicationListener(IApplicationListener listener);
	
	/**
	 * 删除应用对象中指定的监听器
	 * removeApplicationListener
	 * @param listener 应用程序监听器
	 * @return void
	 * @since:0.6
	 */
	void removeApplicationListener(IApplicationListener listener);

	IEditor openUI(EditorConfig editConfig, JComponent comp);
}
