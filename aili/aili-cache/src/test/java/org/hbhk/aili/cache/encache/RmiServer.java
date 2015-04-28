package org.hbhk.aili.cache.encache;

import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.distribution.RMICachePeer;
import net.sf.ehcache.distribution.TransactionalRMICachePeer;

public class RmiServer {

	public static void main(String[] args) {
		try {
			InputStream input = RmiServer.class.getClassLoader().getResourceAsStream("ehcache.xml");
			Ehcache cache = CacheManager.create(input).getEhcache("serviceCache");
			RMICachePeer rmiCachePeer = new TransactionalRMICachePeer(cache, "localhost", 40001, 40002, 120000);
			// 注册通讯端口
			LocateRegistry.createRegistry(40000);
			// 注册通讯路径
			Naming.rebind("rmi://127.0.0.1:40000/serviceCache", rmiCachePeer);
			System.out.println("Service Start!");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
