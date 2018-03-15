package org.hbhk.aili.route.http.processor;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class DubboSubscribeNodeChanges implements IZkChildListener{
	private ZkClient zkClient;


	public DubboSubscribeNodeChanges(ZkClient zkClient){
		 this.zkClient = zkClient;
	}
	@Override
	public void handleChildChange(String parentPath, List<String> currentChilds){
		System.out.println("dubbo node change");
		DubboSubscribeChanges dsc = new DubboSubscribeChanges(zkClient);
		try {
			dsc.handleDataChange("/dubbo", "system");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
