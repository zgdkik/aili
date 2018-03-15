package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;

public interface ISvcPointRelationDao {
	public void addRelation(SvcPointRelationInfo relation);
	public void updateRelation(SvcPointRelationInfo relation);
	public Integer deleteRelation(List<String> ids);
	public List<SvcPointRelationInfo> getSvcpointRelation(String frontSvcCode);
	public Boolean isExistSvcPointRelation(SvcPointRelationInfo info);
	//根据前段服务编码删除关联关系
	public Integer deleteRelationByFrontSvcCode(List<String> frontSvcCodes);
}
