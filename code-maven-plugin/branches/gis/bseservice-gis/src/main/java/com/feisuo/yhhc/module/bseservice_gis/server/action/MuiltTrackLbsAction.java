package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MuiltTrackLbsAction extends AbstractController {

	private String viewPage; // 用于获取配置文件中的viewPage属性

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
		String muiltTrackLbs = request.getParameter("data");
		// 接收参数 根据多组 司机 和 坐标 时间  3个参数 在地图上展示司机信息
		// 定义参数格式  json  [{"userId":xxx,"point":xxx,"time":yyyy-MM-dd HH:mm:ss},{...},{....},{....}]
		JSONArray  muiltTrackLbsJSONArray = JSONArray.fromObject(muiltTrackLbs);
		// 送货起止信息
		model.put("muiltTrackLbsJSONArray", muiltTrackLbsJSONArray.toString());
		//model.put("jso", jso);		
		return new ModelAndView(getViewPage(), model);

	}

}
