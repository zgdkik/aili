package org.hbhk.aili.common.server.dao;

import java.util.Map;

import org.hbhk.aili.common.share.entity.DictValueEntity;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;

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
