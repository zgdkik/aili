package org.hbhk.maikkr.user.server.service;

import java.util.List;

import org.hbhk.maikkr.user.share.pojo.ThemeInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IThemeService {
	ThemeInfo save(ThemeInfo theme);
	ThemeInfo get(ThemeInfo theme);
	List<ThemeInfo> loadUserTheme();
}