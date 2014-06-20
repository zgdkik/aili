package org.hbhk.aili.security.server.dao;

import org.hbhk.aili.mybatis.server.dao.IAiliDao;
import org.hbhk.aili.security.share.pojo.RoleInfo;

public interface IRoleDao extends IAiliDao<RoleInfo,String> {
	
	public  RoleInfo  getRole(String code);

}
