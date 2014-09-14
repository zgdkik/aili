package org.hbhk.maikkr.backend.server.dao;

import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IAdminDao extends GenericEntityDao<AdminInfo, String> {
	@NativeQuery(model = AdminInfo.class, value = "queryAdminsByPage")
	Pagination<AdminInfo> queryAdminsByPage(Page page, Sort sort,
			@QueryParam Map<String, Object> params);

}