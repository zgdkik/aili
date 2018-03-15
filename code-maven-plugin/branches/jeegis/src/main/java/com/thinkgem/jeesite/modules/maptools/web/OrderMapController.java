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
@RequestMapping(value = "/p/maptools/orderMap")
public class OrderMapController extends BaseController {

	private static String ORDERMAPDATAREQADDRESS = Global.getConfig("orderMapDataReqAddress");//订单分布接口
	private static String CAPACITYMAPDATAREQADDRESS = Global.getConfig("capacityMapDataReqAddress");//运力分布接口

	
	@RequestMapping(value = {"orderMap", ""})
	public String list(OrderLoadesTogetherMap orderLoadesTogetherMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tOrderScheduling", orderLoadesTogetherMap);
		return "modules/maptools/orderMap";
	}

	@RequestMapping(value ="orderMapReli")
	public String capacityMapReli(OrderLoadesTogetherMap orderLoadesTogetherMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tOrderScheduling", orderLoadesTogetherMap);
		return "modules/maptools/orderReliMap";
	}

	@ResponseBody
	@RequestMapping(value = "getOrderList")
	public List<Map<String, Object>> getTruckInfoList(
			@RequestParam(required=false) String startDate, //时间
			@RequestParam(required=false) String endDate,//时间
			@RequestParam(required=false) String province,//省份
			@RequestParam(required=false) String city,//城市
			@RequestParam(required=false) String area,//区域
			@RequestParam(required=false) String truckType,//类型
			@RequestParam(required=false) String verified,//是否审核通过
			@RequestParam(required=false) String addressType,//订单类型			
			HttpServletResponse response) throws Exception {
	

			//查询参数
			JSONObject objOrder = new JSONObject();
			objOrder.element("startDate", startDate);
			objOrder.element("endDate", endDate);
			objOrder.element("province", province);
			objOrder.element("city", city);
			objOrder.element("area", area);
			objOrder.element("truckType", truckType);
			objOrder.element("verified", true);
			objOrder.element("addressType", addressType);


			
			
			//***************订单数据开始******************/
			        HttpClient client = new HttpClient();   
			        //设置代理服务器地址和端口     
			        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
			        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
			        // HttpMethod method = new GetMethod("http://10.1.14.20:8088/workflowController/service/todo/addTask"); 
			        //使用POST方法
			        PostMethod method = new PostMethod(ORDERMAPDATAREQADDRESS); 
			     
		            String transJson = objOrder.toString();
		            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
		            method.setRequestEntity(se);
		            //使用系统提供的默认的恢复策略
		            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		            //设置超时的时间
		            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
			    	 
			     
		            client.executeMethod(method);
			        //打印服务器返回的状态
				     System.out.println(method.getStatusLine()+"订单查询ok");
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
			   //***************订单数据结束******************/  
			     
				//***************司机数据开始******************/


					JSONObject objTruck = new JSONObject();
					objTruck.element("startDate", startDate);
					objTruck.element("endDate", endDate);
					objTruck.element("province", province);
					objTruck.element("city", city);
					objTruck.element("area", area);
					objTruck.element("truckType", truckType);
					objTruck.element("verified", true);
					objTruck.element("addressType", "当前地");
			        //设置代理服务器地址和端口     
			        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
			        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
			        // HttpMethod method = new GetMethod("http://10.1.14.20:8088/workflowController/service/todo/addTask"); 
			        //使用POST方法
			        PostMethod methodTruck = new PostMethod(CAPACITYMAPDATAREQADDRESS); 
		            String transTruckJson = objTruck.toString();
		            RequestEntity seTruck = new StringRequestEntity(transTruckJson, "application/json", "UTF-8");			     
			        methodTruck.setRequestEntity(seTruck);
		            //使用系统提供的默认的恢复策略
			        methodTruck.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		            //设置超时的时间
			        methodTruck.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
			    	 
			     
		            client.executeMethod(methodTruck);
			        //打印服务器返回的状态
				     System.out.println(methodTruck.getStatusLine()+"司机查询ok");
				       //打印返回的信息
				     System.out.println();
				     InputStream streamTruck = methodTruck.getResponseBodyAsStream();
				     
				     BufferedReader brTruck = new BufferedReader(new InputStreamReader(streamTruck, "UTF-8"));
				     StringBuffer bufTruck = new StringBuffer();
				     String lineTruck;
				     while (null != (lineTruck = brTruck.readLine())) {
				         bufTruck.append(lineTruck).append("\n");
				     }
				       //释放连接
				     methodTruck.releaseConnection();	            
		     //***************司机数据结束******************/ 			     
			     
			     

			   


			
				List<Map<String, Object>> mapList = Lists.newArrayList();
				Map<String, Object> map = Maps.newHashMap();
				
				//根据条件查询司机  名称 状态 类型 车型类型
				//List<OrderNode> loadNodes = orderNodeService.getLoadNodeList(startTime,endTime,reqBigenTime,reqEndTime,city,status);
				JsonMapper jm = new JsonMapper();
				map.put("rsOrder", jm.toJson(buf.toString()));
				map.put("rsTruck", jm.toJson(bufTruck.toString()));
				
				map.put("status", Boolean.TRUE);
				mapList.add(map);
				return mapList;
	}	

}