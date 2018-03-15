package org.hbhk.hls.user.server.cache;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.constants.UserConstants;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.hls.user.share.vo.CurrentUserVo;
import org.hbhk.hls.user.share.vo.MenuVo;


public class InvalidateAuthCache {
	
	
	public static  void  invalidateUserCache(String userCode){
		if(StringUtils.isEmpty(userCode)){
			return;
		}
			
		ICache<String, List<MenuVo>> menuCache = CacheManager.getInstance().getCache(UserConstants.USER_MENU_LIST_CACHE_ID);
		ICache<String, CurrentUserVo>  userCache =  CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID);
		menuCache.invalid(userCode);
		
		userCache.invalid(userCode);
	}
	
}
