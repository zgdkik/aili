package org.hbhk.aili.mobile.http;

import java.util.List;

import org.hbhk.aili.mobile.http.interceptor.MobileClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

public class MobileRestTemplate extends RestTemplate {

	@Override
	public List<ClientHttpRequestInterceptor> getInterceptors() {
		super.getInterceptors().add(new MobileClientHttpRequestInterceptor());
		return super.getInterceptors();
	}

}
