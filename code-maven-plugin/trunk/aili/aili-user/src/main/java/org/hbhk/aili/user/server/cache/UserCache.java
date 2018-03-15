package org.hbhk.aili.user.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.base.server.filter.FrontendFilter;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.user.server.dao.IUserDao;
import org.hbhk.aili.user.share.vo.CurrentUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCache extends CacheSupport<CurrentUserVo> {

	private static Log log =LogFactory.getLog(FrontendFilter.class);
	@Autowired
	private IUserDao userDao;


	public String getCacheId() {
		return FrontendConstants.USER_CACHE_UUID;
	}

	@Override
	public CurrentUserVo doSet(String key) {
		log.info("获取当前用户信息开始:"+key);
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", key);
		List<CurrentUserVo> list = userDao.getCurrentUserList(params);
		if (list == null || list.isEmpty()) {
			return null;
		}
		CurrentUserVo user = list.get(0);
		// 判断用户类型
		log.info("获取当前用户信息结束:"+key);
		return user;
	}

	@Override
	public Integer getExpire() {
		return 60*6;
	}
}
