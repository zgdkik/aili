package org.hbhk.hms.gateway.http.processor;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;

import org.apache.camel.Endpoint;
import org.apache.camel.component.servlet.ServletComponent;
import org.apache.camel.component.servlet.ServletEndpoint;
import org.apache.camel.http.common.HttpConsumer;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.hbhk.hms.gateway.http.MappingUrlContext;
import org.hbhk.hms.gateway.http.context.DynamicCamelHttpTransportServlet;
import org.hbhk.hms.mq.rabbit.MqConstant;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * http 服务发现
 */
public class HttpServiceDiscovery implements InitializingBean, DisposableBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServiceDiscovery.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private volatile List<String> dataList = new ArrayList<>();

    private String registryAddress;
    private ZooKeeper zookeeper;
    
	public static String confirmUrl = "servlet:/route";
	private ExecutorService service = Executors.newCachedThreadPool();
	private static boolean sc = true;
	
	private MappingUrlContext mappingUrl;
	
	private static Map<String, Set<String>> appServerMap = new LinkedHashMap<String, Set<String>>();

	public static Map<String, String>  gatewayUrls = new LinkedHashMap<String, String>();
	public static Map<String, HttpConsumer> consumers  = new ConcurrentHashMap<String, HttpConsumer>();
    @Override
	public void afterPropertiesSet() throws Exception {
    	service.execute(new Runnable() {
			@Override
			public void run() {
				 zookeeper = connectServer();
		         if (zookeeper != null) {
		             watchNode(zookeeper);
		         }
			}
		});
	}
    public String discover() {
        String data = null;
        int size = dataList.size();
        if (size > 0) {
            if (size == 1) {
                data = dataList.get(0);
                LOGGER.debug("using only data: {}", data);
            } else {
                data = dataList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("using random data: {}", data);
            }
        }
        return data;
    }

    private ZooKeeper connectServer() {
        try {
        	if(zookeeper!=null){
        		return zookeeper;
        	}
        	zookeeper = new ZooKeeper(registryAddress, MqConstant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
        }
        return zookeeper;
    }

    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(MqConstant.ZK_REGISTRY_HTTP_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);
                    }
                }
            });
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                byte[] bytes = zk.getData(MqConstant.ZK_REGISTRY_HTTP_PATH + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            LOGGER.debug("node data: {}", dataList);
            this.dataList = dataList;

            LOGGER.debug("Service discovery triggered updating connected server node.");
            updateConnectedServer();
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("", e);
        }
    }

    private synchronized void updateConnectedServer(){
    	//刷新http服務配置
    	if(mappingUrl!=null){
 		   String app =mappingUrl.getApp();
 		   Map<String, String> urlMap = mappingUrl.getUrlMap();
 		   Set<String> mapping =  urlMap.keySet();
 		   for (String key : mapping) {
 			   String val=  urlMap.get(key);
 			   if(!key.startsWith("/")){
 				  key = "/"+key;
 			   }
 			   gatewayUrls.put(key,val);
 			   addHttpConsumer(app,key, null);
 		   }
 		}
    	if(dataList.isEmpty()){
    		Set<String> apps =  appServerMap.keySet();
    		for (String app : apps) {
    			removeHttpConsumer(app);
			}
    		appServerMap.clear();
    		return ;
    	}
		for (String data : dataList) {
    		LOGGER.info("discovery rest service :"+data);
			String[] arr = data.split(";");
			if(arr.length == 3){
				String app = arr[0];
				String ip = arr[1];
				String url  = arr[2];
				String contextPath = url.split("/")[1];
				addHttpConsumer(contextPath, url, ip);
			}
		}
		
    	
    }
    public static void removeHttpConsumer(String app) {
    	DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		Set<String> keys= hcMap.keySet();
		for (String key : keys) {
			HttpConsumer consumer = hcMap.get(key);
			Endpoint endpoint = consumer.getEndpoint();
			if(endpoint instanceof ServletEndpoint){
				ServletEndpoint servletEndpoint = (ServletEndpoint) endpoint;
				String contextPath =  servletEndpoint.getContextPath();
				if(app.equals(contextPath)){
					dchts.disconnect(consumer);
				}
			}
		}
    }
    public static void addHttpConsumer(String app,
			String url,String ip) {
		DynamicCamelHttpTransportServlet dchts = DynamicCamelHttpTransportServlet.dchts;
		ServletConfig config = DynamicCamelHttpTransportServlet.config;
		if (config == null || dchts == null) {
			while (sc) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				config = DynamicCamelHttpTransportServlet.config;
				dchts = DynamicCamelHttpTransportServlet.dchts;
				if (config != null && dchts != null) {
					sc = false;
				}
			}
		}
		Map<String, HttpConsumer> hcMap = dchts.getConsumers();
		HttpConsumer hcCom = hcMap.get(confirmUrl);
	    Set<String> consumerkeys = hcMap.keySet();
		try {
			String servletName = config.getServletName();
			URI httpURI = new URI(url);
			String path = "servlet:" + url;
			if(consumerkeys.contains(path)){
	    		LOGGER.info("rest service  registed:"+url);
				return ;
			}
			if(appServerMap.containsKey(app)){
				appServerMap.get(app).add(url);
			}else{
				Set<String>  restServices = new HashSet<String>();
				restServices.add(url);
				appServerMap.put(app, restServices);
			}
			ServletEndpoint endpoint = new ServletEndpoint(path,
					(ServletComponent) hcCom.getEndpoint().getComponent(),
					httpURI);
			endpoint.setContextPath(app);
			endpoint.setServletName(servletName);
			HttpConsumer consumer = new HttpConsumer(endpoint,
					hcCom.getProcessor());
			dchts.connect(consumer);
			if(StringUtils.isNotEmpty(ip)){
				LbRoundRobin.add(app, ip);
			}
			Map<String, HttpConsumer> consumerMap = dchts.getConsumers();
			Set<String> keys = consumerMap.keySet();
			for (String key : keys) {
				consumers.put(key, consumerMap.get(key));
			}
			LOGGER.info("" + dchts.getConsumers().keySet());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    public void stop(){
        if(zookeeper!=null){
            try {
                zookeeper.close();
            } catch (InterruptedException e) {
                LOGGER.error("", e);
            }
        }
    }
	public String getRegistryAddress() {
		return registryAddress;
	}
	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}
	
	
	@Override
	public void destroy() throws Exception {
		stop();
		
	}
	public MappingUrlContext getMappingUrl() {
		return mappingUrl;
	}
	public void setMappingUrl(MappingUrlContext mappingUrl) {
		this.mappingUrl = mappingUrl;
	}

	
}
