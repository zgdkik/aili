package com.deppon.esb.management.route.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.route.dao.IRouteDao;
import com.deppon.esb.management.route.domain.RouteInfo;

@Repository
public class RouteDao extends IBatis3DaoImpl implements IRouteDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteInfo> selectAllRoute(int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("limit", limit);
		return getSqlSession().selectList("com.deppon.esb.management.route.domain.RouteInfo.selectAllRoute", map);
	}

	@Override
	public Integer selectRouteCount() {
		return (Integer) getSqlSession().selectOne("com.deppon.esb.management.route.domain.RouteInfo.selectRouteCount");
	}

}
