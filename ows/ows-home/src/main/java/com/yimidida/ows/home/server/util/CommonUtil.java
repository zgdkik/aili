package com.yimidida.ows.home.server.util;

import java.util.Map;

import net.sf.json.JSONObject;

public class CommonUtil {
	public static Map<String, Object> jsonToMap(String jsonStr){
		 JSONObject  jasonObject = JSONObject.fromObject(jsonStr);

		 Map<String, Object> map = (Map)jasonObject;
		 
		 return map;
	}
	
}
