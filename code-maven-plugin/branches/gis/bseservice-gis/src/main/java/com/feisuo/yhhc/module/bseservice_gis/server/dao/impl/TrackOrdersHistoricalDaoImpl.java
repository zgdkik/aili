package com.feisuo.yhhc.module.bseservice_gis.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.frame_work.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.feisuo.yhhc.module.bseservice_gis.api.server.dao.TrackOrdersHistoricalDao;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.OrderSignInfoDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;

public class TrackOrdersHistoricalDaoImpl  extends  iBatis3DaoImpl  implements TrackOrdersHistoricalDao {

	
	public List<TrackOrdersHistoricalDTO>  searchTrackOrdersHistorical(
			Map<String, Object> map) throws Exception {
		//查询 货运轨迹
		List<TrackOrdersHistoricalDTO> list	= this.getSqlSession().selectList("searchTrackOrdersHistorical", map);
		return list;
	}

	public List<TrackOrdersHistoricalDTO> searchTrackPintList(Map<String, Object> map) throws Exception {
		//查询 货车信息 根据指定时间 
		List<TrackOrdersHistoricalDTO> list	= this.getSqlSession().selectList("searchTrackPintList", map);
		return list;
	}
	public List<PointDTO> searchTrackList(Map<String, Object> map) throws Exception {
		//查询 货车信息 根据指定时间 
		List<PointDTO> list	= this.getSqlSession().selectList("searchTrackList", map);
		return list;
	}

	public List<OrderSignInfoDTO> searchTrackSignInfoList(Map<String, Object> map) throws Exception {
		List<OrderSignInfoDTO> list	= this.getSqlSession().selectList("searchTrackSignInfoList", map);
		
		return list;
	}
	public List<CityDTO> searchCityList()throws Exception {
		//查询 货车信息 根据指定时间 
		List<CityDTO> list	= this.getSqlSession().selectList("searchCityList");
		return list;
	}

	public List<PointDTO> searchTrackLineList(Map map) throws Exception {
		//查询 货车信息 根据指定时间 
		List<PointDTO> list	= this.getSqlSession().selectList("searchTrackLineList", map);
		return list;
	}

	public List<TrackOrdersHistoricalDTO> searchTracksInfo(Map map) throws Exception {
		// TODO Auto-generated method stub
		//查询 货车信息 根据指定时间 
		List<TrackOrdersHistoricalDTO> list	= this.getSqlSession().selectList("searchTracksInfo", map);
		return list;
	}

	
 

}
