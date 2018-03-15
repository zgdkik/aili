package org.hbhk.aili.route.http.processor;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.hbhk.aili.route.http.bean.HttpAppInfo;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ServiceConfigLoader implements IServiceConfigLoader,
		ApplicationListener<ApplicationEvent> {

	private String zkHost = "127.0.0.1:2181";
	private Integer timeout = 25000;
	private ZkClient zkClient;

	@Override
	public List<HttpAppInfo> loadALl() {
		return null;
	}

	@Override
	public String load(String url) {
		String[] urls = url.split("/");
		String app = urls[1];
		url = url.substring(url.indexOf("/" + app) + ("/" + app).length(),
				url.length());
		String str = "http://" + LbRoundRobin.roundRobin(urls[1]) + url;
		return str;
	}

	public static void main(String[] args) throws Exception {
		ServiceConfigLoader sc = new ServiceConfigLoader();
		sc.onApplicationEvent(null);
		System.in.read();
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// zkHost = "139.196.180.16:2181";
		zkClient = new ZkClient(zkHost, timeout);
		zkClient.setZkSerializer(new ZkSerializer() {
			public byte[] serialize(Object data) throws ZkMarshallingError {
				return data.toString().getBytes();
			}

			public Object deserialize(byte[] bytes) throws ZkMarshallingError {
				return new String(bytes);
			}
		});
		String dubboSub = "/dubboSub";
		String dubbo= "/dubbo";
		String str = zkClient.readData(dubboSub);
		System.out.println(str);
		zkClient.subscribeDataChanges(dubboSub, new DubboSubscribeChanges(
				zkClient));
		zkClient.subscribeChildChanges(dubbo, new DubboSubscribeNodeChanges(zkClient));

	}

	public String getZkHost() {
		return zkHost;
	}

	public void setZkHost(String zkHost) {
		this.zkHost = zkHost;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

	public void setZkClient(ZkClient zkClient) {
		this.zkClient = zkClient;
	}

}
