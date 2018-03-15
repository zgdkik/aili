package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.feisuo.yhhc.module.bseservice_gis.server.util.HttpPostJson;

public class GetTrackInfoAction extends AbstractController  {

	
	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();		
		//接收参数
		String userId = request.getParameter("userid");//司机ID
		
		//构造json格式的post请求
		String url ="http://10.175.204.127:8891/Cargo.svc/GetTruckDetail";
		
		JSONObject obj = new JSONObject();
		obj.element("userid", userId);
		HttpPostJson httpPostJson= new HttpPostJson();
		String result = httpPostJson.postJson(url, obj);
		
	    ModelAndView mav = new ModelAndView();
	    MappingJacksonJsonView view = new MappingJacksonJsonView();
	      Map attributes = new HashMap();
	          attributes.put("success", Boolean.TRUE);
	          attributes.put("truckInfo", result);
	          view.setAttributesMap(attributes);
	          mav.setView(view);
		return mav;
	}
	
	

}
