package org.hbhk.maikkr.user.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IThemeService extends ICommonService<ThemeInfo> {
	ThemeInfo save(ThemeInfo theme);
	List<ThemeInfo> loadUserThemeType();
	
	Pagination<ThemeInfo> findThemeInfoListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> paraMap);
}