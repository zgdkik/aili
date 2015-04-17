package com.baozun.zk;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKClientTest {
	public static final Logger log = LoggerFactory
			.getLogger(ZKClientTest.class);
	private static ZkClient zkClient;

	
	public static void main(String[] args) {
		zkClient = new ZkClient("10.8.12.186");
		//zkClient.createPersistent("/hbhk2",true);
		String node = "/applife/app";
		boolean a = zkClient.exists(node);
		if (a) {
			zkClient.readData(node);
		}else{
			System.out.println(node);
		}

	}
}
