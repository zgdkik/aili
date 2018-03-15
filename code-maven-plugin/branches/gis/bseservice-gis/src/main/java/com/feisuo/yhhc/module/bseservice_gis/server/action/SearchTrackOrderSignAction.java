package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.OrderSignInfoDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackOrdersHistoricalServiceImpl;

public class SearchTrackOrderSignAction extends AbstractController  {

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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//时间格式化
			Map paraMap = new HashMap<String, Object>();
			//查询所有已签单的订单信息
			List<OrderSignInfoDTO> ordersigninfoDTOList = trackOrdersHistoricalServiceImpl.searchTrackSignInfoList(paraMap);
			for (OrderSignInfoDTO orderSignInfoDTO : ordersigninfoDTOList) {
				paraMap.clear();
				paraMap.put("UserID", orderSignInfoDTO.getTruckID());//用户ID
				paraMap.put("drivingStart", sdf.parse(orderSignInfoDTO.getUseTime()));//用车时间--轨迹查询开始时间 drivingStart
				paraMap.put("drivingEnd", sdf.parse(orderSignInfoDTO.getSignDate()));//签单时间--轨迹查询结束时间 drivingEnd
				//查询导航信息
				List<TrackOrdersHistoricalDTO> trackOrdersHistoricalList = trackOrdersHistoricalServiceImpl.searchTrackOrdersHistorical(paraMap);
				//需要传出orderSignInfoDTO.getSignPoint()
				orderSignInfoDTO.setTrackOrdersHistoricalList(trackOrdersHistoricalList);
			}
			
			
			model.put("ordersigninfoDTOList", ordersigninfoDTOList);
			model.put("ordersigninfoDTOListLength", ordersigninfoDTOList.size());
			
			// 调用getViewPage获取要返回的页面
			return new ModelAndView(getViewPage(), model); 
		} catch (Exception e) {
			//异常
			model.put("message", "发生异常！");
			model.put("errorMessage", e.getStackTrace());
			return new ModelAndView(getViewPage(), model); 
		}
		
		
	}



}
