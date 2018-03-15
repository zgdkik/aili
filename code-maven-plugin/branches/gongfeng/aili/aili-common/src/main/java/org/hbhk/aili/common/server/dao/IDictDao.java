package org.hbhk.aili.common.server.dao;


import java.util.Map;

import org.hbhk.aili.common.share.entity.DictEntity;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;

public interface IDictDao extends IBaseDao<DictEntity, String> {

 	
 	
 	/**
 	 * 删除其自己以及自己的所有子节点对应的数据字典
 	 * @param map
 	 * @author zb134373 16.3.25 15:53
 	 */
 	public void deleteValueKey(Map<String,Object> map);
 	
}
