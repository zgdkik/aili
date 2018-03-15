package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IThemeDao;
import org.hbhk.maikkr.user.server.service.IThemeService;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */
@Service
public class ThemeService implements IThemeService {

	@Autowired
	private IThemeDao themeDao;

	public ThemeInfo save(ThemeInfo theme) {
		theme.setCreateTime(new Date());
		theme.setCreatUser("admin");
		theme.setId(UUIDUitl.getUuid());
		return themeDao.save(theme);
	}

	public List<ThemeInfo> loadUserThemeType() {
		String cuser = UserContext.getCurrentContext().getCurrentUserName();
//		ThemeInfo theme= new ThemeInfo();
//		theme.setCreatUser(cuser);
//		theme.setType("common");
//		return themeDao.get(theme);
		return themeDao.loadUserThemeType(cuser, "common");
	}

	public List<ThemeInfo> get(ThemeInfo theme) {
		return themeDao.get(theme);
	}

	public ThemeInfo update(ThemeInfo model) {
		model.setUpdateTime(new Date());
		model.setUpdateUser("admin");
		return themeDao.update(model);
	}

	public ThemeInfo getOne(ThemeInfo model) {
		return themeDao.getOne(model);
	}

	public List<ThemeInfo> get(ThemeInfo model, Page page) {
		return themeDao.get(model, page);
	}

	public Pagination<ThemeInfo> findThemeInfoListByQueryMapWithPage(Page page,
			Sort[] sorts, Map<String, Object> paraMap) {
		return themeDao.findThemeInfoListByQueryMapWithPage(page, sorts, paraMap);
	}

}