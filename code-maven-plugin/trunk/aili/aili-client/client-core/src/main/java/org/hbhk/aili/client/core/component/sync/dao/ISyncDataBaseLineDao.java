package org.hbhk.aili.client.core.component.sync.dao;

import java.util.List;

import org.hbhk.aili.client.core.component.sync.domain.SyncDataBaseLine;
/**
* <b style="font-family:微软雅黑"><small>Description:同步数据基线的映射器</small></b>   </br>
 */
public interface ISyncDataBaseLineDao {

	/**
	 * 
	 * @return
	 */
	List<SyncDataBaseLine> findBaseLines();

	/**
	 * 
	 * @param request
	 */
	void saveBaseLine(SyncDataBaseLine baseLine);
	
	/**
	 * @param      
	 * @return      
	 * @exception
	 */
	void updateBaseLine(SyncDataBaseLine baseLine);
	
	/**
	 * @param      
	 * @return      
	 * @exception
	 */
	void updateBaseLineByOrg(SyncDataBaseLine baseLine);
	
	/**
	 * 
	 * 
	 * @param      
	 * @return      
	 * @exception
	 */
	SyncDataBaseLine findSyncDataBaseLine(String cls);

	/**
	 * @param cls
	 * @param orgCode
	 * @return
	 */
	SyncDataBaseLine findSyncDataBaseLineAndOrgCode(String cls, String orgCode);
}
