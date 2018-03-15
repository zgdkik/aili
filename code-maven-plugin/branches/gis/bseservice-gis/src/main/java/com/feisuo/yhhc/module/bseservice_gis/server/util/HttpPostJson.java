package com.feisuo.yhhc.module.bseservice_gis.server.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

 
public class HttpPostJson implements com.feisuo.yhhc.module.bseservice_gis.api.shared.util.HttpPostJson{




	public static void main(String[] args) {
		String url = "http://api.map.baidu.com/direction/v1/routematrix?output=json&origins=40.056878,116.30815&destinations=39.915285,116.403857&ak=hF4dbGT9jWPE9c4dboQQSM2f";
		JSONObject obj = new JSONObject();
		obj.element("userid", "83f85d02-22ef-4dc3-bb00-c622ab375846");
		HttpPostJson httpPostJson= new HttpPostJson();
		try {
			System.out.println(httpPostJson.postJson(url,obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			connection.setRequestMethod("GET");
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