package org.hbhk.hls.user.server.dao;

import org.apache.ibatis.annotations.Param;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.hls.user.share.entity.RolePrivilegeEntity;

public interface IRoleFunctionDao extends IBaseDao<RolePrivilegeEntity, String> {
	
	int deleteByRoleCode(@Param("roleCodes") String... roleCodes);

}