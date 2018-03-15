package com.feisuo.sds.common.server.cache;

import java.util.Collections;
import java.util.List;

import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.common.server.dao.IDictValueDao;
import com.feisuo.sds.common.share.entity.DictValueEntity;

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
