package org.hbhk.aili.rpc.server.hessian.service;

import org.hbhk.aili.rpc.server.hessian.IHessianService;
import org.hbhk.aili.rpc.server.hessian.annotation.HessianService;
@HessianService
public class HessianDemoService implements IHessianService{

	@Override
	public String getName(String str) {
		return "welcome:"+str;
	}

}
