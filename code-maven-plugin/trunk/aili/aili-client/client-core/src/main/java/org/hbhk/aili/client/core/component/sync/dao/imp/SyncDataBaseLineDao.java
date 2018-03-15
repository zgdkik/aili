package org.hbhk.aili.client.core.component.sync.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hbhk.aili.client.core.component.sync.dao.ISyncDataBaseLineDao;
import org.hbhk.aili.client.core.component.sync.domain.SyncDataBaseLine;

/**
 * <b style="font-family:微软雅黑"><small>Description:数据同步基线DAo</small></b> </br> <b
 */
public class SyncDataBaseLineDao implements ISyncDataBaseLineDao {

	private SqlSession sqlSession;

	@Inject
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SyncDataBaseLine> findBaseLines() {
		return sqlSession.selectList("foss.sync.findBaseLines");
	}

	@Override
	public void saveBaseLine(SyncDataBaseLine baseLine) {
		sqlSession.insert("foss.sync.saveBaseLine", baseLine);
	}

	@Override
	public void updateBaseLine(SyncDataBaseLine baseLine) {
		if (baseLine.getRegionID() == null || "".equals(baseLine)) {
			sqlSession.update("foss.sync.updateBaseLine", baseLine);
		} else {
			sqlSession.update("foss.sync.updateBaseLineByOrgCode", baseLine);
		}
	}

	@Override
	public SyncDataBaseLine findSyncDataBaseLine(String cls) {
		// 2013-03-13 by xundq
		// 在DefaultSyncProcessor.parseDataSaver方法中，会最终调用此方法来判断某个实体类在
		// 下载任务表PS_SYNCDATABASELINE中是否存在，如果不存在则程序需要自动生成一条任务
		// 但是所使用的SQL语句会导致同一个实体类由与不同的用户登录产生多条数据
		// 这种情况下所以会导致如下异常：
		// Expected one result (or null) to be returned by selectOne(), but
		// found: x
		// DefaultSyncProcessor.parseDataSaver方法中不能够捕获到该异常导致插入重复的任务
		// return sqlSession.selectOne("foss.sync.findSyncDataBaseLine", cls);
		List<SyncDataBaseLine> syncDataBaseLines = sqlSession.selectList(
				"foss.sync.findSyncDataBaseLine", cls);
		return (syncDataBaseLines.size() > 0) ? syncDataBaseLines.get(0) : null;

	}

	public SyncDataBaseLine findSyncDataBaseLineAndOrgCode(String cls,
			String orgCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("className", cls);
		map.put("orgCode", orgCode);
		// 2013-03-13 by xundq
		// 参见findSyncDataBaseLine的修改
		List<SyncDataBaseLine> syncDataBaseLines = sqlSession.selectList(
				"foss.sync.findSyncDataBaseLineAndOrgCode", map);
		return (syncDataBaseLines.size() > 0) ? syncDataBaseLines.get(0) : null;
	}

	/**
	 * @param      
	 * @return      
	 * @exception
	 */
	public void updateBaseLineByOrg(SyncDataBaseLine baseLine) {
		sqlSession.update("foss.sync.updateBaseLineByOrgCode", baseLine);
	}

}
