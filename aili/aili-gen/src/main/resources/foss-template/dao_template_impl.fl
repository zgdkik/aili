package com.deppon.${project}.module.${module}.server.dao.impl;

import com.deppon.${project}.module.${module}.server.dao.I${table.typeName}Dao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.deppon.foss.module.frameworkimpl.shared.define.DpapConstants;
import com.deppon.${project}.module.${module}.shared.domain.${table.typeName}Entity;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ${table.name}
 * 
 * @author DPAP
 * 
 */
public class ${table.typeName}Dao extends iBatis3DaoImpl implements I${table.typeName}Dao {

	public static final String NAMESPACE = "com.deppon.${project}.module.${module}.server.dao.${table.typeName}Dao.";
	
	/** 新增 */
	public void insert(${table.typeName}Entity entity) {
		getSqlSession().insert(NAMESPACE + "insert", entity);
	}
	
	/** 删除  **/
	public void delete(List<String> ids, String modifyUser){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("modifyUser", modifyUser);
		map.put("modifyDate", new Date());
		// Y 为作废 N 作废
		map.put("active", "N");
    	getSqlSession().insert(NAMESPACE + "delete", map);
    }
	
	/** 修改 */
	public void update(${table.typeName}Entity entity) {
		getSqlSession().update(NAMESPACE + "update", entity);
	}
	
	/** 获取所有的记录 */
	public List<${table.typeName}Entity> getAll() {
		return getSqlSession().selectList(NAMESPACE + "getAll");
	}
	
	/** 获取符合条件的记录 */
	public List<${table.typeName}Entity> queryByEntity(${table.typeName}Entity entity, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return getSqlSession().selectList(NAMESPACE + "queryByEntity",entity,rowBounds);
	}
	
	/** 获取符合条件的记录数 */
	public Long queryByEntityCount(${table.typeName}Entity entity){
		return (Long)getSqlSession().selectOne(NAMESPACE + "queryByEntityCount",
				entity);
	}
	
}
