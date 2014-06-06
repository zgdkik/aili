package org.hbhk.aili.security.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.security.server.dao.IResourceDao;
import org.hbhk.aili.security.server.service.IResourceService;
import org.hbhk.aili.security.share.pojo.ResourceInfo;
import org.springframework.stereotype.Service;

@Service
public class ResourceService implements IResourceService {

	@Resource
	private  IResourceDao  resourceDao;
	
	@Override
	public ResourceInfo getResByCode(String code) {
		if(StringUtils.isEmpty(code)){
			return null;
		}
		return resourceDao.getResByCode(code);
	}

	@Override
	public List<ResourceInfo> getResByPaCode(String pcode) {
		if(StringUtils.isEmpty(pcode)){
			return null;
		}
		return resourceDao.getResByPaCode(pcode);
	}

}
