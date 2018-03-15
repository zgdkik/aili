package org.hbhk.aili.client.core.component.sync;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hbhk.aili.client.core.commons.task.ITaskContext;
/**
* <b style="font-family:微软雅黑"><small>Description:同步数据处理器，抽象类</small></b>   </br>
 */
public abstract class AbstractSyncDataProcessor implements ISyncDataProcessor {
	
	/**
	 * 最大下载页数
	 */
	private int maxPageSize = 800; 
	
	// 同步数据远程服务接口
	private ISyncDataRemoting remoteService;
	private Map<Class<?>, ISyncDataSaver> dataSavers = new HashMap<Class<?>, ISyncDataSaver>();
	private Set<ISyncDataStatusListener> listeners = new HashSet<ISyncDataStatusListener>();
	private ISyncDataStatusConvert statusConvert;
	private SyncDataExecutor executor;
	private List<SyncDataRequest> requests;
	private ITaskContext context;
	@Override
	public void init() {
		this.requests = parseSyncDataConfigs();
	}

	/**
	 * 由具体实现类去获取当前需要同步哪些东西
	 * 
	 * @return
	 */
	abstract protected List<SyncDataRequest> parseSyncDataConfigs();
	
	/**
	 *  下载了首次的数据以后下次下载新的数据
	 * @param after
	 * @return
	 */
	abstract protected List<SyncDataRequest> parseSyncDataConfigsAfter(boolean after);

	@Override
	final public void registeDataSaver(ISyncDataSaver saver) {
		this.dataSavers.put(saver.getSaveType(), saver);

	}
	/**
	 * 
	 * <p>Title:registSyncListener
	 * <p>Description:注册同步监听器</p>
	 * @param listener
	 * @see org.hbhk.aili.client.core.component.sync.ISyncDataProcessor#registSyncListener(org.hbhk.aili.client.core.component.sync.ISyncDataStatusListener)
	 */
	@Override
	final public void registSyncListener(ISyncDataStatusListener listener) {
		this.listeners.add(listener);
	}
	/**
	 * 
	 * <p>Title:process
	 * <p>Description:执行数据同步操作</p>
	 * @throws Exception 
	 * @see org.hbhk.aili.client.core.component.sync.ISyncDataProcessor#process()
	 */
	@Override
	public void process() throws Exception {
		executor = new SyncDataExecutor(this, requests);
		executor.setContext(context);
		executor.Execute();
	
	}

	@Override
	public void cancel() {
		executor.stop();
	}
	
	/**
	 * Processor 是否可活动的
	 * @param 
	 * @return
	 */
	abstract protected boolean isActive();

	/**
	 * 
	 * @param targetCls
	 * @return
	 */
	final protected ISyncDataSaver getDataSaver(Class<?> targetCls) {
		return this.dataSavers.get(targetCls);
	}

	@Override
	final public void setSyncStatusMessageConvert(ISyncDataStatusConvert convert) {
		this.statusConvert = convert;
	}

	/**
	 * 
	 * @return
	 */
	final protected ISyncDataStatusConvert getSyncDataStatusConvert() {
		return this.statusConvert;
	}

	/**
	 * 
	 * @param index
	 * @param count
	 * @param targetCls
	 */
/*	final protected void onStatusChange(int index, int count, Class<?> targetCls) {
		for (ISyncDataStatusListener listener : this.listeners) {
			listener.onStatusChange(statusConvert.converMessage(index, count,
					targetCls));
		}
	}*/
	
	final protected void onStatusChange(Class<?> targetCls) {
		for (ISyncDataStatusListener listener : this.listeners) {
			listener.onStatusChange(statusConvert.converMessage(targetCls));
		}
	}
	
	@Override
	final public void setSyncRemoteService(ISyncDataRemoting remoteService) {
		this.remoteService = remoteService;
	}

	/**
	 * 
	 * @return
	 */
	final protected ISyncDataRemoting getSyncRemoteService() {
		return this.remoteService;
	}

	/**
	 * @return the context
	 */
	public ITaskContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(ITaskContext context) {
		this.context = context;
	}

	public int getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(int maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
	
	
}