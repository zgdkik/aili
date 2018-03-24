package com.yimidida.ows.user.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yimidida.ows.base.server.filter.FrontendFilter;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.user.server.dao.IUserDao;
import com.yimidida.ows.user.share.vo.CurrentUserVo;
import com.yimidida.ymdp.cache.server.CacheSupport;

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
		String[] arr = key.split(",");
		String compCode=arr[0];
		String userCode =arr[1];
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("compCode", compCode);
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
