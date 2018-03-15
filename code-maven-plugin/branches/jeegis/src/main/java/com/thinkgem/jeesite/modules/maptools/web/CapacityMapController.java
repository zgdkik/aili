/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maptools.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.maptools.entity.OrderLoadesTogetherMap;

/**
 * 拼单管理Controller
 * @author 刘荣辉
 * @version 2015-10-26
 */
@Controller
@RequestMapping(value = "/p/maptools/capacityMap")
public class CapacityMapController extends BaseController {

	private static String CAPACITYMAPDATAREQADDRESS = Global.getConfig("capacityMapDataReqAddress");
	
	@RequestMapping(value = {"capacityMap", ""})
	public String list(OrderLoadesTogetherMap orderLoadesTogetherMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tOrderScheduling", orderLoadesTogetherMap);
		return "modules/maptools/capacityMap";
	}

	@RequestMapping(value ="capacityMapReli")
	public String capacityMapReli(OrderLoadesTogetherMap orderLoadesTogetherMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tOrderScheduling", orderLoadesTogetherMap);
		return "modules/maptools/capacityReliMap";
	}
	
	@ResponseBody
	@RequestMapping(value = "getTruckList")
	public List<Map<String, Object>> getTruckInfoList(
			@RequestParam(required=false) String startDate, 
			@RequestParam(required=false) String endDate,
			@RequestParam(required=false) String province,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String area,
			@RequestParam(required=false) String truckType,
			@RequestParam(required=false) String verified,
			@RequestParam(required=false) String addressType,
			HttpServletResponse response) throws Exception {
		boolean isVerified =false;
		if(null!=verified&&verified.equals("1")){
			isVerified = true;
		}else{
			isVerified = false;
		}
		String url = CAPACITYMAPDATAREQADDRESS;
	

		//返回结果实例
			JSONObject obj = new JSONObject();
			obj.element("startDate", startDate);
			obj.element("endDate", endDate);
			obj.element("province", province);
			obj.element("city", city);
			obj.element("area", area);
			obj.element("truckType", truckType);
			obj.element("verified", isVerified);
			obj.element("addressType", addressType);
			
			
			
			        HttpClient client = new HttpClient();   
			        //设置代理服务器地址和端口     
			     //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
			        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
			       // HttpMethod method = new GetMethod("http://10.1.14.20:8088/workflowController/service/todo/addTask"); 
			        //使用POST方法
			        PostMethod method = new PostMethod(url); 
			     
		            String transJson = obj.toString();
		            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
		            method.setRequestEntity(se);
		            //使用系统提供的默认的恢复策略
		            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		            //设置超时的时间
		            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
			    	 
			     
			     client.executeMethod(method);
			        //打印服务器返回的状态
			     System.out.println(method.getStatusLine()+"司机运力查询ok");
			       //打印返回的信息
			     System.out.println();
			     InputStream stream = method.getResponseBodyAsStream();
			     
			     BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			     StringBuffer buf = new StringBuffer();
			     String line;
			     while (null != (line = br.readLine())) {
			         buf.append(line).append("\n");
			     }
			       //释放连接
			     method.releaseConnection();
			   


			
			//String ss =httpPostJson.postJson(url,obj);
			List<Map<String, Object>> mapList = Lists.newArrayList();
			Map<String, Object> map = Maps.newHashMap();
			
			//根据条件查询司机  名称 状态 类型 车型类型
			//List<OrderNode> loadNodes = orderNodeService.getLoadNodeList(startTime,endTime,reqBigenTime,reqEndTime,city,status);
			JsonMapper jm = new JsonMapper();
			map.put("rs", jm.toJson(buf.toString()));
			map.put("status", Boolean.TRUE);
			mapList.add(map);
			return mapList;
	}	

}