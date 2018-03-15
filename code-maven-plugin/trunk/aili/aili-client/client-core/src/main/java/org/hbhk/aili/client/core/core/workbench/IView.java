package org.hbhk.aili.client.core.core.workbench;

import java.util.List;

/**
 *	Description:IView表示一个视图，一般用来菜单等的导航视图
 */
public interface IView extends IWindow {
	/**
	 * 在这个WorkBench中每个IView都必须有一个唯一的id
	 * getId
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getId();
	
	/**
	 * 设置此IView对象的配置信息
	 * setConfig
	 * @param config 视图设置对象
	 * @return void
	 * @since:0.6
	 */
	void setConfig(ViewConfig config);
	
	/**
	 * 获取IView对象的配置信息
	 * getConfig
	 * @return
	 * @return ViewConfig 视图设置对象
	 * @since:0.6
	 */
	ViewConfig getConfig();
	
	/**
	 * 给view对象添加动作在某一时刻触发
	 * addAction
	 * @param action 动作接口对象
	 * @return void
	 * @since:0.6
	 */
	void addAction(IAction action);
	
	/**
	 * 以集合的形式向IView对象注入动作Action
	 * setActions
	 * @param actions 动作接口列表
	 * @return void
	 * @since:0.6
	 */
	void setActions(List<IAction> actions);
	
	/**
	 * 获取IView的所有可执行动作
	 * getActions
	 * @return
	 * @return List<IAction> 动作接口列表
	 * @since:0.6
	 */
	List<IAction> getActions();
}