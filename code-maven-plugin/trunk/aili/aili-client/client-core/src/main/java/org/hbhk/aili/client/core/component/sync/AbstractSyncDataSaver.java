package org.hbhk.aili.client.core.component.sync;

import org.hbhk.aili.client.core.component.sync.domain.SyncDataBaseLine;
import org.hbhk.aili.client.core.component.sync.service.ISyncDataBaseLineService;
import org.hbhk.aili.client.core.component.sync.service.SyncDataBaseLineServiceFactory;

/**
 * Description:同步数据保存器，抽象类。提供把客户端同步过来的数据保存到本地数据库中</small></b> </br> <b
 */
public abstract class AbstractSyncDataSaver implements ISyncDataSaver {

	private ISyncDataBaseLineService syncDataBaseLineService;

	public AbstractSyncDataSaver() {

		syncDataBaseLineService = SyncDataBaseLineServiceFactory
				.getSyncDateBaseLineService();
	}

	/**
	 * 
	 * <p>
	 * Title:updateBaseLine
	 * <p>
	 * Description:更新数据基线
	 * </p>
	 * 
	 * @param request
	 * @see org.hbhk.aili.client.core.component.sync.ISyncDataSaver#updateBaseLine(com.deppon.foss.framework.entity.SyncDataRequest)
	 */
	@Override
	public void updateBaseLine(SyncDataRequest request) {
		
			SyncDataBaseLine baseLine = new SyncDataBaseLine();
			baseLine.setEntityClsName(request.getSyncKey().getName());
			baseLine.setSyncDate(request.getFromDate());
			baseLine.setOrgCode(request.getOrgCode());
			baseLine.setRegionID(request.getRegionID());
			//baseLine.setSyncPage(request.getFromPage());
			syncDataBaseLineService.updateBaseLine(baseLine);
		
		
		
	}
	
	

	@Override
	public void updateBaseLineByOrgCode(SyncDataRequest request) {
		SyncDataBaseLine baseLine = new SyncDataBaseLine();
		baseLine.setEntityClsName(request.getSyncKey().getName());
		baseLine.setSyncDate(request.getFromDate());
		baseLine.setOrgCode(request.getOrgCode());
		baseLine.setRegionID(request.getRegionID());
		//baseLine.setSyncPage(request.getFromPage());
		syncDataBaseLineService.updateBaseLineByOrg(baseLine);
	}

	public ISyncDataBaseLineService getSyncDataBaseLineService() {
		return syncDataBaseLineService;
	}

	public void setSyncDataBaseLineService(ISyncDataBaseLineService syncDataBaseLineService) {
		this.syncDataBaseLineService = syncDataBaseLineService;
	}
	
	
}
