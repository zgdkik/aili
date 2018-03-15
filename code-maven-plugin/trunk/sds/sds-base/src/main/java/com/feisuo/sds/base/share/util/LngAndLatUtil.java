package com.feisuo.sds.base.share.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.feisuo.sds.base.share.entity.LngAndLat;

public class LngAndLatUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestfulClient.class);

	public static LngAndLat getLngAndLat(String address) {
		LngAndLat map = new LngAndLat();
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&ak=1rENYOIqG1RIMwnfH5uHS1o9";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.parseObject(json);
		if (obj.get("status").toString().equals("0")) {
			double lng = obj.getJSONObject("result").getJSONObject("location")
					.getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location")
					.getDouble("lat");
			map.setLng(lng);
			map.setLat(lat);
			LOGGER.info("经度：" + lng + "---纬度：" + lat);
		} else {
			LOGGER.info("未找到相匹配的经纬度！");
			return null;
		}
		return map;
	}

	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}
}