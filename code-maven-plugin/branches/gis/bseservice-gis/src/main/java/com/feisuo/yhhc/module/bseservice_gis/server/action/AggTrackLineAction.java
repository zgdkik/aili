package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackPointServiceImpl;

public class AggTrackLineAction extends AbstractController {

	private String viewPage; // 用于获取配置文件中的viewPage属性
	private TrackPointServiceImpl trackPointServiceImpl;

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		request.setCharacterEncoding("utf-8");
	/*	request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 接收参数
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String city = request.getParameter("City");
	
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		TrackOrdersHistoricalDTO trackOrdersHistoricalDTO = new TrackOrdersHistoricalDTO();
		Map map =  new HashMap();
		
		if(null!=startTime && ""!=startTime.trim()){
			Date startTimeDate = sdf.parse(startTime);
			map.put("startTimeDate", startTimeDate);
			trackOrdersHistoricalDTO.setStartTime(startTime);
		}else{
			String orderTimeSub ="0 0:30:0"; 
			map.put("orderTimeSub", orderTimeSub);
		}
		if(null!=endTime && ""!=endTime.trim()){
			Date endTimeDate = sdf.parse(endTime);
			map.put("endTimeDate", endTimeDate);
			trackOrdersHistoricalDTO.setEndTime(endTime);
		}
		map.put("City", city);
		if(null==startTime || ""!=startTime.trim()){
			trackOrdersHistoricalDTO.setCity("全国");
		}else{
			trackOrdersHistoricalDTO.setCity(city);
		}
		
		
		List<TrackOrdersHistoricalDTO> list = trackPointServiceImpl.searchTrackList(map);
		

		model.put("trackOrdersHistoricalDTO", trackOrdersHistoricalDTO);
		model.put("trackList", list);*/
		
		String citysStr = "广州市";//request.getParameter("citys");
		//citysStr = new String(citysStr.getBytes("GB2312"),"utf-8");
		List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
		if(StringUtils.isNotEmpty(citysStr)){
			String[] city = citysStr.split(",");
			for (String str : city) {
				CityDTO cd = new CityDTO();
				cd.setId(str);
				cd.setName(str);
				cityDTOs.add(cd);
			}
		}else{
			CityDTO cd = new CityDTO();
			cd.setId("请选择");
			cd.setName("请选择");
			cityDTOs.add(cd);
		}
		/*else{
			cityDTOs = trackPointServiceImpl.searchCityList();
		}*/
		
		model.put("cityList", cityDTOs);
		// 调用getViewPage获取要返回的页面
		return new ModelAndView(getViewPage(), model);
	}

	public TrackPointServiceImpl getTrackPointServiceImpl() {
		return trackPointServiceImpl;
	}

	public void setTrackPointServiceImpl(TrackPointServiceImpl trackPointServiceImpl) {
		this.trackPointServiceImpl = trackPointServiceImpl;
	}



}
