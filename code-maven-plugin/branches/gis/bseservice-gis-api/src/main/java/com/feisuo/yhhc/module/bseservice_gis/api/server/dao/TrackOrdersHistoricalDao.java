package com.feisuo.yhhc.module.bseservice_gis.api.server.dao;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.OrderSignInfoDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;


public interface TrackOrdersHistoricalDao {

	public List<TrackOrdersHistoricalDTO>  searchTrackOrdersHistorical(Map<String,Object> map)throws Exception;

	public List<TrackOrdersHistoricalDTO> searchTrackPintList(Map<String, Object> map)throws Exception;
	public List<OrderSignInfoDTO> searchTrackSignInfoList(Map<String, Object> map)throws Exception;
	public List<PointDTO> searchTrackList(Map<String, Object> map)throws Exception;
	public List<CityDTO> searchCityList()throws Exception;

	public List<PointDTO> searchTrackLineList(Map map)throws Exception;
	public List<TrackOrdersHistoricalDTO> searchTracksInfo(Map map)throws Exception;
	
}
