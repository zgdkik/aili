package com.feisuo.yhhc.module.bseservice_gis.server.service.impl;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.server.dao.OrderHeatMapDao;
import com.feisuo.yhhc.module.bseservice_gis.api.server.service.OrderHeatMapService;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.Order;


public class OrderHeatMapServiceImpl implements OrderHeatMapService {

	public OrderHeatMapDao orderHeatMapDao;
	
	public List<Order> searchTrackOrders(Map<String, Object> map) throws Exception {
		//查询 货运轨迹
		List<Order> list	= this.orderHeatMapDao.searchTrackOrders(map);
		return list;
	}

	public List<CityDTO> searchCitys() throws Exception {
		//查询 货运轨迹
		List<CityDTO> list	= this.orderHeatMapDao.searchCitys();
		return list;
	}

	public OrderHeatMapDao getOrderHeatMapDao() {
		return orderHeatMapDao;
	}

	public void setOrderHeatMapDao(OrderHeatMapDao orderHeatMapDao) {
		this.orderHeatMapDao = orderHeatMapDao;
	}

}
