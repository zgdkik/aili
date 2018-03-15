package org.hbhk.maikkr.backend.server.service;

import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.hbhk.maikkr.user.server.service.ICommonService;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;

/**
 * Service接口开发规范 
 * 2.类名必须以I开头、以Service结尾
 */
public interface IAdminService extends ICommonService<AdminInfo> {

	AdminInfo login(AdminInfo admin);

	AdminInfo regist(AdminInfo admin);

	Pagination<AdminInfo> queryAdminsByPage(Page page, Sort sort,
			Map<String, Object> params);
	
	Pagination<ThemeInfo> queryCommsByPage(Page page, Sort sort,
			 Map<String, Object> params);
}