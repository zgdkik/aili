package org.hbhk.aili.security.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.core.server.data.AiliSqlSessionDaoSupport;
import org.hbhk.aili.security.server.dao.IRoleDao;
import org.hbhk.aili.security.share.pojo.RoleInfo;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AiliSqlSessionDaoSupport implements IRoleDao {
	private final String NAMESPACE = "aili.role.";

	@Override
	public RoleInfo getRole(String code) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		List<RoleInfo> userInfos = getSqlSession().selectList(
				NAMESPACE + "getMe", parameter);
		if (CollectionUtils.isEmpty(userInfos)) {
			return null;
		}
		return userInfos.get(0);
	}

}
