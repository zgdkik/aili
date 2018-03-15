package org.hbhk.aili.workflow.server.dao;

import java.util.Map;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.workflow.share.vo.UserTaskVo;

public interface IUserTaskDao extends IBaseDao<UserTaskVo, String> {

	Pagination<UserTaskVo> queryUserTaskList(Map<String, Object> params,Page page);
	
	UserTaskVo queryUserTask(Map<String, Object> params);

}
