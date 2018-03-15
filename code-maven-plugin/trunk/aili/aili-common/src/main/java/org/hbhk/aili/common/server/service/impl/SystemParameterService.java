package org.hbhk.aili.common.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.common.server.dao.ISystemParameterDao;
import org.hbhk.aili.common.server.service.ISystemParameterService;
import org.hbhk.aili.common.share.entity.SystemParameterEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * ClassName: SystemParameterService Description: TODO Author: fanhoutao Date:
 * 2015年12月5日
 */
@Service
@Transactional
public class SystemParameterService implements ISystemParameterService {
	@Autowired
	private ISystemParameterDao systemParameterDao;

	/**
	 * 新增
	 */
	@Override
	public int insert(SystemParameterEntity systemParameterEntity) {
		String id = systemParameterEntity.getId();
		int num = 0;
		if (StringUtils.isNotEmpty(id)) {
			systemParameterEntity.setUpdateTime(new Date());
			systemParameterEntity.setUpdateUser(UserContext.getCurrentUser()
					.getUserName());
			num = systemParameterDao.update(systemParameterEntity);

		} else {
			systemParameterEntity.setCreateTime(new Date());
			systemParameterEntity.setCreateUser(UserContext.getCurrentUser()
					.getUserName());
			systemParameterEntity.setId(UuidUtil.getUuid());
			num = systemParameterDao.insert(systemParameterEntity);
		}
		if (num > 0) {
			invalidate(systemParameterEntity.getSysKey());
		}
		return num;
	}

	/**
	 * 修改
	 */
	@Override
	public int update(SystemParameterEntity t) {
		int num = systemParameterDao.update(t);
		if (num > 0) {
			invalidate(t.getSysKey());
		}
		return num;
	}

	/**
	 * 通过id查询
	 */
	@Override
	@Transactional(readOnly = true)
	public SystemParameterEntity getById(String id) {

		return systemParameterDao.getById(id);
	}

	@Override
	public List<SystemParameterEntity> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemParameterEntity> getPage(Map<String, Object> params,
			int pageNum, int pageSize) {

		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {

		return 0;
	}

	/**
	 * 通过id删除
	 */
	@Override
	public int deleteById(String id) {
		if (id == null || id.isEmpty()) {
			throw new BusinessException("删除的ID为空");
		}
		invalidate(getById(id).getSysKey());
		int num = systemParameterDao.deleteById(id);
		return num;
	}

	/**
	 * 通过id修改状态
	 */
	@Override
	public int updateStatusById(String id, int status) {
		int num = systemParameterDao.updateStatusById(id, status);
		if (num > 0) {
			invalidate(getById(id).getSysKey());
		}
		return num;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Pagination<SystemParameterEntity> getPagination(
			Map<String, Object> params, Page page, Sort... sorts) {

		return systemParameterDao.getPagination(params, page, sorts);
	}

	/**
	 * 键的唯一性校验
	 */
	@Override
	public boolean checkSysKey(String sysKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysKey", sysKey);
		List<SystemParameterEntity> list = systemParameterDao.get(map);
		return list.size() < 1;
	}

	private void invalidate(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID);
		cache.invalid(key);
	}

}