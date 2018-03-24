package com.yimidida.ows.common.server.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.common.server.dao.ISystemParameterDao;
import com.yimidida.ows.common.share.entity.SystemParameterEntity;
import com.yimidida.ymdp.cache.server.CacheSupport;
import com.yimidida.ymdp.core.share.util.BeanToMapUtil;

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
