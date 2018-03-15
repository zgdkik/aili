package org.hbhk.aili.client.core.component.sync.service;

import java.util.List;

import org.hbhk.aili.client.core.component.sync.domain.SyncDataBaseLine;

/**
 * <b style="font-family:微软雅黑"><small>Description:ISyncDataBaseLineService
 * 同步基线服务接口</small></b> </br> <b
 */
public interface ISyncDataBaseLineService {

	/**
	 * @Title:getAll
	 * @Description:获得所有的同步数据基线
	 * @param @return
	 * @return List<SyncDataBaseLine>
	 * @throws
	 */
	List<SyncDataBaseLine> getAll();

	/**
	 * 
	 * @Title:saveBaseLine
	 * @Description:保存数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */
	void saveBaseLine(SyncDataBaseLine baseLine);

	/**
	 * 
	 * @Title:updateBaseLine
	 * @Description:更新数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */
	void updateBaseLine(SyncDataBaseLine baseLine);

	
	/**
	 * 
	 * @Title:updateBaseLine
	 * @Description:更新数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */
	void updateBaseLineByOrg(SyncDataBaseLine baseLine);
	/**
	 * 
	 * @Title:getSyncDataBaseLineByClass
	 * @Description:根据类名获得同步数据基线
	 * @param @param cls 类名
	 * @param @return
	 * @return SyncDataBaseLine
	 * @throws
	 */
	SyncDataBaseLine getSyncDataBaseLineByClass(String cls);

	/**
	 * @param entityClsName
	 * @param userOrgCode
	 * @return
	 */
	SyncDataBaseLine getSyncDataBaseLineByClassAndOrgCode(
			String entityClsName, String userOrgCode);


}
