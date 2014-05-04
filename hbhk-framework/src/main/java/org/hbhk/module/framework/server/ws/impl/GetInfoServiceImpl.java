package org.hbhk.module.framework.server.ws.impl;

import javax.jws.WebService;

import org.hbhk.module.framework.server.ws.IGetInfoService;

@WebService(endpointInterface = "org.hbhk.module.framework.server.ws.IGetInfoService")
public class GetInfoServiceImpl implements IGetInfoService {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public String getRetInfo(String name, int age) {
		return name + "" + age;
	}

}