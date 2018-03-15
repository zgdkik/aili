package com.feisuo.sds.user.server.cache;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;

import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.base.share.constants.UserConstants;
import com.feisuo.sds.user.share.vo.CurrentUserVo;
import com.feisuo.sds.user.share.vo.MenuVo;


public class InvalidateAuthCache {
	
	
	public static  void  invalidateUserCache(String userCode){
		if(StringUtils.isEmpty(userCode)){
			return;
		}
		ICache<String, List<MenuVo>> menuCache = CacheManager.getInstance().getCache(UserConstants.USER_MENU_LIST_CACHE_ID);
		menuCache.invalid(userCode);
		ICache<String, CurrentUserVo>  userCache =  CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID);
		userCache.invalid(userCode);
	}
	
}
