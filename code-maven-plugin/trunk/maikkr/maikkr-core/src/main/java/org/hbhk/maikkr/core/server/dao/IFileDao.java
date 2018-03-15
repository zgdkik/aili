package org.hbhk.maikkr.core.server.dao;

import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.maikkr.user.share.pojo.FileInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IFileDao extends GenericEntityDao<FileInfo, String> {

}