package com.deppon.esb.management.web.action.infobull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.route.domain.RouteInfo;
import com.deppon.esb.management.route.service.IRouteService;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

@Controller("routeAction")
@Scope("prototype")
public class RouteAction extends ESBActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private IRouteService routeService;

	private List<RouteInfo> routeInfoList;

	@SuppressWarnings("unchecked")
	public String queryAllRoute() {
		Map<String, Object> result = new HashMap<String, Object>();
		result = routeService.queryAllRoute(start, start + limit);
		if (result == null) {
			return ERROR;
		}
		routeInfoList = ((List<RouteInfo>) result.get("routeInfoList"));
		resultCount = (Integer) result.get("resultCount");
		return SUCCESS;
	}

	public List<RouteInfo> getRouteInfoList() {
		if (routeInfoList == null) {
			routeInfoList = new ArrayList<RouteInfo>();
		}
		return routeInfoList;
	}

	public void setRouteInfoList(List<RouteInfo> routeInfoList) {
		this.routeInfoList = routeInfoList;
	}

}
