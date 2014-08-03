package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;

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
		theme.setCreatUser(UserContext.getCurrentContext().getCurrentUserName());
		theme.setId(UUIDUitl.getUuid());
		return themeDao.save(theme);
	}

	public List<ThemeInfo> loadUserTheme() {
		return null;
	}

	public ThemeInfo get(ThemeInfo theme) {
		return themeDao.getOne(theme);
	}

}