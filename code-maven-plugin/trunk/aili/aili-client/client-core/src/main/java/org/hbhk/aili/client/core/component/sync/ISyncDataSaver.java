package org.hbhk.aili.client.core.component.sync;

import java.util.Date;
import java.util.List;

/**
* <b style="font-family:微软雅黑"><small>Description:同步数据保存器接口</small></b>   </br>
 */
public interface ISyncDataSaver {
	/**
	 * 
	 * @Title:getSaveType
	 * @Description:获得同步数据类型
	 * @param @return
	 * @return Class<?>
	 * @throws
	 */
	Class<?> getSaveType();
	
	/**
	 * 
	 * @Title:updateBaseLine
	 * @Description:更新数据基线
	 * @param @param request 同步数据请求
	 * @return void
	 * @throws
	 */
	void updateBaseLine(SyncDataRequest request);
	
	/**
	 * 
	 * @Title:saveData
	 * @Description:保存数据
	 * @param @param data 数据列表
	 * @return void
	 * @throws
	 */
	Date saveData(List<?> data);
	
	/**
	 * 
	 * @Title:saveData
	 * @Description:获取区域ID
	 * @param @param data  
	 * @return void
	 * @throws
	 */
	String getRegionID();

	/**
	 * @param saveRequest
	 */
	void updateBaseLineByOrgCode(SyncDataRequest saveRequest);
}