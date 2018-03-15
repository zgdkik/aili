package org.hbhk.aili.route.http.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.hbhk.aili.route.common.IpMonitor;

/**
 * 負載均衡算法，輪詢法
 *
 */
public class LbRoundRobin {

	private static Map<String, List<String>> serverWeigthMap = new LinkedHashMap<String, List<String>>();
	private static Map<String, Integer> posMap = new HashMap<String, Integer>();
	public static Set<String> appMonitor = new HashSet<String>();

	private static ExecutorService service = Executors.newCachedThreadPool();
	
	public static void monitor(final String app){
		if(appMonitor.contains(app)){
			return;
		}
		service.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					List<String> ips =  serverWeigthMap.get(app);
					if(ips!=null && ips.size()>0){
						for (String ip : ips) {
							boolean result = IpMonitor.isHostConnectable(ip);
							if(!result){
								ips.remove(ip);
								System.out.println("应用:"+app+"主机:"+ip+"出现异常");
								posMap.put(app, 0);
							}
						}
					}else{
						DubboSubscribeChanges.disconnectByApp(app);
					}
					try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
	}

	public static void add(String app, String ip) {
		if (serverWeigthMap.containsKey(app)) {
			if (!serverWeigthMap.get(app).contains(ip)) {
				serverWeigthMap.get(app).add(ip);
			}
		} else {
			List<String> ips = new ArrayList<String>();
			serverWeigthMap.put(app, ips);
		}
	}

	
	public static String roundRobin(String app) {
		monitor(app);
		if (posMap.containsKey(app)) {
			posMap.put(app, posMap.get(app));
		} else {
			posMap.put(app, 0);
		}
		Integer pos = posMap.get(app);
		// 重新建立一個map,避免出現由於服務器上線和下線導致的並發問題
		Map<String, List<String>> serverMap = new LinkedHashMap<String, List<String>>();
		serverMap.putAll(serverWeigthMap);
		// 獲取ip列表list
		List<String> keySet = serverMap.get(app);
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		String server = null;
		synchronized (pos) {
			if (pos >= keySet.size()) {
				pos = 0;
			}
			server = keyList.get(pos);
			posMap.put(app, pos + 1);
		}
		return server;
	}

	public static void main(String[] args) {
		List<String> ips = new ArrayList<String>();
		ips.add("192.168.1.12");
		ips.add("192.168.1.13");
		ips.add("192.168.1.14");
		ips.add("192.168.1.15");
		ips.add("192.168.1.16");
		ips.add("192.168.1.17");
		ips.add("192.168.1.18");
		ips.add("192.168.1.19");
		for (String ip : ips) {
			add("hbhk", ip);
			add("hbhk1", ip);
		}
		for (int i = 0; i < 100; i++) {
			String serverIp = roundRobin("hbhk");
			System.out.println(serverIp);
			System.out.println( roundRobin("hbhk1"));
		}
	}
}
