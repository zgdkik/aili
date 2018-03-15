package org.hbhk.aili.rpc.server.test.thrift;

import org.apache.thrift.TException;
import org.hbhk.aili.rpc.server.test.thrift.ThriftService.Iface;

public class ThriftServiceImpl  implements Iface {

	@Override
	public int add(int a, int b) throws TException {
		return a+b;
	}

}
