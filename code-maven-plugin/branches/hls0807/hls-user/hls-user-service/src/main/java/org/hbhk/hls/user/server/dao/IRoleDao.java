package org.hbhk.hls.user.server.dao;

import java.util.List;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.hls.user.share.entity.RoleEntity;

public interface IRoleDao extends IBaseDao<RoleEntity, String> {
	
	int deleteRoleById(List<String> id);
	
}
