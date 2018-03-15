package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.ISvcPointRelationDao;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
@Repository
public class SvcPointRelationDao extends IBatis3DaoImpl implements
		ISvcPointRelationDao {

	@Override
	public void addRelation(SvcPointRelationInfo relation) {
		this.getSqlSession().insert("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.saveSvcpointRelation", relation);
	}

	@Override
	public void updateRelation(SvcPointRelationInfo relation) {
		this.getSqlSession().update("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.updateRelation", relation);
	}

	@Override
	public Integer deleteRelation(List<String> ids) {
		return this.getSqlSession().delete("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.deleteRelationById", ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SvcPointRelationInfo> getSvcpointRelation(String frontSvcCode) {	
		return this.getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.getSvcpointRelation", frontSvcCode);
	}

	@Override
	public Boolean isExistSvcPointRelation(
			SvcPointRelationInfo info) {
		Object obj = this.getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.isExistSvcpointRelation",info);
		return obj != null ? true : false;
	}

	@Override
	public Integer deleteRelationByFrontSvcCode(List<String> frontSvcCodes) {
		return this.getSqlSession().delete("com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo.deleteRelationByFrontSvcCode", frontSvcCodes);
	}

}
