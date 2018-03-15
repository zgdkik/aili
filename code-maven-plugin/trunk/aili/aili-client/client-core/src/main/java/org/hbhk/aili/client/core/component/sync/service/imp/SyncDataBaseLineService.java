package org.hbhk.aili.client.core.component.sync.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.component.sync.dao.ISyncDataBaseLineDao;
import org.hbhk.aili.client.core.component.sync.domain.SyncDataBaseLine;
import org.hbhk.aili.client.core.component.sync.service.ISyncDataBaseLineService;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;

/**
 * <b style="font-family:微软雅黑"><small>Description:同步数据基线的数据访问接口</small></b>
 */
public class SyncDataBaseLineService implements ISyncDataBaseLineService {

	//log
	private static final Log LOG = LogFactory
		.getLog(SyncDataBaseLineService.class);
	
	@Inject
	private ISyncDataBaseLineDao syncDataBaseLineDao;

	/**
	 * @Title:getAll
	 * @Description:获得所有的同步数据基线
	 * @param @return
	 * @return List<SyncDataBaseLine>
	 * @throws
	 */
	@Transactional
	public List<SyncDataBaseLine> getAll() {
		List<SyncDataBaseLine> lists = null;
		try {
			lists = syncDataBaseLineDao.findBaseLines();
		} catch (Exception t) {
			LOG.error("find base line exception", t);
			lists = new ArrayList<SyncDataBaseLine>();
		}
		return lists;
	}

	/**
	 * 
	 * @Title:saveBaseLine
	 * @Description:保存数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */
	@Transactional
	public void saveBaseLine(SyncDataBaseLine baseLine) {
		syncDataBaseLineDao.saveBaseLine(baseLine);
	}

	/**
	 * 
	 * @Title:updateBaseLine
	 * @Description:更新数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */
	@Transactional
	public void updateBaseLine(SyncDataBaseLine baseLine) {
		syncDataBaseLineDao.updateBaseLine(baseLine);
	}
	/**
	 * 
	 * @Title:updateBaseLine
	 * @Description:更新数据基线
	 * @param @param baseLine 同步数据基线
	 * @return void
	 * @throws
	 */@Transactional
	public void updateBaseLineByOrg(SyncDataBaseLine baseLine) {
		syncDataBaseLineDao.updateBaseLineByOrg(baseLine);
	}
	
	/**
	 * 
	 * @Title:getSyncDataBaseLineByClass
	 * @Description:根据类名获得同步数据基线
	 * @param @param cls 类名
	 * @param @return
	 * @return SyncDataBaseLine
	 * @throws
	 */
	@Transactional
	public SyncDataBaseLine getSyncDataBaseLineByClass(String cls) {
		SyncDataBaseLine syncDataBaseLine = null;
		try {
			syncDataBaseLine = syncDataBaseLineDao.findSyncDataBaseLine(cls);
		} catch (Exception e) {
			LOG.error(" findSyncDataBaseLine exception", e);
		}
		return syncDataBaseLine;
	}
	
	/**
	 * 
	 * @Title:getSyncDataBaseLineByClass and org code
	 * @Description:根据类名获得同步数据基线
	 * @param @param cls 类名
	 * @param @return
	 * @return SyncDataBaseLine
	 * @throws
	 */
	@Transactional
	public SyncDataBaseLine getSyncDataBaseLineByClassAndOrgCode(String cls, String orgCode) {
		SyncDataBaseLine syncDataBaseLine = null;
		try {
			syncDataBaseLine = syncDataBaseLineDao.findSyncDataBaseLineAndOrgCode(cls,orgCode);
		} catch (Exception e) {
			LOG.error(" findSyncDataBaseLineAndOrgCode exception", e);
		}
		return syncDataBaseLine;
	}
}
