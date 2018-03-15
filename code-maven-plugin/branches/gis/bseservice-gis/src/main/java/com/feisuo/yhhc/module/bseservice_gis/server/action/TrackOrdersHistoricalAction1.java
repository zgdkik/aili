package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackOrdersHistoricalServiceImpl;

public class TrackOrdersHistoricalAction1 extends AbstractController  {

	private String viewPage; // 用于获取配置文件中的viewPage属性
	private TrackOrdersHistoricalServiceImpl trackOrdersHistoricalServiceImpl; // 用于获取配置文件中的viewPage属性
	


	public TrackOrdersHistoricalServiceImpl getTrackOrdersHistoricalServiceImpl() {
		return trackOrdersHistoricalServiceImpl;
	}

	public void setTrackOrdersHistoricalServiceImpl(
			TrackOrdersHistoricalServiceImpl trackOrdersHistoricalServiceImpl) {
		this.trackOrdersHistoricalServiceImpl = trackOrdersHistoricalServiceImpl;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
			String messageInfo=null;//错误信息
			
			//接收参数
			String UserID = request.getParameter("UserID");//司机编号 uuid
			String drivingStart = request.getParameter("drivingStart");//导航开始时间
			String drivingEnd = request.getParameter("drivingEnd");//导航结束时间
			String orderStartAddrPoint = request.getParameter("orderStart");//接货地点 坐标格式"，"分割 "111.00,39.00"
			String orderEndAddrPoint = request.getParameter("orderEnd");//送货地点 坐标格式"，"分割 "111.00,39.00"
			String signpoint = request.getParameter("signpoint");//签到地点 坐标格式"，"分割 "111.00,39.00"
			String signdate = request.getParameter("signdate");//签到时间"，"2015-01-21 10:58:45"
			if(null ==UserID||"undefined".equals(UserID)||"".equals(UserID))
			{
				UserID = request.getParameter("userid");//司机ID
			}
			
			Map<Object, Object> paraMap = new HashMap<Object, Object>();
			paraMap.put("UserID", UserID);
			if(null !=drivingStart&&!"undefined".equals(drivingStart)&&!"".equals(drivingStart.trim()))
			{
				Date drivingStartDate = sdf.parse(drivingStart);
				paraMap.put("drivingStart", drivingStartDate);

			}			
			if(null !=drivingEnd&&!"undefined".equals(drivingEnd)&&!"".equals(drivingEnd.trim()))
			{
				Date drivingEndDate = sdf.parse(drivingEnd);
				paraMap.put("drivingEnd", drivingEndDate);
				
			}			
			


			
			//送货起止信息-接收app后台传入参数 单独存入一个对象中
			TrackOrdersHistoricalDTO trackOrdersHistoricalDTO = new TrackOrdersHistoricalDTO();
			trackOrdersHistoricalDTO.setUserID(UserID);
			trackOrdersHistoricalDTO.setSigndate(signdate);
			trackOrdersHistoricalDTO.setSignpoint(signpoint);
			trackOrdersHistoricalDTO.setDrivingStart(drivingStart);
			trackOrdersHistoricalDTO.setDrivingEnd(drivingEnd);
			
			PointDTO orderStartAddrP = null;
			if(null !=orderStartAddrPoint&&!"undefined".equals(orderStartAddrPoint)&&!"".equals(orderStartAddrPoint.trim()))
			{
				orderStartAddrP = new PointDTO();
				String[] orderStartAddrPointArr = orderStartAddrPoint.split(",");
				orderStartAddrP.setLatitude(orderStartAddrPointArr[0]);
				orderStartAddrP.setLongitude(orderStartAddrPointArr[1]);
			}
			//
			PointDTO orderEndP =null;
			if(null !=orderEndAddrPoint&&!"undefined".equals(orderEndAddrPoint)&&!"".equals(orderEndAddrPoint.trim()))
			{	
				orderEndP = new PointDTO();
				String[] orderEndAddrPointArr = orderEndAddrPoint.split(",");
				orderEndP.setLatitude(orderEndAddrPointArr[0]);
				orderEndP.setLongitude(orderEndAddrPointArr[1]);
			}			
			
			//查询导航信息
			//List<TrackOrdersHistoricalDTO> list = trackOrdersHistoricalServiceImpl.searchTrackOrdersHistorical(paraMap);
			
			Map<Object, Object> parm = new HashMap<Object, Object>();
			paraMap.put("url", "10.174.107.27");
			paraMap.put("port", 27017);
			paraMap.put("dataBase", "TruckNo1_Lbs");
			
			
			paraMap.put("tableName", drivingStart.replaceAll("-", "").split(" ")[0]);
			/*paraMap.put("UserID", "");
			paraMap.put("drivingStart", "");
			paraMap.put("drivingEnd", "");*/
			SearchList s = new SearchList();
			List list =s.searchTrackOrdersHistorical(paraMap);
			
			model.put("trackOrdersHistoricalList", list);
			//送货起止信息
			model.put("trackOrdersHistoricalDTO", trackOrdersHistoricalDTO);
			model.put("orderStart", orderStartAddrP);
			model.put("drivingStart", request.getParameter("drivingStart"));//导航开始时间
			model.put("orderEndPoint", request.getParameter("orderEnd"));//送货地点 坐标格式"，"分割 "111.00,39.00"
			model.put("signpoint", request.getParameter("signpoint"));//签到地点 坐标格式"，"分割 "111.00,39.00"
			model.put("signdate", request.getParameter("signdate"));//签到时间"，"2015-01-21 10:58:45"
			model.put("drivingEnd", request.getParameter("drivingEnd"));//导航结束时间
			model.put("orderStartPoint", request.getParameter("orderStart"));//接货地点 坐标格式"，"分割 "111.00,39.00"
			model.put("shippoint", request.getParameter("shippoint"));//提货坐标"，"分割 "111.00,39.00"
			System.out.println(request.getParameter("shipdate"));
			model.put("shipdate", String.valueOf(request.getParameter("shipdate")));////提货时间时间"，"2015-01-21 10:58:45"
			model.put("orderEnd", orderEndP);
			model.put("message", messageInfo);
			model.put("errorMessage", null);
			model.put("channel", StringUtils.isBlank(request.getParameter("channel"))||StringUtils.isEmpty(request.getParameter("channel"))?false:true);
			//是否有途径地坐标
			String [] wayPointsArray = null;
			if(StringUtils.isNotBlank(request.getParameter("wayPoints"))||StringUtils.isNotEmpty(request.getParameter("wayPoints"))){
				String wayPointsStr = URLDecoder.decode(request.getParameter("wayPoints"));
				wayPointsArray = wayPointsStr.split(":");
				List<String> wayPointsList = new ArrayList<String>();
				Collections.addAll(wayPointsList, wayPointsArray);
				model.put("wayPoints", wayPointsList);
				
			}
			//是否有途径地地址
			String [] wayAdressArray = null;
			if(StringUtils.isNotBlank(request.getParameter("wayAdress"))||StringUtils.isNotEmpty(request.getParameter("wayAdress"))){
				String wayAdressStr = URLDecoder.decode(URLDecoder.decode(request.getParameter("wayAdress"),"utf-8"),"utf-8");
				wayAdressArray = wayAdressStr.split(",:,");
				List<String> wayAdressArrayList = new ArrayList<String>();
				Collections.addAll(wayAdressArrayList, wayAdressArray);
				model.put("wayAdress", wayAdressArrayList);
			}
			// 调用getViewPage获取要返回的页面
			return new ModelAndView(getViewPage(), model); 
		} catch (Exception e) {
			//异常
			model.put("message", "发生异常，参数不正确！");
			model.put("errorMessage", e.getMessage());
			e.printStackTrace();
			return new ModelAndView(getViewPage(), model); 
		}
		
		
	}



}
