package com.deppon.esb.management.svccfg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.svccfg.dao.ISvcPointDao;
import com.deppon.esb.management.svccfg.dao.ISvcPointRelationDao;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;
import com.deppon.esb.management.svccfg.service.ISvcpointService;


@Service
@Transactional
public class SvcpointService implements ISvcpointService {
	@Resource(name="svcPointDao")
	private ISvcPointDao svcPointDao;
	@Resource(name="svcPointRelationDao")
	private ISvcPointRelationDao svcPointRelationDao;
	
	public ISvcPointRelationDao getSvcPointRelationDao() {
		return svcPointRelationDao;
	}

	public void setSvcPointRelationDao(ISvcPointRelationDao svcPointRelationDao) {
		this.svcPointRelationDao = svcPointRelationDao;
	}

	public ISvcPointDao getSvcPointDao() {
		return svcPointDao;
	}

	public void setSvcPointDao(ISvcPointDao svcPointDao) {
		this.svcPointDao = svcPointDao;
	}

	@Override
	public void addSvcpoint(SvcPointInfo info) {
		svcPointDao.addSvcpointInfo(info);
	}

	@Override
	public void updateSvcpoint(SvcPointInfo info) {
		svcPointDao.updateSvcPointInfo(info);

	}

//	@Override
//	public List<SvcPointInfo> getSvcpoints() {
//		return svcPointDao.loadAllConfigs();
//	}

	@Override
	public SvcPointInfo getSvcpointBySvcCode(String svccode) {
		return svcPointDao.loadConfigBySvcCode(svccode);
	}

	/**
	 * 删除服务配置，并且根据服务配置删除前段后端服务关联
	 */
	@Override
	public void deleteSvcPointInfos(List<String> svcCodes) {
		svcPointDao.deleteSvcPointInfos(svcCodes);
		svcPointRelationDao.deleteRelationByFrontSvcCode(svcCodes);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SvcPointInfo> getSvcpointsByConditions(Map map) {
		return svcPointDao.loadConfigByConditions(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getTotalCount(Map map) {
		
		return svcPointDao.getSvcpointTotalCount(map);
	}

	@Override
	public void addSvcpoint(SvcPointInfo info,
			SvcPointRelationInfo svcPointRelationInfo) {
		svcPointDao.addSvcpointInfo(info);
	}

	@Override
	public List<SvcpointBean> getSvcpointBean(SvcpointQueryBean bean) {
		return svcPointDao.getSvcpointBean(bean);
	}

	@Override
	public boolean isSvcpointRelationExisit(SvcPointRelationInfo info) {
		return svcPointRelationDao.isExistSvcPointRelation(info);
	}

	@Override
	public void addSvcpointRelation(SvcPointRelationInfo info) {
		svcPointRelationDao.addRelation(info);
		
	}

	@Override
	public Integer deleteSvcpointRelation(List<String> ids) {
		return svcPointRelationDao.deleteRelation(ids);
	}

	@Override
	public List<SvcPointRelationInfo> getSvcpointRelation(String frontSvcCode) {
		return svcPointRelationDao.getSvcpointRelation(frontSvcCode);
	}

	@Override
	public Integer getSvcpointBeanCount(SvcpointQueryBean bean) {
		return svcPointDao.getSvcpointBeanCount(bean);
	}

	public SvcPointInfo loadConfigBySvcCode(String serviceCode) {
		return svcPointDao.loadConfigBySvcCode(serviceCode);
	}
}
