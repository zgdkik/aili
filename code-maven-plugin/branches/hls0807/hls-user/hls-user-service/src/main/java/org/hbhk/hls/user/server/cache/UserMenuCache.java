package org.hbhk.hls.user.server.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.constants.UserConstants;
import org.hbhk.aili.base.share.util.SystemParameterUtil;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.hls.user.server.dao.IFunctionDao;
import org.hbhk.hls.user.share.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		params.put("urls", accessUris);
		List<MenuVo> menus = functionDao.getUserMenuTree(params); ///functionDao.getMenuTree(params);
		menus = dealUserMenu(menus);
		return menus;
	}

	private List<MenuVo> dealUserMenu(List<MenuVo> menus) {
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		String appType = SystemParameterUtil.getValue("app.type");
	    for (MenuVo tree : menus) {
            if(appType.equals(tree.getParentId())){
            	menuList.add(tree);
            }
            for (MenuVo t : menus) {
                if(t.getParentId()!=null && t.getParentId().equals(tree.getAcl())){
                    if(tree.getChildList() == null){
                        List<MenuVo> myChildrens = new ArrayList<MenuVo>();
                        myChildrens.add(t);
                        tree.setChildList(myChildrens);
                    }else{
                        tree.getChildList().add(t);
                    }
                }
            }
        }
		return menuList;
	}
	
	
	public void dealUserMenu( List<MenuVo> menus,
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
	
	@Override
	public Integer getExpire() {
		return 60;
	}

}
