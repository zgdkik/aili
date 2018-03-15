package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.TrackNearOrderServiceImpl;

public class TrackNearOrderAction extends AbstractController {

	private String viewPage; // 用于获取配置文件中的viewPage属性
	private TrackNearOrderServiceImpl trackNearOrderServiceImpl;

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 接收参数
		String orderTime = request.getParameter("orderStart");// 订单时间
		Map<String, Object> paraMap = new HashMap<String, Object>();
		TrackOrdersHistoricalDTO trackOrdersHistoricalDTO = new TrackOrdersHistoricalDTO();
		String orderLatLng = request.getParameter("drivingStart");// 订单经纬度
		if (null != orderLatLng && !"undefined".equals(orderLatLng)) {
			//orderLatLng = "113.387749,23.022309"; // 先模拟一个
			Double orderLat = Double.valueOf(orderLatLng.split(",")[0]);
			Double orderLng = Double.valueOf(orderLatLng.split(",")[1]);
			trackOrdersHistoricalDTO.setLatitude(orderLat);
			trackOrdersHistoricalDTO.setLongitude(orderLng);
		}

		if (null != orderTime && !"undefined".equals(orderTime)&&!"".equals(orderTime)) {
			Date orderTimeDate = sdf.parse(orderTime);
			paraMap.put("orderTime", orderTimeDate);
			trackOrdersHistoricalDTO.setOrderTime(orderTime);
		}
		// 送货起止信息
		model.put("trackOrdersHistoricalDTO", trackOrdersHistoricalDTO);
		// 调用getViewPage获取要返回的页面
		return new ModelAndView(getViewPage(), model);

	}

	public TrackNearOrderServiceImpl getTrackNearOrderServiceImpl() {
		return trackNearOrderServiceImpl;
	}

	public void setTrackNearOrderServiceImpl(
			TrackNearOrderServiceImpl trackNearOrderServiceImpl) {
		this.trackNearOrderServiceImpl = trackNearOrderServiceImpl;
	}

}
