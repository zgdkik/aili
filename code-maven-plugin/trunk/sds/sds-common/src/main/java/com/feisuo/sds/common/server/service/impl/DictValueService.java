package com.feisuo.sds.common.server.service.impl;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.common.server.dao.IDictValueDao;
import com.feisuo.sds.common.server.service.IDictValueService;
import com.feisuo.sds.common.share.entity.DictValueEntity;

@Service
public class DictValueService implements IDictValueService {
	
	@Autowired
	private IDictValueDao dictValueDao;
	
	@Override
	public int insert(DictValueEntity t) {
		invalidate(t.getDictCode());
		return dictValueDao.insert(t);
	}

	@Override
	public int update(DictValueEntity t) {
		invalidate(t.getDictCode());
		return dictValueDao.update(t);
	}

	@Override
	public DictValueEntity getById(String id) {
		return dictValueDao.getById(id);
	}

	@Override
	public List<DictValueEntity> get(Map<String, Object> params) {
		return dictValueDao.get(params);
	}

	@Override
	public List<DictValueEntity> getPage(Map<String, Object> params,
			int pageNum, int pageSize) {
		return dictValueDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return dictValueDao.getPageTotalCount(params);
	}

	@Override
	public int deleteById(String id) {
		return dictValueDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		invalidate(getById(id).getDictCode());
		return dictValueDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<DictValueEntity> getPagination(
			Map<String, Object> params, Page page, Sort... sorts) {
		return dictValueDao.getPagination(params, page, sorts);
	}

	private void invalidate(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.DICT_VALUE_CACHE_UUID);
		cache.invalid(key);
	}
}
