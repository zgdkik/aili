package org.hbhk.hms.gateway.http.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.http.common.HttpConsumer;
import org.apache.camel.http.common.HttpRestServletResolveConsumerStrategy;
import org.apache.camel.support.RestConsumerContextPathMatcher;
import org.hbhk.hms.gateway.http.processor.HttpServiceDiscovery;

public class DynHttpRestServletResolveConsumerStrategy extends
		HttpRestServletResolveConsumerStrategy {
	
	private Map<String, HttpConsumer> consumers ;

	public HttpConsumer resolve(HttpServletRequest request,
			Map<String, HttpConsumer> sysconsumers) {
//		if(1==1){
//			return super.resolve(request, sysconsumers);
//		}
		this.consumers = HttpServiceDiscovery.consumers;
		HttpConsumer answer = null;

		String path = request.getPathInfo();
		if (path == null) {
			return null;
		}
		String method = request.getMethod();
		if (method == null) {
			return null;
		}
		//TODO 动态添加消费者
		List<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>> paths = new ArrayList<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>>();
		for (final Map.Entry<String, HttpConsumer> entry : consumers.entrySet()) {
			paths.add(new RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>() {
				@Override
				public String getRestrictMethod() {
					return entry.getValue().getEndpoint()
							.getHttpMethodRestrict();
				}

				@Override
				public String getConsumerPath() {
					return entry.getValue().getPath();
				}

				@Override
				public HttpConsumer getConsumer() {
					return entry.getValue();
				}
			});
		}

		RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> best 
		= DynRestConsumerContextPathMatcher.matchBestPath(method, path, paths);
				//.matchBestPath(method, path, paths);
		if (best != null) {
			answer = best.getConsumer();
		}

		if (answer == null) {
			// fallback to default
			answer = super.resolve(request, consumers);
		}

		return answer;
	}

}
