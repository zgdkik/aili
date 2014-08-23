package org.hbhk.maikkr.user.server.dao;

import java.util.List;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IThemeDao extends GenericEntityDao<ThemeInfo, String> {

	@NativeQuery(model = ThemeInfo.class,value="loadUserThemeType")
	List<ThemeInfo> loadUserThemeType(@QueryParam("cuser") String cuser,
			@QueryParam("type") String type);
}