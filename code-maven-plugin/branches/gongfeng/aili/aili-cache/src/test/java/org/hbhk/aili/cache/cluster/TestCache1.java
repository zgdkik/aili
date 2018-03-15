package org.hbhk.aili.cache.cluster;

import java.util.concurrent.TimeUnit;

import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.CacheSupportTest;
import org.hbhk.aili.cache.server.RoleInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class TestCache1 {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"redis2-cluster.xml");

		CacheSupportTest cacheSupportTest = (CacheSupportTest) context
				.getBean("cacheSupportTest");
		for (int i = 0; i < 10; i++) {
			RoleInfo sss = cacheSupportTest.get("hebo" + i); 
			System.out.println(sss + "aaaaaaaaa" + i); 
			TimeUnit.SECONDS.sleep(1);
		}
		System.in.read();
		RoleInfo cache = CacheManager.getInstance().getCache("hbhk", "");
	}

}
