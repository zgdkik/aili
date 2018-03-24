package com.yimidida.ows.user.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.user.share.entity.RoleEntity;

public interface IRoleDao extends IBaseDao<RoleEntity, String> {
	
	int deleteRoleById(List<String> id);
	
}
