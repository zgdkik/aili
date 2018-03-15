package com.deppon.esb.management.svccfg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.svccfg.dao.ISvcPointRelationDao;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.service.ISvcpointRelationService;

@Service
@Transactional
public class SvcpointRelationService implements ISvcpointRelationService{

	@Resource(name="svcPointRelationDao")
	private ISvcPointRelationDao svcPointRelationDao;
	
	public ISvcPointRelationDao getSvcPointRelationDao() {
		return svcPointRelationDao;
	}

	public void setSvcPointRelationDao(ISvcPointRelationDao svcPointRelationDao) {
		this.svcPointRelationDao = svcPointRelationDao;
	}

	@Override
	public boolean isExistSvcpointRelation(SvcPointRelationInfo info) {
		return svcPointRelationDao.isExistSvcPointRelation(info);
	}

	@Override
	public void addSvcPointRelation(SvcPointRelationInfo info) {
		svcPointRelationDao.addRelation(info);
	}

}
