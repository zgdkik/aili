package org.hbhk.aili.route.jms.server.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.route.jms.server.component.IComponent;
import org.hbhk.aili.route.jms.server.component.impl.CamelComponents;
import org.hbhk.aili.route.jms.server.process.DefaultProcess;
import org.hbhk.aili.route.jms.server.route.IDynamicRouter;
import org.hbhk.aili.route.jms.server.route.impl.DefaultDynamicRouter;
import org.hbhk.aili.route.jms.share.ComponentInfo;
import org.hbhk.aili.route.jms.share.RouteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class JmsRoutelistener implements ServletContextListener {

	private static final CamelContext camelContext = new DefaultCamelContext();

	public static ApplicationContext springContext;

	private CamelComponents camelComponents;
	private Logger  log = LoggerFactory.getLogger(getClass());

	private static ExecutorService executorService = Executors.newFixedThreadPool(5);
	
	private IDynamicRouter dynamicRouter = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (springContext == null) {
			springContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sce.getServletContext());
		}
		if (camelComponents == null) {
			camelComponents = springContext.getBean("camelComponents",
					CamelComponents.class);
		}
		if (camelComponents != null) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						log.info("jms route starting ...");
						List<IComponent> components = camelComponents
								.getComponents();
						if (components != null && !components.isEmpty()) {
							for (IComponent cmt : components) {
								ComponentInfo ci = cmt.getComponent();
								String name = ci.getName();
								Component com = ci.getComponent();
								if (StringUtils.isNotEmpty(name) && com != null) {
									camelContext.addComponent(name, com);
								}
							}
						}
						List<RouteInfo>   routeList = new ArrayList<RouteInfo>();
						/**
						 * 1、校验路由
						 * 2、处理路由
						 * 3、异常路由
						 */
						for (RouteInfo rt : routeList) {
							String interfaceCode = rt.getInterfaceCode();
							String dynamicRouterName = rt.getDynamicRouter();
							dynamicRouter = springContext.getBean("dynamicRouterName",IDynamicRouter.class);
							String form = rt.getForm();
							String fromComponent = rt.getFromComponent();
							camelContext.addRoutes(new RouteBuilder() {
								public void configure() {
									from("jms:queue:hbhk1")
									.process(new DefaultProcess())
									//.to("jms:queue:hbhk2");
									.dynamicRouter(method(dynamicRouter, "route"))
									.process(new DefaultProcess())
									;
									
								}
							});
							camelContext.addRoutes(new RouteBuilder() {
								public void configure() {
									from("direct:normal")
									.process(new DefaultProcess())
									//.to("jms:queue:hbhk2");
									.dynamicRouter(method(dynamicRouter, "route"))
									.process(new DefaultProcess())
									;
									
								}
							});
							camelContext.addRoutes(new RouteBuilder() {
								public void configure() {
									from("direct:exception")
									.process(new DefaultProcess())
									//.to("jms:queue:hbhk2");
									.dynamicRouter(method(dynamicRouter, "route"))
									.process(new DefaultProcess())
									;
									
								}
							});
						}
						
						camelContext.start();
						log.info("jms route start end");
					} catch (Exception e) {
						log.error("jms route start error",e);
					}
				}

			});
		}
		while (true) {
			log.debug("jms route listener...");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				log.error("jms route start error",e);
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
