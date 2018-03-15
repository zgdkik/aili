package org.hbhk.aili.client.boot.autorun;


/**
 * 自动运行任务对象
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:dp-yangtong,date:2012-10-12 上午9:29:42, </p>
 * @author dp-yangtong
 * @date 2012-10-12 上午9:29:42
 * @since
 * @version
 */
public class AutoRun {
    	/**
    	 * 插件ID
    	 */
	public String pluginId;
	/**
    	 * 自动运行任务接口
    	 */
	public IAutoRunner autoRunner;
	/**
    	 * 
    	 */
	public boolean haltOnError;
	/**
    	 * 自动运行任务阶段对象
    	 */
	public AutoRunPhase phase;
	/**
    	 * 排序
    	 */
	public int order;
	/**
    	 * 是否可注销lable
    	 */
	public boolean cancelable;
	/**
    	 * 是否可以后台运行
    	 */
	public boolean canRunBackground;
	/**
	 * 是否隐藏
	 */
	public boolean hiddenDialog;
}