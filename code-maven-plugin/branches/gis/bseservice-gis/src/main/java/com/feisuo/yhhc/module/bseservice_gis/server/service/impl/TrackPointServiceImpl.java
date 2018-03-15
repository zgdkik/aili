package com.feisuo.yhhc.module.bseservice_gis.server.service.impl;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.server.dao.TrackOrdersHistoricalDao;
import com.feisuo.yhhc.module.bseservice_gis.api.server.service.trackPointService;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;


public class TrackPointServiceImpl implements trackPointService {

	public TrackOrdersHistoricalDao trackOrdersHistoricalDao;
	

	
	public TrackOrdersHistoricalDao getTrackOrdersHistoricalDao() {
		return trackOrdersHistoricalDao;
	}


	public void setTrackOrdersHistoricalDao(
			TrackOrdersHistoricalDao trackOrdersHistoricalDao) {
		this.trackOrdersHistoricalDao = trackOrdersHistoricalDao;
	}

	
	



	public List<TrackOrdersHistoricalDTO> searchTrackPointList(
			Map<String, Object> map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackPintList(map);
	}
	
	public List<PointDTO> searchTrackList(
			Map<String, Object> map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackList(map);
	}
	
	public List<CityDTO> searchCityList() throws Exception {
		return trackOrdersHistoricalDao.searchCityList();
	}


	public List<PointDTO> searchTrackLineList(Map map) throws Exception {
		return trackOrdersHistoricalDao.searchTrackLineList(map);
	}


	public List<TrackOrdersHistoricalDTO> searchTracksInfo(Map map) throws Exception {
		return trackOrdersHistoricalDao.searchTracksInfo(map);
	}


}
