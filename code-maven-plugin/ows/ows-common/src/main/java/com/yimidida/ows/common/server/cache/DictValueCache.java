package com.yimidida.ows.common.server.cache;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.common.server.dao.IDictValueDao;
import com.yimidida.ows.common.share.entity.DictValueEntity;
import com.yimidida.ymdp.cache.server.CacheSupport;
import com.yimidida.ymdp.core.share.util.BeanToMapUtil;

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
