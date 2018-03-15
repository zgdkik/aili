package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TruckPointsDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackPointServiceImpl;

public class GetTrackThermalLineInfoAction extends AbstractController  {

	private TrackPointServiceImpl trackPointServiceImpl;	
	public TrackPointServiceImpl getTrackPointServiceImpl() {
		return trackPointServiceImpl;
	}

	public void setTrackPointServiceImpl(TrackPointServiceImpl trackPointServiceImpl) {
		this.trackPointServiceImpl = trackPointServiceImpl;
	}

	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();		
		//接收参数
		
		JSONObject obj = new JSONObject();


		Map<String, Object> model = new HashMap<String, Object>();
		request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 接收参数
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String city = request.getParameter("city");
	
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		TrackOrdersHistoricalDTO trackOrdersHistoricalDTO = new TrackOrdersHistoricalDTO();
		Map map =  new HashMap();
		
			Date endTimeDate = null;
			if(StringUtils.isEmpty(endTime)){
				Date startTimeDate = sdf.parse(startTime);
				startTimeDate.setHours(4);
				map.put("startTimeDate", startTimeDate);
				trackOrdersHistoricalDTO.setStartTime(startTime);
				
				endTimeDate = sdf.parse(startTime);
				endTimeDate.setHours(9);
				map.put("endTimeDate",endTimeDate);
			}else{
				Date startTimeDate = sdf.parse(startTime);
				map.put("startTimeDate", startTimeDate);
				trackOrdersHistoricalDTO.setStartTime(startTime);
				
				endTimeDate = sdf.parse(endTime);
				map.put("endTimeDate",endTimeDate);
				trackOrdersHistoricalDTO.setEndTime(endTime);
			}
		if(null!=city && ""!=city.trim() && !city.equals("全国")){
			map.put("City", city);
		}
		
		List<TruckPointsDTO> listTruckPointsDTO = new ArrayList<TruckPointsDTO>();
		List<TrackOrdersHistoricalDTO> trucks = trackPointServiceImpl.searchTracksInfo(map);
		for (TrackOrdersHistoricalDTO trackOrdersHistoricalDTO2 : trucks) {
			Map mapTemp =  new HashMap();
			mapTemp.putAll(map);
			mapTemp.put("userId", trackOrdersHistoricalDTO2.getUserID());
			List<PointDTO> listPoint = trackPointServiceImpl.searchTrackLineList(mapTemp);
			TruckPointsDTO p = new TruckPointsDTO();
			p.setUserId(trackOrdersHistoricalDTO2.getUserID());
			p.setPoints(listPoint);
			listTruckPointsDTO.add(p);
			
		}
		

		JSONArray jlist =new JSONArray();
		//jlist = jlist.fromObject(listTruckPointsDTO);
	
		//封裝list成為json		
		
		
		
	    ModelAndView mav = new ModelAndView();
	    MappingJacksonJsonView view = new MappingJacksonJsonView();
	    
	      Map attributes = new HashMap();
	          attributes.put("success", Boolean.TRUE);
	          attributes.put("truckThermalInfo", listTruckPointsDTO);
	          view.setAttributesMap(attributes);
	          mav.setView(view);
	          System.gc() ; 
		return mav;
	}
	
	

}
