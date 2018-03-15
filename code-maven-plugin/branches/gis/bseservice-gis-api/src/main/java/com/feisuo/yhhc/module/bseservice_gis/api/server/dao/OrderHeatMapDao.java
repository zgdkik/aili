package com.feisuo.yhhc.module.bseservice_gis.api.server.dao;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.Order;


public interface OrderHeatMapDao {

	public List<Order>  searchTrackOrders(Map<String,Object> map)throws Exception;

	public List<CityDTO> searchCitys()throws Exception;
	
}
