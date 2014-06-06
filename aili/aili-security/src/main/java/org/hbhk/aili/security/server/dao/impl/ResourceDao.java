package org.hbhk.aili.security.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.core.server.data.AiliDaoSupport;
import org.hbhk.aili.security.server.dao.IResourceDao;
import org.hbhk.aili.security.share.pojo.ResourceInfo;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDao extends AiliDaoSupport implements IResourceDao {
	private final String NAMESPACE = "aili.res.";

	@Override
	public ResourceInfo getResByCode(String code) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		List<ResourceInfo> resInfos = getSqlSession().selectList(
				NAMESPACE + "getResByCode", parameter);
		if (CollectionUtils.isEmpty(resInfos)) {
			return null;
		}
		return resInfos.get(0); 
	}

	@Override
	public List<ResourceInfo> getResByPaCode(String pcode) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("pcode", pcode);
	return getSqlSession().selectList(
				NAMESPACE + "getResByPaCode", parameter);
	}

}
