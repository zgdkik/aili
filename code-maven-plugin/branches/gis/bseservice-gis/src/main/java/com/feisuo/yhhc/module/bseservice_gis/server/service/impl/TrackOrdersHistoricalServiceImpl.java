package com.feisuo.yhhc.module.bseservice_gis.server.service.impl;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.server.dao.TrackOrdersHistoricalDao;
import com.feisuo.yhhc.module.bseservice_gis.api.server.service.TrackNearOrderService;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.OrderSignInfoDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;


public class TrackOrdersHistoricalServiceImpl implements TrackNearOrderService {

	public TrackOrdersHistoricalDao trackOrdersHistoricalDao;
	

	
	public TrackOrdersHistoricalDao getTrackOrdersHistoricalDao() {
		return trackOrdersHistoricalDao;
	}



	public void setTrackOrdersHistoricalDao(
			TrackOrdersHistoricalDao trackOrdersHistoricalDao) {
		this.trackOrdersHistoricalDao = trackOrdersHistoricalDao;
	}



	public List<TrackOrdersHistoricalDTO> searchTrackOrdersHistorical(Map<String, Object> map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackOrdersHistorical(map);
	}



	public List<TrackOrdersHistoricalDTO> searchTrackPintList(Map<String, Object> map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackPintList(map);
	}
	public List<OrderSignInfoDTO> searchTrackSignInfoList(Map<String, Object> map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackSignInfoList(map);
	}


}
