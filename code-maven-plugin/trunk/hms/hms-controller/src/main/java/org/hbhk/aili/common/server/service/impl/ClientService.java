package org.hbhk.aili.common.server.service.impl;

import org.hbhk.aili.common.server.service.IClientService;
import org.hbhk.aili.rest.server.service.IUserService;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class ClientService implements IClientService{

	@Reference
	private IUserService userService;
	
	public String test() {
		System.out.println(userService.registerUser());
		return "";
	}
}
