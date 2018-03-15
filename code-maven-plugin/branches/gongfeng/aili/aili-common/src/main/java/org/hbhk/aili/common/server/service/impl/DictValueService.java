package org.hbhk.aili.common.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.common.server.dao.IDictValueDao;
import org.hbhk.aili.common.server.service.IDictValueService;
import org.hbhk.aili.common.share.entity.DictValueEntity;
import org.hbhk.aili.common.share.util.Constants;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class DictValueService implements IDictValueService {
	
	@Autowired
	private IDictValueDao dictValueDao;
	/**
	 * 分页查询数据字典信息
	 * @param queryPageVo
	 * @return Pagination<DictValueEntity>
	 * @author zb134373 16.3.28
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination<DictValueEntity> getPage(QueryPageVo queryPageVo) {
		Map<String, Object> map = queryPageVo.getParaMap();
		map.put("status", Constants.Y);
		Pagination<DictValueEntity> pageInfo  = dictValueDao.getPagination(map,queryPageVo.getPage());
		return pageInfo;
	}
	/**
	 * 新增数据字典
	 * @param dictValueEntity
	 * @return void
	 * @author zb134373 16.3.28
	 * @return 
	 */
	@Override
	@Transactional
	public void addDictValue(DictValueEntity dictValueEntity) {
		dictValueEntity.setId(UuidUtil.getUuid());
		dictValueEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setCreateTime(new Date());
		dictValueEntity.setUpdateTime(new Date());
		dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("key", dictValueEntity.getKey());
		map.put("dictCode", dictValueEntity.getDictCode());
		map.put("status", Constants.Y);
		List<DictValueEntity> dictValueEntityList=dictValueDao.get(map);
		
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("orderNo", dictValueEntity.getOrderNo());
		map1.put("dictCode", dictValueEntity.getDictCode());
		map1.put("status", Constants.Y);
		List<DictValueEntity> dictValueEntityList1=dictValueDao.get(map1);
		if(dictValueEntityList.size()>0){
			throw new BusinessException("关键字已经存在!");
		}else if(dictValueEntityList1.size()>0){
			throw new BusinessException("序号已经存在!");
		}else{
			dictValueDao.insert(dictValueEntity);
		}
	}
	
	
	/**
	 * 修改数据字典
	 * @param updateDictValue
	 * @return void
	 * @author zb134373 16.3.28
	 * @return 
	 */
	@Override
	@Transactional
	public void updateDictValue(DictValueEntity dictValueEntity) {
		dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setUpdateTime(new Date());
		dictValueDao.update(dictValueEntity);
		invalidate(dictValueEntity.getDictCode());
	}
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
	
	/**
	 * 根据数据字典类型、关键词查询对应的数据字典
	 * @author IT-000036-zhangxingwang
	 * @date 2016-5-6 15:33:29
	 */
	@Override
	public DictValueEntity getDictValueByKeyCache(String key, String cacheType) {
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(cacheType)){
			return null;
		}
		ICache<String, List<DictValueEntity>>  cacheDictValue = CacheManager.getInstance().getCache(cacheType);
		if(cacheDictValue  == null){
			return null;
		}
		List<DictValueEntity> dictList = cacheDictValue.get(key);
		if(CollectionUtils.isEmpty(dictList)){
			return null;
		}
		DictValueEntity dict = null;
		for(DictValueEntity dictTemp : dictList){
			if(dictTemp != null && key.equals(dictTemp.getKey())){
				dict = dictTemp;
				break;
			}
		}
		return dict;
	}
}
