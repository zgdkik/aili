package com.feisuo.sds.common.server.cache;

import java.util.List;

import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.common.server.dao.ISystemParameterDao;
import com.feisuo.sds.common.share.entity.SystemParameterEntity;

@Component
public class SystemParameterCache extends CacheSupport<String> {
	
	@Autowired
	private ISystemParameterDao systemParameterDao;

	@Override
	public String getCacheId() {
		return FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID;
	}

	@Override
	public String doSet(String key) {
		SystemParameterEntity sp = new SystemParameterEntity();
		sp.setSysKey(key);
		List<SystemParameterEntity> list = systemParameterDao.get(BeanToMapUtil.convert(sp));
		if(list != null && list.size()>0){
			return list.get(0).getSysValue();
		}
		return null;
	}

}
