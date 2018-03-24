package com.yimidida.ows.user.server.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.share.constants.UserConstants;
import com.yimidida.ows.base.share.entity.IUserMenu;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.user.server.dao.IFunctionDao;
import com.yimidida.ows.user.share.vo.MenuVo;
import com.yimidida.ymdp.cache.server.CacheSupport;

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
		//menus = dealUserMenu(menus);
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
                        List<IUserMenu> myChildrens = new ArrayList<IUserMenu>();
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
	
	
	@Override
	public Integer getExpire() {
		return 60;
	}

}
