package org.hbhk.aili.map.share.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.hbhk.aili.map.share.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class RestfulClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestfulClient.class);

	private static RestTemplate restTemplate;

	private static List<HttpMessageConverter<?>> messageConverters = null;
	private static HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = null;

	private RestfulClient() {
		// 长连接保持30秒
		PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(
				30, TimeUnit.SECONDS);
		// 总连接数
		pollingConnectionManager.setMaxTotal(1000);
		// 同路由的并发数
		pollingConnectionManager.setDefaultMaxPerRoute(1000);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(pollingConnectionManager);
		// 重试次数，默认是3次，没有开启
		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2,
				true));
		// 保持长连接配置，需要在头添加Keep-Alive
		httpClientBuilder
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

		RequestConfig.Builder builder = RequestConfig.custom();
		builder.setConnectionRequestTimeout(200);
		builder.setConnectTimeout(5000);
		builder.setSocketTimeout(5000);

		RequestConfig requestConfig = builder.build();
		httpClientBuilder.setDefaultRequestConfig(requestConfig);

		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
		headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
		headers.add(new BasicHeader("Accept-Language", "zh-CN"));
		headers.add(new BasicHeader("Connection", "Keep-Alive"));
		httpClientBuilder.setDefaultHeaders(headers);
		HttpClient httpClient = httpClientBuilder.build();

		// httpClient连接配置，底层是配置RequestConfig
		clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		// 连接超时
		clientHttpRequestFactory.setConnectTimeout(5000);
		// 数据读取超时时间，即SocketTimeout
		clientHttpRequestFactory.setReadTimeout(5000);
		// 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
		// clientHttpRequestFactory.setConnectionRequestTimeout(200);
		// 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。

		// 添加内容转换器
		messageConverters = new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter(Charset
				.forName("UTF-8")));
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		restTemplate.setRequestFactory(clientHttpRequestFactory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		LOGGER.info("RestClient初始化完成");

	}

	public static RestTemplate getClient() {
		if (restTemplate == null) {
			new RestfulClient();
		}
		return restTemplate = new RestTemplate();
	}

	public static ResultEntity post(String url, Object obj) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> param = BeanToMapUtil.convert(obj);
		MultiValueMap<String, Object> requestParam = new LinkedMultiValueMap<String, Object>();
		requestParam.add("ak", "1rENYOIqG1RIMwnfH5uHS1o9");
		requestParam.add("service_id", 110814);
		if (param != null) {
			for (String key : param.keySet()) {
				requestParam.add(key, param.get(key));
			}
		}
		String result = restTemplate.postForObject(url, requestParam,
				String.class);
		return JsonUtil.parseJson(result, ResultEntity.class);
	}
}
