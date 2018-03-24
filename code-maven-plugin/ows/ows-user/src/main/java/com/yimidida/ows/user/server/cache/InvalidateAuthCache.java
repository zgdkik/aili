package com.yimidida.ows.user.server.cache;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.constants.UserConstants;
import com.yimidida.ows.user.share.vo.CurrentUserVo;
import com.yimidida.ows.user.share.vo.MenuVo;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.cache.server.ICache;


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
