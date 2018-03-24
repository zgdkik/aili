package com.yimidida.ows.common.server.service;

import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.share.entity.DictValueEntity;

public interface IDictValueService extends IBaseService<DictValueEntity, String> {
	/**
	 * 分页查询数据字典信息
	 * @param queryPageVo
	 * @return Pagination<DictValueEntity>
	 * @author zb134373 16.3.28
	 */
	Pagination<DictValueEntity> getPage(QueryPageVo queryPageVo);
	/**
	 * 新增数据字典
	 * @param dictValueEntity
	 * @return void
	 * @author zb134373 16.3.28
	 * @return 
	 */
	void addDictValue(DictValueEntity dictValueEntity);
	
	/**
	 * 修改数据字典
	 * @param updateDictValue
	 * @return void
	 * @author zb134373 16.3.28
	 * @return 
	 */
	void updateDictValue(DictValueEntity dictValueEntity);

	/**
	 * 根据数据字典类型、关键词查询对应的数据字典
	 * @author IT-000036-zhangxingwang
	 * @date 2016-5-6 15:33:29
	 */
	DictValueEntity getDictValueByKeyCache(String key, String cacheType);
}
