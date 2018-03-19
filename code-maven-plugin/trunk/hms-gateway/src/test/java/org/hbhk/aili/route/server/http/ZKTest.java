package org.hbhk.aili.route.server.http;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class ZKTest {


	public static void main(String[] args) {

		String zkHost = "139.196.180.16:2181";
		ZkClient zkClient = new ZkClient(zkHost, 25000);
		zkClient.setZkSerializer(new ZkSerializer() {
			public byte[] serialize(Object data) throws ZkMarshallingError {
				return data.toString().getBytes();
			}

			public Object deserialize(byte[] bytes) throws ZkMarshallingError {
				return new String(bytes);
			}
		});
		zkClient.createEphemeral("/test1/asd");
		//zkClient.writeData("/test1", "dsaff");
	}

}
