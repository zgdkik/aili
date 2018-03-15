package org.hbhk.aili.workflow.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.workflow.server.dao.IFlowLogDao;
import org.hbhk.aili.workflow.server.dao.ILeaveDao;
import org.hbhk.aili.workflow.server.service.ILeaveService;
import org.hbhk.aili.workflow.server.service.IProcessEngineCore;
import org.hbhk.aili.workflow.share.entity.FlowLog;
import org.hbhk.aili.workflow.share.entity.Leave;
import org.hbhk.aili.workflow.share.util.Constants;
import org.hbhk.aili.workflow.share.util.StringUtil;
import org.hbhk.aili.workflow.share.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @Title: LeaveServiceImpl.java
 * @Description: org.activiti.demo.service
 * @Package org.activiti.demo.service
 * @author hncdyj123@163.com
 * @date 2013-3-15
 * @version V1.0
 * 
 */
@Service
public class LeaveService implements ILeaveService {
	@Resource
	private ILeaveDao leaveDao;
	
	@Autowired
	private IProcessEngineCore processCoreService;

	@Autowired
	private IFlowLogDao flowLogDao;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String insertLeave(Leave leave,String processInstanceByKey) {
		String id = UuidUtil.getUuid();
		leave.setId(id);
		leave.setCreateTime(new Date());
		String user = UserContext.getCurrentUser().getUserName();
		leave.setCreateUser(user);
		leaveDao.insert(leave);
		// 当前新增记录ID
		String recordId = id;
		// 下一节点审批人
		String nextApp = "";
		// 提交 则启动流程
		if (StringUtil.isEqualString(leave.getStatus()+"", Constants.FLOW_DOM_SUMBIT)) {
			// 当前登录用户提交申请
			Map<String, Object> proMap = new HashMap<String, Object>();
			proMap.put("loginUser", user);
			proMap.put("assignee", leave.getAssignee());
			// 启动流程并且完成第一节点TASK
			ProcessInstance processInstance = processCoreService.startInstance(processInstanceByKey, proMap);
			Task task = processCoreService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
			processCoreService.completeUserTask(task.getId(), null);
			nextApp = ToolUtil.getNextAppersonal(processCoreService, task.getExecutionId());
			task = processCoreService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
			// 创建流程日志
			FlowLog flowLog = ToolUtil.getFlowLog(user, recordId, "1", null, task.getId(), processInstance.getProcessDefinitionId(), processInstance.getProcessInstanceId());
			flowLogDao.insert(flowLog);
		}
		return nextApp;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String updateLeave(Leave leave,String processInstanceByKey) {
		Leave ltemp = leaveDao.getById(leave.getId());
		leaveDao.update(leave);
		// 下一节点审批人
		String nextApp = "";
		// 提交 dom启动流程
		if (StringUtil.isEqualString(ltemp.getStatus()+"", Constants.FLOW_DOM_SUMBIT) && StringUtil.isEqualString(leave.getStatus()+"", Constants.FLOW_DOM_SUMBIT)) {
			// 当前登录用户提交申请
			Map<String, Object> proMap = new HashMap<String, Object>();
			proMap.put("loginUser", leave.getCreateUser());
			proMap.put("assignee", leave.getAssignee());
			// 启动流程并且完成第一节点TASK
			ProcessInstance processInstance = processCoreService.startInstance(processInstanceByKey, proMap);
			
			Task task = processCoreService.queryByExecutionIdSingle(processInstance.getProcessInstanceId());
			processCoreService.completeUserTask(task.getId(), null);
			nextApp = ToolUtil.getNextAppersonal(processCoreService, task.getExecutionId());
			// 创建流程日志
			FlowLog flowLog = ToolUtil.getFlowLog(leave.getCreateUser(), leave.getId(), "1", null, task.getId(), processInstance.getProcessDefinitionId(), processInstance.getProcessInstanceId());
			flowLogDao.insert(flowLog);
		} else {
			// 流程打回提交
			FlowLog f = new FlowLog();
			f.setRecordId(ltemp.getId());
			// 获取流程日志集合
			List<FlowLog> list = flowLogDao.get(BeanToMapUtil.convert(f));
			if (!StringUtil.isEmptyArray(list)) {
				f = list.get(0);
			}
			// 根据流程ID查询task 未考虑子流程
			Task task = processCoreService.queryByExecutionIdSingle(f.getFlowId());
			if (task != null) {
				// 提交完成当前任务
				processCoreService.completeUserTask(task.getId(), null);
				nextApp = ToolUtil.getNextAppersonal(processCoreService, task.getExecutionId());
				// 创建流程日志
				FlowLog flowLog = ToolUtil.getFlowLog(leave.getCreateUser(), leave.getId(), "1", null, task.getId(), task.getProcessDefinitionId(), task.getProcessInstanceId());
				flowLogDao.insert(flowLog);
			}
		}
		return nextApp;
	}

	@Override
	public int insert(Leave t) {
		return 0;
	}

	@Override
	public int update(Leave t) {
		return 0;
	}

	@Override
	public Leave getById(String id) {
		return leaveDao.getById(id);
	}

	@Override
	public List<Leave> get(Map<String, Object> params) {
		return leaveDao.get(params);
	}

	@Override
	public List<Leave> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		return leaveDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return leaveDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		return 0;
	}

	@Override
	public Pagination<Leave> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return leaveDao.getPagination(params, page, sorts);
	}

	


}
