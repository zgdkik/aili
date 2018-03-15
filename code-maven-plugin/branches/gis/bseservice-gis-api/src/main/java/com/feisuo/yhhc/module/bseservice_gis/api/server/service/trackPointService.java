package com.feisuo.yhhc.module.bseservice_gis.api.server.service;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;


public interface trackPointService {
	
	public List<TrackOrdersHistoricalDTO>  searchTrackPointList(Map<String,Object> map)throws Exception;
	public List<PointDTO>  searchTrackList(Map<String,Object> map)throws Exception;
	public List<TrackOrdersHistoricalDTO> searchTracksInfo(Map map)throws Exception;

}
