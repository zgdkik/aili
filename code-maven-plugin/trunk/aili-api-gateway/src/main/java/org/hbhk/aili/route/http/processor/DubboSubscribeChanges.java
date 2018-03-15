package org.hbhk.aili.route.http.processor;

import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.camel.component.servlet.ServletComponent;
import org.apache.camel.component.servlet.ServletEndpoint;
import org.apache.camel.http.common.HttpConsumer;
import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.route.http.bean.HttpAppInfo;
import org.hbhk.aili.route.http.context.DynamicCamelHttpTransportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubboSubscribeChanges implements IZkDataListener {

	private ZkClient zkClient;

	List<String> dubboUrl = new ArrayList<String>();

	public static String confirmUrl = "servlet:/route";
	Map<String, List<HttpAppInfo>> appUrlLbMap = new HashMap<String, List<HttpAppInfo>>();

	private ExecutorService service = Executors.newCachedThreadPool();
	private static boolean sc = true;

	private static Logger LOG = LoggerFactory
			.getLogger(DynamicCamelHttpTransportServlet.class);

	public DubboSubscribeChanges(ZkClient zkClient) {
		this.zkClient = zkClient;
		try {
			handleDataChange("/dubbo", "system");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loadTrees(List<String> chs, String pt) {
		for (int i = 0; chs != null && i < chs.size(); i++) {
			String p = chs.get(i);
			String newPath = pt + "/" + p;
			try {
				String url = URLDecoder.decode(newPath, "UTF-8");
				System.out.println(url);
				if (url.indexOf("http:") > -1) {
					System.out.println("匹配url:" + url);
					dubboUrl.add(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String> chs1 = zkClient.getChildren(newPath);
			if (chs1 != null && chs1.size() > 0) {
				loadTrees(chs1, pt + "/" + p);
			}
		}
	}
	public static void disconnectByApp(String app){
		DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
		ServletConfig config = DynamicCamelHttpTransportServlet.config;
		if (config == null) {
			while (sc) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				config = DynamicCamelHttpTransportServlet.config;
				if (config != null) {
					sc = false;
				}
			}
		}
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		Set<String> hcs =  hcMap.keySet();
		for (String hc : hcs) {
			if (hc.startsWith("servlet:/" + app)) {
				dchts.disconnect(hcMap.get(hc));
			}
		}
	}
	
	public static void addApiUrlp(String app){
		DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
		ServletConfig config = DynamicCamelHttpTransportServlet.config;
		if (config == null) {
			while (sc) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				config = DynamicCamelHttpTransportServlet.config;
				if (config != null) {
					sc = false;
				}
			}
		}
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		Set<String> hcs =  hcMap.keySet();
		for (String hc : hcs) {
			if (hc.startsWith("servlet:/" + app)) {
				dchts.disconnect(hcMap.get(hc));
			}
		}
	}
	@Override
	public void handleDataChange(String dataPath, Object data) throws Exception {
		System.out.println("dubbo data change");
		service.execute(new Runnable() {
			@Override
			public void run() {
				String dubbo = "/dubbo";
				List<String> chs = zkClient.getChildren(dubbo);
				loadTrees(chs, dubbo);
				DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
				ServletConfig config = DynamicCamelHttpTransportServlet.config;
				if (config == null) {
					while (sc) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						config = DynamicCamelHttpTransportServlet.config;
						if (config != null) {
							sc = false;
						}
					}
				}

				Map<String, HttpConsumer> hcMap = dchts.getConsumers();

				if (dubboUrl.size() > 0) {
					// /dubbo/org.hbhk.aili.rest.server.service.IUserService/providers/
					//http://192.168.0.100:8080?
					//urls=/user1/register1&application=hbhk&timestamp=1481614436799
					String url = dubboUrl.get(0);
					String str = url.substring(url.lastIndexOf("providers/")
							+ "providers/".length(), url.length());
					String[] arr = str.split("\\?");
					arr = arr[1].split("&");
					arr = arr[1].split("=");
					if (hcMap != null) {
						Set<String> hcs = hcMap.keySet();
						for (String hc : hcs) {
							if (!hc.equals(confirmUrl)
									&& hc.startsWith("servlet:/" + arr[1])) {
								dchts.disconnect(hcMap.get(hc));
							}
						}
					}
				}
				for (String url : dubboUrl) {
					String str = url.substring(url.lastIndexOf("providers/")
							+ "providers/".length(), url.length());
					String[] arr = str.split("\\?");
					String cp = arr[0];
					System.out.println(arr[0] + " --- " + arr[1]);
					String ip = cp.substring(
							cp.indexOf("http://") + "http://".length(),
							cp.length());
					arr = arr[1].split("&");
					String[] us = arr[0].split("=")[1].split(",");
					String app = arr[1].split("=")[1];
					LbRoundRobin.add(app, ip);
					for (String u : us) {
						if (StringUtils.isNotEmpty(u)) {
							initHttpConsumer(config, dchts, cp + u, u, app);
						}
					}

				}
			}
		});

	}

	public void initHttpConsumer(ServletConfig config,
			DynamicCamelHttpTransportServlet dchts, String uri, String url,
			String app) {
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		HttpConsumer hcCom = hcMap.get(confirmUrl);
		try {
			String servletName = config.getServletName();
			URI httpURI = new URI("/" + app + url);
			String path = "servlet:/" + app + url;
			ServletEndpoint endpoint = new ServletEndpoint(path,
					(ServletComponent) hcCom.getEndpoint().getComponent(),
					httpURI);
			endpoint.setContextPath(app);
			endpoint.setServletName(servletName);

			HttpConsumer consumer = new HttpConsumer(endpoint,
					hcCom.getProcessor());
			dchts.connect(consumer);
			LOG.info("" + dchts.getConsumers().keySet());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void addHttpConsumer(String app,
			String url,String ip) {
		DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
		ServletConfig config = DynamicCamelHttpTransportServlet.config;
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		HttpConsumer hcCom = hcMap.get(confirmUrl);
		try {
			String servletName = config.getServletName();
			URI httpURI = new URI("/" + app + url);
			String path = "servlet:/" + app + url;
			ServletEndpoint endpoint = new ServletEndpoint(path,
					(ServletComponent) hcCom.getEndpoint().getComponent(),
					httpURI);
			endpoint.setContextPath(app);
			endpoint.setServletName(servletName);
			HttpConsumer consumer = new HttpConsumer(endpoint,
					hcCom.getProcessor());
			dchts.connect(consumer);
			LbRoundRobin.add(app, ip);
			LOG.info("" + dchts.getConsumers().keySet());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void handleDataDeleted(String dataPath) throws Exception {
		System.out.println("dubbo Deleted");

	}

	public static void main(String[] args) {
		String url = "/dubbo/org.hbhk.aili.rest.server.service.IOrderService/providers/"
				+ "rest://192.168.0.100:8080/org.hbhk.aili.rest.server.service.IOrderService?"
				+ "anyhost=true&application=hbhk&dubbo=2.8.4&generic=false&"
				+ "interface=org.hbhk.aili.rest.server.service.IOrderService&methods=save,"
				+ "get&pid=2920&side=provider&timestamp=1481278050407";
		String ip = url.substring(url.indexOf("rest://"), url.indexOf("?"));
		System.out.println();
	}
}
