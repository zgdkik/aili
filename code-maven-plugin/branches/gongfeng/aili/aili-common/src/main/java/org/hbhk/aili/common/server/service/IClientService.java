package org.hbhk.aili.common.server.service;

import java.util.Map;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.base.share.entity.Client;

public interface IClientService extends IBaseService<Client, String> {

	
	void updateFilePath(Map<String, Object> params);
}
