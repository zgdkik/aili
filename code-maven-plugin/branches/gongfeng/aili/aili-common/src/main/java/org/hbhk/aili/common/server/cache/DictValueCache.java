package org.hbhk.aili.common.server.cache;

import java.util.Collections;
import java.util.List;

import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.common.server.dao.IDictValueDao;
import org.hbhk.aili.common.share.entity.DictValueEntity;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DictValueCache extends CacheSupport<List<DictValueEntity>> {
	
	@Autowired
	private IDictValueDao dictValueDao;

	@Override
	public String getCacheId() {
		return FrontendConstants.DICT_VALUE_CACHE_UUID;
	}

	@Override
	public List<DictValueEntity> doSet(String key) {
		DictValueEntity dictValue = new DictValueEntity();
		dictValue.setDictCode(key);
		List<DictValueEntity> list =dictValueDao.get(BeanToMapUtil.convert(dictValue));
		Collections.sort(list);
		return list;
	}

}
