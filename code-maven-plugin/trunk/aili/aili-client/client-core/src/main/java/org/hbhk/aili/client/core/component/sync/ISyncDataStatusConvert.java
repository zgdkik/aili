package org.hbhk.aili.client.core.component.sync;
/**
* <b style="font-family:微软雅黑"><small>Description:同步数据状态转换器接口</small></b>   </br>
 */
public interface ISyncDataStatusConvert {
	
	/**
	 * 由业务去进行消息的格式化处理，框架只提供结构
	 * 
	 * @param index
	 *            从第几条开始
	 * @param count
	 *            总共有多少条
	 * @param target
	 *            目标对象
	 * @return
	 */
	//String converMessage(int index, int count, Class<?> target);
	
	String converMessage(Class<?> target);
	 
	
}
