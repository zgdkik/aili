package org.hbhk.aili.workflow.server.service;

import java.io.InputStream;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.workflow.share.entity.FlowLog;
import org.hbhk.aili.workflow.share.vo.UserTaskVo;

public interface IProcessService {
	/**
	 * 查询个人代办
	 * 
	 * @param userTask
	 * @return
	 * @throws Exception
	 */
	Pagination<UserTaskVo> queryUserTaskList(QueryPageVo queryPageVo);

	/**
	 * 处理个人代办
	 * 
	 * @param userTask
	 * @param processInstance
	 * @throws Exception
	 */
	String handlUserTask(UserTaskVo userTask, String workflowType);

	/**
	 * 获取流程日志
	 * 
	 * @param flowLog
	 * @return
	 * @throws Exception
	 */
	Pagination<FlowLog> queryFlowLog(QueryPageVo queryPageVo);

	/**
	 * 根据taskId获取图片流
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	InputStream getProcessImage(String taskId);


}
