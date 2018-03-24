package com.yimidida.ows.user.server.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.user.share.entity.RolePrivilegeEntity;

public interface IRoleFunctionDao extends IBaseDao<RolePrivilegeEntity, String> {
	
	int deleteByRoleCode(@Param("roleCodes") String... roleCodes);

}
