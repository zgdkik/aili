package org.hbhk.aili.client.core.component.sync;

/**
* <b style="font-family:微软雅黑"><small>Description:同步数据处理器接口</small></b>   </br>
 */
public interface ISyncDataProcessor {
	/**
	 * 进行配置读取等工作
	 */
	void init();
	
	/**
	 * 
	 * <p>Title: registeDataSaver</p>
	 * <p>Description: 进行数据保存的具体实现类的注册</p>
	 * @param saver 同步数据保存器
	 */
	void registeDataSaver(ISyncDataSaver saver);
	
	/**
	 * 
	 * <p>Title: registSyncListener</p>
	 * <p>Description: 进行数据同步过程中的信息知会</p>
	 * @param listener 同步数据状态监听器
	 */
	void registSyncListener(ISyncDataStatusListener listener);
	
	/**
	 * 
	 * <p>Title: setSyncStatusMessageConvert</p>
	 * <p>Description: 设置同步状态消息转换器</p>
	 * @param convert 同步数据状态转换器
	 */
	void setSyncStatusMessageConvert(ISyncDataStatusConvert convert);
	
	/**
	 * 
	 * <p>Title: setSyncRemoteService</p>
	 * <p>Description: 设置远程同步服务接口</p>
	 * @param remoteService
	 */
	void setSyncRemoteService(ISyncDataRemoting remoteService);
	
	/**
	 * 
	 * <p>Title: process</p>
	 * <p>Description: 执行数据同步</p>
	 */
	void process() throws Exception;
	
	/**
	 * 
	 * <p>Title: cancel</p>
	 * <p>Description: 可以在同步的过程中取消操作</p>
	 */
	void cancel();
}