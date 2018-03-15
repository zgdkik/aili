package com.deppon.esb.management.svccfg.service;

import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;

public interface ISvcpointRelationService {
	public boolean isExistSvcpointRelation(SvcPointRelationInfo info);
	public void addSvcPointRelation(SvcPointRelationInfo info);
}
