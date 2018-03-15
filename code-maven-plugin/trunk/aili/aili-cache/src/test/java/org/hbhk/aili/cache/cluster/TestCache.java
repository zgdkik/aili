package org.hbhk.aili.cache.cluster;

import java.util.concurrent.TimeUnit;

import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.CacheSupportTest;
import org.hbhk.aili.cache.server.RoleInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class TestCache {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"redis3-cluster.xml");
		
		JedisCluster jedisCluster= (JedisCluster) context.getBean("jedisCluster");
		jedisCluster.set("t2", "hbhk1");
		

//		for (int i = 0; i < args.length; i++) {
//			System.out.println(jedisCluster.get("t2"));
//			TimeUnit.SECONDS.sleep(1);
//		}
		CacheSupportTest cacheSupportTest = (CacheSupportTest) context
				.getBean("cacheSupportTest"); 
		
		for (int i = 0; i < 10; i++) {
			try {
				RoleInfo sss = cacheSupportTest.get("hebo"+i);
				System.out.println(sss+"aaaaaaaaa"+i);
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.in.read();
		RoleInfo  cache = CacheManager.getInstance().getCache("hbhk","");
	}

}
