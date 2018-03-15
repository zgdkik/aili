package org.hbhk.aili.route.jms.server.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.route.jms.server.common.dynamicroute.ValidateDynamicRouter;
import org.hbhk.aili.route.jms.server.component.IComponent;
import org.hbhk.aili.route.jms.server.component.impl.CamelComponents;
import org.hbhk.aili.route.jms.server.process.DefaultAfterProcess;
import org.hbhk.aili.route.jms.server.process.DefaultBeforeProcess;
import org.hbhk.aili.route.jms.server.process.ExceptionProcessor;
import org.hbhk.aili.route.jms.server.route.IDynamicRouter;
import org.hbhk.aili.route.jms.server.route.impl.DefaultDynamicRouter;
import org.hbhk.aili.route.jms.share.ComponentInfo;
import org.hbhk.aili.route.jms.share.RouteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JmsRouteContext implements ApplicationContextAware,
		InitializingBean {

	private static final CamelContext camelContext = new DefaultCamelContext();

	public static ApplicationContext springContext;

	private CamelComponents camelComponents;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(5);

	private IDynamicRouter dynamicRouter = new DefaultDynamicRouter();

	private Processor beforeProcess = new DefaultBeforeProcess();

	private Processor afterProcess = new DefaultAfterProcess();

	public void afterPropertiesSet() throws Exception {
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
						List<RouteInfo> routeList = new ArrayList<RouteInfo>();
						/**
						 * 1、校验路由 2、处理路由 3、异常路由
						 */
						for (RouteInfo rt : routeList) {
							String dynamicRouterName = rt.getDynamicRouter();
							if (StringUtils.isNotEmpty(dynamicRouterName)) {
								dynamicRouter = springContext
										.getBean(dynamicRouterName,
												IDynamicRouter.class);
							}
							String bProcess = rt.getBeforeProcess();
							if (StringUtils.isNotEmpty(bProcess)) {
								beforeProcess = springContext.getBean(bProcess,
										Processor.class);
							}
							String aProcess = rt.getAfterProcess();
							if (StringUtils.isNotEmpty(aProcess)) {
								afterProcess = springContext.getBean(aProcess,
										Processor.class);
							}
							final String form = rt.getForm();
							final String fromComponent = rt.getFromComponent();
							camelContext.addRoutes(new RouteBuilder() {
								public void configure() {
									from(fromComponent + ":queue:" + form)
											
											.dynamicRouter(method(ValidateDynamicRouter.class,"route"))
											.process(afterProcess);

								}
							});
						}
						camelContext.addRoutes(new RouteBuilder() {
							public void configure() {
								from("direct:normal")
										.process(beforeProcess)
										.dynamicRouter(
												method(dynamicRouter, "route"))
										.process(afterProcess);

							}
						});
						camelContext.addRoutes(new RouteBuilder() {
							public void configure() {
								from("direct:exception")
										.process(new ExceptionProcessor());

							}
						});
						camelContext.start();
						log.info("jms route start end");
					} catch (Exception e) {
						log.error("jms route start error", e);
					}
				}

			});
		}
		while (true) {
			log.debug("jms route listener...");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				log.error("jms route start error", e);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		springContext = applicationContext;
	}

}
