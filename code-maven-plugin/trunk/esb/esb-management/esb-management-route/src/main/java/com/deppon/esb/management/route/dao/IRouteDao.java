package com.deppon.esb.management.route.dao;

import java.util.List;

import com.deppon.esb.management.route.domain.RouteInfo;

public interface IRouteDao {

	List<RouteInfo> selectAllRoute(int start, int limit);
	
	Integer selectRouteCount();

}
