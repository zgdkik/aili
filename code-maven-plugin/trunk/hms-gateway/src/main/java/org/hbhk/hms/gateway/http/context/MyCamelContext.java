package org.hbhk.hms.gateway.http.context;

import java.net.URI;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.camel.component.servlet.DefaultHttpRegistry;
import org.apache.camel.component.servlet.HttpRegistry;
import org.apache.camel.http.common.CamelServlet;
import org.apache.camel.http.common.HttpConsumer;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.InitializingBean;

public class MyCamelContext implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		
		CamelContext camelContext = new DefaultCamelContext();
		
		camelContext.addRoutes(new RouteBuilder() {
			public void configure() {
				from("servlet://hbhk").to("https://www.baidu.com");
			}
		});
		CamelServlet camelServlet = new CamelServlet();
		URI httpURI = new URI("https://www.baidu.com");
		HttpEndpoint endpoint = new HttpEndpoint("servlet://hbhk", null,httpURI);
		HttpConsumer consumer = new HttpConsumer(endpoint, null);
		camelServlet.connect(consumer);
		HttpRegistry httpRegistry  = new DefaultHttpRegistry();
		httpRegistry.register(camelServlet);
		//HttpConsumer
		//camelContext.ad
	}

}
