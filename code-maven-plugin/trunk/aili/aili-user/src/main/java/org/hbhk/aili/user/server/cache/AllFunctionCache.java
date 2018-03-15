package org.hbhk.aili.user.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.user.server.dao.IFunctionDao;
import org.hbhk.aili.user.share.entity.PrivilegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllFunctionCache extends CacheSupport<Map<String, PrivilegeEntity>> {
	
	@Autowired
	private IFunctionDao functionDao;

	@Override
	public String getCacheId() {
		return FrontendConstants.FUNCTION_ALL_ENTITY_CACHE_UUID;
	}

	@Override
	public Map<String, PrivilegeEntity> doSet(String key) {
		PrivilegeEntity function = new PrivilegeEntity();
		function.setStatus(1);
		Map<String, Object>  params = BeanToMapUtil.convert(function);
		Map<String, PrivilegeEntity> urlFunMap = new HashMap<String, PrivilegeEntity>();
		List<PrivilegeEntity> list =  functionDao.get(params);
		if(list != null){
			for (PrivilegeEntity f : list) {
				urlFunMap.put(f.getUrl(), f);
			}
		}
		return urlFunMap;
	}

}
