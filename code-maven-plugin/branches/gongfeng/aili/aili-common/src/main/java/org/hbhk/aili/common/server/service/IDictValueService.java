package org.hbhk.aili.common.server.service;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.common.share.entity.DictValueEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;

public interface IDictValueService extends IBaseService<DictValueEntity, String> {
	/**
	 * 分页查询数据字典信息
	 * @param queryPageVo
	 * @return Pagination<DictValueEntity>
	 */
	Pagination<DictValueEntity> getPage(QueryPageVo queryPageVo);
	/**
	 * 新增数据字典
	 * @param dictValueEntity
	 * @return void
	 * @return 
	 */
	void addDictValue(DictValueEntity dictValueEntity);
	
	/**
	 * 修改数据字典
	 * @param updateDictValue
	 * @return void
	 * @return 
	 */
	void updateDictValue(DictValueEntity dictValueEntity);

	/**
	 * 根据数据字典类型、关键词查询对应的数据字典
	 */
	DictValueEntity getDictValueByKeyCache(String key, String cacheType);
}
