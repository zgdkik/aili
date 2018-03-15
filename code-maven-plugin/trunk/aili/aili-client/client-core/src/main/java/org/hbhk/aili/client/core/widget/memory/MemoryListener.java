package org.hbhk.aili.client.core.widget.memory;
/**
* <b style="font-family:微软雅黑"><small>Description:内存监听器，当内存改变时触发内存事件</small></b>   </br>
 */
public interface MemoryListener {
	/**
	 * 
	 * @Title:onMemoryChanged
	 * @Description:TODO
	 * @param @param event
	 * @return void
	 * @throws
	 */
	void onMemoryChanged(MemoryEvent event);

}
