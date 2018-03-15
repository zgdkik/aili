package org.hbhk.aili.rpc.server.hessian.service;

import org.hbhk.aili.rpc.server.hessian.IDemoHessianService;
import org.hbhk.aili.rpc.server.hessian.annotation.HessianRemoting;
@HessianRemoting
public class HessianDemoService implements IDemoHessianService{

	@Override
	public String getName(String str) {
		return "welcome:"+str;
	}

}
