package com.feisuo.yhhc.module.bseservice_gis.api.server.service;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;


public interface TrackNearOrderService {
	
	public List<TrackOrdersHistoricalDTO>  searchTrackPintList(Map<String,Object> map)throws Exception;

}
