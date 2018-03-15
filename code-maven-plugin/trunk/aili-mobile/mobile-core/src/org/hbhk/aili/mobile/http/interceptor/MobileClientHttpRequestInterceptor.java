package org.hbhk.aili.mobile.http.interceptor;

import java.io.IOException;

import org.hbhk.aili.mobile.context.UserContext;
import org.hbhk.aili.mobile.util.DesUtil;
import org.hbhk.aili.mobile.util.JsonUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

public class MobileClientHttpRequestInterceptor implements
		ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		String json = JsonUtil.toJson(UserContext.userContext);
		json = new DesUtil("hbhk").encypt(json);
		requestWrapper.getHeaders().add(UserContext.USER_CODE,json);
		return execution.execute(requestWrapper, body);
	}

}
