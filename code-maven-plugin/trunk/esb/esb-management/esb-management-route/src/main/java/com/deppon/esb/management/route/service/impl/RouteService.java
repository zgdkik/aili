package com.deppon.esb.management.route.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.route.dao.IRouteDao;
import com.deppon.esb.management.route.service.IRouteService;

@Transactional
@Service
public class RouteService implements IRouteService{

	@Resource
	private IRouteDao routeDao;

	@Override
	public Map<String, Object> queryAllRoute(int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("routeInfoList", routeDao.selectAllRoute(start, limit));
		map.put("resultCount", routeDao.selectRouteCount());
		return map;
	}

}
