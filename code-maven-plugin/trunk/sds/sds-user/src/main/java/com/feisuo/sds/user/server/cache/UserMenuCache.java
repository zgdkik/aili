package com.feisuo.sds.user.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hbhk.aili.cache.server.CacheSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.constants.UserConstants;
import com.feisuo.sds.user.server.dao.IFunctionDao;
import com.feisuo.sds.user.share.vo.MenuVo;

@Component
public class UserMenuCache extends CacheSupport<List<MenuVo>> {

	@Autowired
	private IFunctionDao functionDao;

	@Override
	public String getCacheId() {
		return UserConstants.USER_MENU_LIST_CACHE_ID;
	}

	@Override
	public List<MenuVo> doSet(String key) {
		Set<String> accessUris = UserContext.getCurrentUser().queryAccessUris();
		if (accessUris == null) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", "0");
		List<MenuVo> menus = functionDao.getMenuTree(params);
		dealUserMenu(menus, accessUris);
		return menus;
	}

	private void dealUserMenu( List<MenuVo> menus,
			Set<String> accessUris) {
		if(menus==null || menus.size()==0){
			return;
		}
		for (int i = 0; i < menus.size(); i++) {
			MenuVo menu = menus.get(i);
			String url = menu.getUrl();
			if(!accessUris.contains(url)){
				menus.remove(i);
				i=i-1;
				continue;
			}
			menu.traverse(accessUris);
		}
	}

}
