package org.hbhk.aili.user.server.dao;

import java.util.List;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.user.share.entity.RoleEntity;

public interface IRoleDao extends IBaseDao<RoleEntity, String> {
	
	int deleteRoleById(List<String> id);
	
}
