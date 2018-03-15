package com.feisuo.sds.user.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.feisuo.sds.user.share.entity.RoleEntity;

public interface IRoleDao extends IBaseDao<RoleEntity, String> {
	
	int deleteRoleById(List<String> id);
	
}
