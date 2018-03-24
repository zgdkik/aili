package com.yimidida.ows.common.server.dao;

import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.common.share.entity.DictValueEntity;

public interface IDictValueDao extends IBaseDao<DictValueEntity, String> {
	/**
	 * 查询数据字典分页
	 * @author zb134373 16.3.28
	 * @param page
	 * @param map
	 * @return
	 */
	Pagination<DictValueEntity> queryDictValueInfoByPage(Page page,
			Map<String, Object> map);
}
