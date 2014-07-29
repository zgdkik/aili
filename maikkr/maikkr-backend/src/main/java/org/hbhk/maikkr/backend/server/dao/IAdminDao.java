package org.hbhk.maikkr.backend.server.dao;

import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IAdminDao extends GenericEntityDao<AdminInfo, String> {

}