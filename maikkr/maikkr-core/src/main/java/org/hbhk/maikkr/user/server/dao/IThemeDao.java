package org.hbhk.maikkr.user.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IThemeDao extends GenericEntityDao<ThemeInfo, String> {

	@NativeQuery(model = ThemeInfo.class,value="loadUserThemeType")
	List<ThemeInfo> loadUserThemeType(@QueryParam("cuser") String cuser,
			@QueryParam("type") String type);
	@NativeQuery(model = ThemeInfo.class, value = "findThemeInfoListByQueryMapWithPage")
	Pagination<ThemeInfo> findThemeInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
}