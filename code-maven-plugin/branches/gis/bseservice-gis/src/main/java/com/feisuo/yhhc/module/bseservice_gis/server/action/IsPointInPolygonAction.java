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

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.IsPointInPolygonServiceImpl;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackNearOrderServiceImpl;

public class IsPointInPolygonAction extends AbstractController  {

	private TrackNearOrderServiceImpl 	trackNearOrderServiceImpl;
	private IsPointInPolygonServiceImpl 	isPointInPolygonServiceImpl;

	
	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//接收参数
		String province = request.getParameter("Province");//省份
		String circlesLats = request.getParameter("circlesLats");//圆的经度坐标集合
		String circlesLngs = request.getParameter("circlesLngs");//圆的纬度坐标集合
		//把经纬度坐标处理成 对象数据
		List<Double> polygonXA = dealCirclesLatLng(circlesLats);
		List<Double> polygonYA = dealCirclesLatLng(circlesLngs);
		
		String orderTimeSub = request.getParameter("orderTimeSub");//时间格式
		//处理时间差 使符合mysql处理函数
		orderTimeSub = dealOrderTimeSub(orderTimeSub);
		
		//处理时间格式
		String orderTime = request.getParameter("orderTime");//订单时间
		Date orderTimeDate=null;
		if(orderTime!=null&&!"undefined".equals(orderTime)&&!"".equals(orderTime)){
			orderTimeDate = sdf.parse(orderTime);
		}
		orderTimeDate = new Date();

		
		//送货起止信息-接收app后台传入参数 单独存入一个对象中
		String orderStartAddrPoint ="113.387749,23.022309"; //先模拟一个
		String orderEndAddrPoint ="113.306338,22.972316";
		TrackOrdersHistoricalDTO trackOrdersHistoricalDTO = new TrackOrdersHistoricalDTO();
		//经度
		String[] orderStartAddrPointArr = orderStartAddrPoint.split(",");
		PointDTO orderStartAddrP = new PointDTO();
		orderStartAddrP.setLatitude(orderStartAddrPointArr[0]);
		orderStartAddrP.setLongitude(orderStartAddrPointArr[1]);
		//纬度
		String[] orderEndAddrPointArr = orderEndAddrPoint.split(",");
		PointDTO orderEndP = new PointDTO();
		orderEndP.setLatitude(orderEndAddrPointArr[0]);
		orderEndP.setLongitude(orderEndAddrPointArr[1]);
		
		Map<String , Object> paraMap = new HashMap<String, Object>();
		paraMap.put("Province", province);
		paraMap.put("orderTime", orderTimeDate);
		paraMap.put("orderTimeSub", orderTimeSub);
		
		//查询司机信息
		List<TrackOrdersHistoricalDTO> list = trackNearOrderServiceImpl.searchTrackPintList(paraMap);
		List<TrackOrdersHistoricalDTO> TrackOrdersHistoricalDTOList = new ArrayList<TrackOrdersHistoricalDTO>();
		//判断司机是否在给定的圆形范围  圆形范围顶点坐标由前端传入 先模拟一个圆形坐标区域
		for (TrackOrdersHistoricalDTO trackOrdersHistoricalDTO2 : list) {
			Double latitude = trackOrdersHistoricalDTO2.getLatitude();
			Double longitude = trackOrdersHistoricalDTO2.getLongitude();
			boolean flag = isPointInPolygonServiceImpl.isPointInPolygon(latitude, longitude, polygonXA, polygonYA);
			if(flag){
				TrackOrdersHistoricalDTOList.add(trackOrdersHistoricalDTO2);
			}
		}

		model.put("trackOrdersHistoricalList", TrackOrdersHistoricalDTOList);
		//送货起止信息
		model.put("trackOrdersHistoricalDTO", trackOrdersHistoricalDTO);
		// 调用getViewPage获取要返回的页面
		JSONObject jsonObject = JSONObject.fromObject(trackOrdersHistoricalDTO);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(TrackOrdersHistoricalDTOList);
		JSONArray TrackOrdersAllDTOList = new JSONArray();
		TrackOrdersAllDTOList.addAll(list);
		
	    ModelAndView mav = new ModelAndView();
	    MappingJacksonJsonView view = new MappingJacksonJsonView();
	      Map attributes = new HashMap();
	          attributes.put("success", Boolean.TRUE);
	          attributes.put("trackOrdersHistoricalDTO", jsonObject.toString());
	          attributes.put("TrackOrdersHistoricalDTOList", jsonArray.toString());
	          attributes.put("TrackOrdersAllDTOList", TrackOrdersAllDTOList.toString());
	          view.setAttributesMap(attributes);
	          mav.setView(view);
		return mav;
	}
	
	
	//把经纬度坐标处理成 对象数据
	private List<Double> dealCirclesLatLng(String circlesLatLng) {
		List<Double> polygon = new ArrayList<Double>();
		String[] polygonXYAString =circlesLatLng.split(",");
		for (String string : polygonXYAString) {
			polygon.add(Double.valueOf(string));
		}
		return polygon;
	}

	private String dealOrderTimeSub(String orderTimeSub) {
		int orderTimeSubVal=Integer.parseInt(orderTimeSub);
		switch(orderTimeSubVal) 
		{ 
		case 2: 
		orderTimeSub ="0 0:02:0"; 
		break; 
		case 5: 
			orderTimeSub ="0 0:05:0"; 
			break; 
		case 10: 
			orderTimeSub ="0 0:10:0"; 
			break; 
		case 30: 
			orderTimeSub ="0 0:30:0"; 
		break; 
		case 60: 
			orderTimeSub ="0 1:0:0"; 
		break; 
		case 120: 
			orderTimeSub ="0 2:00:0"; 
			break; 
		case 180: 
			orderTimeSub ="0 3:0:0"; 
			break; 
		case 240: 
			orderTimeSub ="0 4:0:0"; 
			break; 
		case 300: 
			orderTimeSub ="0 5:0:0"; 
			break; 
		case 360: 
			orderTimeSub ="0 6:0:0"; 
			break; 
		case 420: 
			orderTimeSub ="0 7:0:0"; 
			break; 
		case 480: 
			orderTimeSub ="0 8:0:0"; 
			break; 
		case 540: 
			orderTimeSub ="0 9:0:0"; 
			break; 
		case 600: 
			orderTimeSub ="0 10:0:0"; 
			break; 
		case 1440: 
			orderTimeSub ="1 0:0:0"; 
			break; 
		case 14400: 
			orderTimeSub ="10 0:0:0"; 
			break; 
		default: 
		break; 
		}
		return orderTimeSub;
	}

	public TrackNearOrderServiceImpl getTrackNearOrderServiceImpl() {
		return trackNearOrderServiceImpl;
	}

	public void setTrackNearOrderServiceImpl(
			TrackNearOrderServiceImpl trackNearOrderServiceImpl) {
		this.trackNearOrderServiceImpl = trackNearOrderServiceImpl;
	}

	public IsPointInPolygonServiceImpl getIsPointInPolygonServiceImpl() {
		return isPointInPolygonServiceImpl;
	}

	public void setIsPointInPolygonServiceImpl(
			IsPointInPolygonServiceImpl isPointInPolygonServiceImpl) {
		this.isPointInPolygonServiceImpl = isPointInPolygonServiceImpl;
	}



}
