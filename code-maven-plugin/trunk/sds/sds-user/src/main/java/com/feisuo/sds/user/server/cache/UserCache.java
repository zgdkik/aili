package com.feisuo.sds.user.server.cache;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.cache.server.CacheSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feisuo.sds.base.server.filter.FrontendFilter;
import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.user.server.dao.IUserDao;
import com.feisuo.sds.user.share.vo.CurrentUserVo;

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
		List<CurrentUserVo> list = userDao.getCurrentUserList(key);
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
