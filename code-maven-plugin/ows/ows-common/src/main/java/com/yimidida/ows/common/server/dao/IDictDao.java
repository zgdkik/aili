package com.yimidida.ows.common.server.dao;


import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.common.share.entity.DictEntity;

public interface IDictDao extends IBaseDao<DictEntity, String> {

 	/**
 	 * 删除其所有子节点的
 	 * @param map
 	 * @author zb134373 16.3.25 15:53
 	 */
 	public void deleteChildParent(Map map);
 	
 	
 	/**
 	 * 删除其自己以及自己的所有子节点对应的数据字典
 	 * @param map
 	 * @author zb134373 16.3.25 15:53
 	 */
 	public void deleteValueKey(Map map);
 	
}
