package com.feisuo.yhhc.module.bseservice_gis.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.frame_work.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.feisuo.yhhc.module.bseservice_gis.api.server.dao.OrderHeatMapDao;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.Order;

public class OrderHeatMapDaoImpl  extends  iBatis3DaoImpl  implements OrderHeatMapDao {


	public List<Order> searchTrackOrders(Map<String, Object> map) throws Exception {
		//查询 货运轨迹
		List<Order> list	= this.getSqlSession().selectList("searchOrderMap", map);
		return list;
	}

	public List<CityDTO> searchCitys() throws Exception {
		//查询 货运轨迹
		List<CityDTO> list	= this.getSqlSession().selectList("searchCityList");
		return list;
	}

	
 

}
