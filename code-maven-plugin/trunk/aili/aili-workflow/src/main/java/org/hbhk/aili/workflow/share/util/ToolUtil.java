package org.hbhk.aili.workflow.share.util;

import java.util.Date;
import java.util.List;

import org.activiti.engine.task.Task;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.workflow.server.service.IProcessEngineCore;
import org.hbhk.aili.workflow.share.entity.FlowLog;

public class ToolUtil {

	/**
	 * 获取流程日志对象
	 * 
	 * @param userID
	 *            用户ID
	 * @param domStatus
	 *            记录状态
	 * @param recordID
	 *            记录主键
	 * @param action
	 *            状态(1.同意 2.不同意)
	 * @param opinion
	 *            意见
	 * @param processInstance
	 *            流程对象
	 * @return
	 */
	public static FlowLog getFlowLog(String userName, String recordId, String action, String opinion, String taskId, String definitionId, String instanceId) {
		FlowLog flowLog = new FlowLog();
		flowLog.setUserName(userName);
		flowLog.setAction(action);
		flowLog.setCreateTime(new Date());
		flowLog.setId(UuidUtil.getUuid());
		flowLog.setOpinion(opinion);
		flowLog.setTaskId(taskId);
		flowLog.setTaskName(definitionId);
		flowLog.setFlowId(instanceId);
		flowLog.setRecordId(recordId);
		return flowLog;
	}

	/**
	 * 获取下一节点审批人
	 * 
	 * @param processEngineCore
	 * @param userTask
	 * @return
	 */
	public static String getNextAppersonal(IProcessEngineCore processEngineCore, String executionId) {
		List<Task> taskList = processEngineCore.queryByExecutionId(executionId);
		String nextApp = "";
		for (int i = 0; i < taskList.size(); i++) {
			nextApp += taskList.get(i).getAssignee() + (i == taskList.size() - 1 ? "" : ",");
		}
		return nextApp;
	}

}
