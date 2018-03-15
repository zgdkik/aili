package com.feisuo.yhhc.module.bseservice_gis.api.server.service;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;


public interface TrackOrdersHistoricalService {
	
	public List<TrackOrdersHistoricalDTO>  searchTrackOrdersHistorical(Map<String,Object> map)throws Exception;

}
