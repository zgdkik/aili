package org.hbhk.aili.mobile.http;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpClient  {

	public void post(String url){
		RestTemplate restTemplate = new MobileRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		String result = restTemplate.getForObject(url, String.class, "Android");
	}
}
