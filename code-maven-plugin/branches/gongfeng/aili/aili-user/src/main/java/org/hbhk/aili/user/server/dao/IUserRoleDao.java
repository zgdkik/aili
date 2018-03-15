package org.hbhk.aili.user.server.dao;

import java.util.List;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.user.share.entity.UserRoleEntity;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity,String>{
	
	public int deleteByRoleId(List<String> userids);
	
	public int deleteRoleReByUserName(String userName);
	
	
}
