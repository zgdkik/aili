package com.feisuo.yhhc.module.bseservice_gis.server.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import net.sf.json.JSONObject;

 
public class TestHttpPostJson implements com.feisuo.yhhc.module.bseservice_gis.api.shared.util.HttpPostJson{




	public static void main(String[] args) {
		//String url = "http://yunpan.cn/cwIvQ6KGN6Ra7";
		String url = "http://s.360.cn/yunpan/webclick.html?u=http%3A%2F%2Fyunpan.cn%2Fsingle&id=173866086.4207444600806723000.1433944977726.2627&buttonid=Sharedown&t=1433948465560";
		JSONObject obj = new JSONObject();
		obj.element("userid", "83f85d02-22ef-4dc3-bb00-c622ab375846");
		TestHttpPostJson httpPostJson= new TestHttpPostJson();
		try {
			System.out.println(httpPostJson.postJson(url,obj));
			String resultString = httpPostJson.postJson(url,obj);
			String[] strArray = resultString.split("SYS_CONF =");
			
			
			StringBuffer sb = new  StringBuffer();
			sb.append("http://sdl29.yunpan.cn/share.php?method=Share.download");
			for (int i = 0;i<strArray.length;i++) {
				if(i!=0){
					JSONObject jsObj = JSONObject.fromObject(strArray[i].split(";    var monitorConfig = ")[0]);
					 Iterator it = jsObj.keys();  
			            while (it.hasNext()) {  
			                String key = (String) it.next();  
			                String value = jsObj.getString(key);  
			                sb.append("&"+key+"="+value);
			            }
					System.out.println(sb.toString());	
					}
			}
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
			connection.setRequestMethod("POST");
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