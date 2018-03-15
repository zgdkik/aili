package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.security.domain.InterfaceCount;
import com.deppon.esb.security.domain.UserInterfaceRelation;


public interface IInterfaceCountDao {
	
	public List<InterfaceCount> queryAllInterfaceCount();
	
}
