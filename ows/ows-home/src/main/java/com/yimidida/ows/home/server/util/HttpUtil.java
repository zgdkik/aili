package com.yimidida.ows.home.server.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	
	public  static <T> T sendPost(String url,MultiValueMap<String, Object> param ,Class<T> cls){
		RestTemplate restTemplate = new RestTemplate();
		
		return  restTemplate.postForObject(url, param,cls);
	}
}
