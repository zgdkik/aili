[#ftl]
[#assign dao="${table.typeName?uncap_first}Dao"]
package com.deppon.${project}.module.${module}.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.deppon.${project}.framework.server.context.UserContext;
import com.deppon.${project}.module.${module}.server.dao.I${table.typeName}Dao;
import com.deppon.${project}.module.${module}.server.service.I${table.typeName}Service;
import com.deppon.${project}.module.${module}.shared.domain.${table.typeName}Entity;
import com.deppon.${project}.module.${module}.shared.exception.${table.typeName}Exception;
/**
 * ${table.name}
 * 
 * @author DPAP ${.now}
 * 
 */
public class ${table.typeName}Service   implements I${table.typeName}Service{
	
	private I${table.typeName}Dao ${dao};
	
	/** 新增 */
	public void insert(${table.typeName}Entity entity) throws ${table.typeName}Exception {
		if(entity == null){
			throw new ${table.typeName}Exception("param is null");
		}
		String username =UserContext.getCurrentUser().getUserName();
		if(username == null || username.equals("")){
			throw new ${table.typeName}Exception("user not login");
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		entity.setId(uuid);
		entity.setActive("Y");
		entity.setCreateDate(new Date());
		entity.setCreateUser(username);
		this.${dao}.insert(entity);
	}
	
	/** 删除  **/
	public void delete(List<String> ids){
		if(ids==null || ids.size() == 0){
			throw new ${table.typeName}Exception("param is null");
		}
		String username =UserContext.getCurrentUser().getUserName();
		if(username == null || username.equals("")){
			throw new ${table.typeName}Exception("user not login");
		}
		this.${dao}.delete(ids, username);
    }
	
	/** 修改 */
	public void update(${table.typeName}Entity entity) throws ${table.typeName}Exception {
		if(entity == null){
			throw new ${table.typeName}Exception("param is null");
		}
		String username =UserContext.getCurrentUser().getUserName();
		if(username == null || username.equals("")){
			throw new ${table.typeName}Exception("user not login");
		}
		entity.setModifyDate(new Date());
		entity.setModifyUser(username);
		this.${dao}.update(entity);
	}
		
	/** 获取所有的记录 */
	public List<${table.typeName}Entity> getAll() throws ${table.typeName}Exception {
		
		return this.${dao}.getAll();
	}
	
	/** 获取符合条件的记录 */
	public List<${table.typeName}Entity> queryByEntity(${table.typeName}Entity entity , int start, int limit) {
		if(entity == null){
			throw new ${table.typeName}Exception("param is null");
		}
		return this.${dao}.queryByEntity(entity,start,limit);
	}
	
	public void set${dao?cap_first}(I${table.typeName}Dao ${dao}) {
		this.${dao} = ${dao};
	}
	
	/** 获取符合条件的记录数 */
	public Long queryByEntityCount(${table.typeName}Entity entity){
		if(entity == null){
			throw new ${table.typeName}Exception("param is null");
		}
		return  this.${dao}.queryByEntityCount(entity);
	}
	
}
