package org.hbhk.aili.common.server.dao;

import java.util.Map;

import org.hbhk.aili.base.share.entity.Client;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;

public interface IClientDao extends IBaseDao<Client, String> {

	int updateFilePath(Map<String, Object> params);

}
