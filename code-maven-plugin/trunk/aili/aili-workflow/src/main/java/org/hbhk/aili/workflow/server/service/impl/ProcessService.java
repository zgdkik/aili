package org.hbhk.aili.workflow.server.service.impl;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.share.util.AopTargetUtil;
import org.hbhk.aili.workflow.server.dao.IFlowLogDao;
import org.hbhk.aili.workflow.server.dao.IUserTaskDao;
import org.hbhk.aili.workflow.server.service.IProcessEngineCore;
import org.hbhk.aili.workflow.server.service.IProcessService;
import org.hbhk.aili.workflow.share.entity.FlowLog;
import org.hbhk.aili.workflow.share.entity.WorkflowEntity;
import org.hbhk.aili.workflow.share.util.Constants;
import org.hbhk.aili.workflow.share.util.StringUtil;
import org.hbhk.aili.workflow.share.util.ToolUtil;
import org.hbhk.aili.workflow.share.vo.UserTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcessService implements IProcessService {

	@Autowired
	private IUserTaskDao userTaskDao;
	@Autowired
	private IFlowLogDao flowLogDao;
	@Autowired
	private IProcessEngineCore processCoreService;
	
	private Map<String, IBaseDao<BizBaseEntity, String>> workflowDaoMap;
	private static Map<String, WorkflowEntity> modelClass = new ConcurrentHashMap<String, WorkflowEntity>();
	
	@Override
	public Pagination<UserTaskVo> queryUserTaskList(QueryPageVo queryPageVo) {
		return userTaskDao.queryUserTaskList(queryPageVo.getParaMap(),queryPageVo.getPage());
	}

	@Override
	@Transactional
	public String handlUserTask(UserTaskVo userTaskRequest, String workflowType){
		Map<String, Object> params = new HashMap<String, Object>();
		//TODO 获取下一环节审批人
		params.put("assignee",UserContext.getCurrentUser().getUserName());
		params.put("id", userTaskRequest.getId());
		UserTaskVo userTask = userTaskDao.queryUserTask(params);
		// 处理当前代办
		Map<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("action", userTaskRequest.getAction());
		processCoreService.completeUserTask(userTask.getId(), proMap);
		// 获取下一节点审批人
		String nextApp = ToolUtil.getNextAppersonal(processCoreService,
				userTask.getInstanceId());
		// 创建流程日志
		FlowLog flowLog = ToolUtil.getFlowLog(userTask.getUserName(),
				userTask.getRecordId(), userTaskRequest.getAction(),
				userTaskRequest.getOpinion(), userTask.getId(),
				userTask.getDefinitionId(), userTask.getInstanceId());
		flowLogDao.insert(flowLog);
		// 查看流程是否结束
		boolean end = processCoreService.queryProcessIsEnd(userTask
				.getInstanceId());
		// 修改流程对象记录
		IBaseDao<BizBaseEntity, String>  workflowDao = workflowDaoMap.get(workflowType);
		//获取泛型实体 实例化
		WorkflowEntity wf = getGenericInterfaces(workflowDao);
		wf.setId(flowLog.getRecordId());
		if (StringUtil
				.isEqualString(userTaskRequest.getAction(), Constants.FLOW_AGREE)
				&& end) {
			wf.setStatus(Integer.parseInt(Constants.FLOW_DOM_PASS));
			workflowDao.update(wf);
		}
		if (StringUtil.isEqualString(userTaskRequest.getAction(),
				Constants.FLOW_DISAGREE)) {
			wf.setStatus(Integer.parseInt(Constants.FLOW_DOM_NOTPASS));
			workflowDao.update(wf);
		}
		return nextApp;
	}

	private WorkflowEntity getGenericInterfaces(IBaseDao<BizBaseEntity, String>  workflowDao) {
		try {
			Class<?> clazz = AopTargetUtil.getJdkDynamicProxyTargeClass(workflowDao);
			String className = clazz.getName();
			if (modelClass.containsKey(className)) {
				return modelClass.get(className);
			}
			Type[] types = clazz.getGenericInterfaces();
			ParameterizedType pType = (ParameterizedType) types[0];
			Class<?> cls = (Class<?>) pType.getActualTypeArguments()[0];
			modelClass.put(className, (WorkflowEntity) cls.newInstance());
			return modelClass.get(className);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
		
	}
	@Override
	public Pagination<FlowLog> queryFlowLog(QueryPageVo queryPageVo) {
		return flowLogDao.getPagination(queryPageVo.getParaMap(), queryPageVo.getPage(), queryPageVo.getSorts());
	}

	@Override
	public InputStream getProcessImage(String taskId) {
		
		return processCoreService.getImageStream(taskId);
	}

	public Map<String, IBaseDao<BizBaseEntity, String>> getWorkflowDaoMap() {
		return workflowDaoMap;
	}

	public void setWorkflowDaoMap(
			Map<String, IBaseDao<BizBaseEntity, String>> workflowDaoMap) {
		this.workflowDaoMap = workflowDaoMap;
	}
	
	

}
