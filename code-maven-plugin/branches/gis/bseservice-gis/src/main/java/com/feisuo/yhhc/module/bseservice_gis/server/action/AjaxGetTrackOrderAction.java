package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.CityDTO;
import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.Order;
import com.feisuo.yhhc.module.bseservice_gis.server.service.impl.OrderHeatMapServiceImpl;

public class AjaxGetTrackOrderAction extends AbstractController {

	private String viewPage; // 用于获取配置文件中的viewPage属性
	private OrderHeatMapServiceImpl orderHeatMapServiceImpl;

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String orders_id = request.getParameter("orders_id");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String city = request.getParameter("city");
		String type = request.getParameter("type");
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("orders_id", orders_id)	;
		map.put("startTime", startTime)	;
		map.put("endTime", endTime)	;
		map.put("city", city)	;
		map.put("type", type)	;
		
		List<Order> ol = orderHeatMapServiceImpl.searchTrackOrders(map);
		
		List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
		//cityDTOs = orderHeatMapServiceImpl.searchCitys();
		

		// 调用getViewPage获取要返回的页面
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(ol);
		//JSONArray jsonCityDTOs = new JSONArray();
		//jsonArray.addAll(cityDTOs);
		
		
	    ModelAndView mav = new ModelAndView();
	    MappingJacksonJsonView view = new MappingJacksonJsonView();
	      Map attributes = new HashMap();
	          attributes.put("success", Boolean.TRUE);
	          attributes.put("orderList", jsonArray.toString());
	         // attributes.put("cityDTOs", jsonCityDTOs.toString());
	          view.setAttributesMap(attributes);
	          mav.setView(view);
		return mav;
	}

	public OrderHeatMapServiceImpl getOrderHeatMapServiceImpl() {
		return orderHeatMapServiceImpl;
	}

	public void setOrderHeatMapServiceImpl(OrderHeatMapServiceImpl orderHeatMapServiceImpl) {
		this.orderHeatMapServiceImpl = orderHeatMapServiceImpl;
	}

	


}
