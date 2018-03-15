package com.thinkgem.jeesite.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

 
public class HttpPostJson{




	public static void main(String[] args) {
		String url = "http://192.168.20.165:9999/CustomService.svc/GetTruckListResult";
		JSONObject obj = new JSONObject();
		obj.element("startDate", "2016-01-13 16:29:57");
		obj.element("endDtate", "2016-01-14 16:29:57");
		obj.element("province", "上海市");
		obj.element("city", "上海市");
		obj.element("area", "");
		obj.element("truckType", "");
		obj.element("verified", false);
		HttpPostJson httpPostJson= new HttpPostJson();
		
		try {
        HttpClient client = new HttpClient();   
        //设置代理服务器地址和端口     
     //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
       // HttpMethod method = new GetMethod("http://10.1.14.20:8088/workflowController/service/todo/addTask"); 
        //使用POST方法
        PostMethod method = new PostMethod(url); 
     
        String transJson = obj.toString();
        RequestEntity se;
	
			se = new StringRequestEntity(transJson, "application/json", "UTF-8");
		
        method.setRequestEntity(se);
        //使用系统提供的默认的恢复策略
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //设置超时的时间
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
    	 
     
     client.executeMethod(method);
        //打印服务器返回的状态
     System.out.println(method.getStatusLine());
       //打印返回的信息
     System.out.println();
     InputStream stream = method.getResponseBodyAsStream();
     
     BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
     StringBuffer buf = new StringBuffer();
     String line;
     while (null != (line = br.readLine())) {
         buf.append(line).append("\n");
     }
     System.out.println(buf.toString());
       //释放连接
     method.releaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	
	public HttpMethodBase createMethod(String url,  JSONObject jsonObject,int timeout) {
		        PostMethod method = null;
		        try {
		            method = new PostMethod(url);
		 
		            String transJson = jsonObject.toString();
		            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
		            method.setRequestEntity(se);
		            //使用系统提供的默认的恢复策略
		            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		            //设置超时的时间
		            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
		        } catch (IllegalArgumentException e) {
		        	e.printStackTrace();
		        } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		        }
		 
		        return method;
		}

	public String postJson(String postUrl, JSONObject jsonObj)
			throws Exception {
		try {
			// 创建连接
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(jsonObj.toString());
			out.flush();
			out.close();

			
			
			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes("utf-8"), "utf-8");
				sb.append(lines);
			}
			reader.close();
			
			
			// 断开连接
			connection.disconnect();
			return sb.toString();
		} catch (MalformedURLException e) {
			throw new Exception(e); 
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e); 
		} catch (IOException e) {
			throw new Exception(e); 
		}

	}

	

}