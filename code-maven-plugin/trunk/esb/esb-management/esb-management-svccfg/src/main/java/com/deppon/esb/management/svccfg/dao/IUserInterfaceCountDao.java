package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.security.domain.UserInterfaceCount;

public interface IUserInterfaceCountDao {
	
	public List<UserInterfaceCount> queryAllUserInterfaceCount();
	
}
