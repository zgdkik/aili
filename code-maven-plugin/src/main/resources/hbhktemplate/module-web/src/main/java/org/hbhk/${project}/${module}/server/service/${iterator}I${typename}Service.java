package com.deppon.${project}.module.${module}.server.service;

import com.deppon.${project}.module.${module}.shared.domain.${table.typeName}Entity;
import com.deppon.foss.framework.service.IService;
import com.deppon.${project}.module.${module}.shared.exception.${table.typeName}Exception;
import java.util.List;

/**
 * ${table.name}
 * 
 * @author DPAP ${.now}
 * 
 */
public interface I${table.typeName}Service extends IService {
	
	/** 新增 */
	void insert(${table.typeName}Entity entity) throws ${table.typeName}Exception;
	
	/** 删除  **/
	void delete(List<String> ids) throws ${table.typeName}Exception;
	
	/** 修改 */
	void update(${table.typeName}Entity entity) throws ${table.typeName}Exception;
		
	/** 获取所有的记录 */
	List<${table.typeName}Entity> getAll() throws ${table.typeName}Exception;
	
	/** 获取符合条件的记录 */
	List<${table.typeName}Entity> queryByEntity(${table.typeName}Entity entity, int start, int limit) throws ${table.typeName}Exception;

	/** 获取符合条件的记录数 */
	Long queryByEntityCount(${table.typeName}Entity entity) throws ${table.typeName}Exception;
}
