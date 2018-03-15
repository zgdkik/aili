package org.hbhk.hls.user.server.service;

import java.util.List;

public interface IUserRoleService {
	
	int deleteRoleRe(List<String> roleids);
	
	int deleteRoleReByUserName(String userName);

	int addRoleRe(List<String> roleids, String userId);
	
	List<String> getRoleIdByUserName(String userName);
	
}
