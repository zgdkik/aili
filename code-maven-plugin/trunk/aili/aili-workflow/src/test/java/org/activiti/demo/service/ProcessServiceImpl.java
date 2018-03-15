//package org.activiti.demo.service;
//
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.activiti.demo.dao.CommonDao;
//import org.activiti.demo.engine.ProcessEngineCore;
//import org.activiti.demo.utils.ToolUtil;
//import org.hbhk.aili.workflow.share.entity.FlowLog;
//import org.hbhk.aili.workflow.share.entity.Leave;
//import org.hbhk.aili.workflow.share.util.Constants;
//import org.hbhk.aili.workflow.share.util.StringUtil;
//import org.hbhk.aili.workflow.share.vo.UserTaskVo;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * 
// * @Title: ProcessServiceImpl.java
// * @Description: org.activiti.demo.service
// * @Package org.activiti.demo.service
// * @author hncdyj123@163.com
// * @date 2013-3-19
// * @version V1.0
// * 
// */
//@Service("processService")
//public class ProcessServiceImpl implements ProcessService {
//
//	@Resource(name = "commonDao")
//	private CommonDao commonDao;
//	@Resource(name = "processEngineCore")
//	private ProcessEngineCore processEngineCore;
//
//	@Override
//	public List<UserTaskVo> queryPersonalTask(UserTaskVo userTask) throws Exception {
//		return (List<UserTaskVo>) commonDao.listObject(userTask);
//	}
//
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Override
//	public String handlePersonalTask(UserTaskVo userTask) throws Exception {
//		// 处理当前代办
//		Map<String, Object> proMap = new HashMap<String, Object>();
//		proMap.put("action", userTask.getAction());
//		processEngineCore.completeUserTask(userTask.getID(), proMap);
//		// 获取下一节点审批人
//		String nextApp = ToolUtil.getNextAppersonal(processEngineCore, userTask.getInstanceId());
//		// 创建流程日志
//		FlowLog flowLog = ToolUtil.getFlowLog(userTask.getUserID(), userTask.getRecordID(), userTask.getAction(), userTask.getOpinion(), userTask.getID(), userTask.getDefinitionId(), userTask.getInstanceId());
//		commonDao.insertObject(flowLog);
//		// 查看流程是否结束
//		boolean isend = processEngineCore.queryProcessIsEnd(userTask.getInstanceId());
//		Leave leave = new Leave();
//		leave.setID(Integer.parseInt(flowLog.getRecordID()));
//		if (StringUtil.isEqualString(userTask.getAction(), Constants.FLOW_AGREE) && isend) {
//			leave.setDomStatus(Constants.FLOW_DOM_PASS);
//		}
//		if (StringUtil.isEqualString(userTask.getAction(), Constants.FLOW_DISAGREE)) {
//			leave.setDomStatus(Constants.FLOW_DOM_NOTPASS);
//		}
//		// 修改记录
//		commonDao.updateObject(leave);
//		return nextApp;
//	}
//
//	@Override
//	public List<FlowLog> listFlowLog(FlowLog flowLog) throws Exception {
//		return (List<FlowLog>) commonDao.listObject(flowLog);
//	}
//
//	@Override
//	public int queryPersonalTaskCount(UserTaskVo userTask) throws Exception {
//		return commonDao.listObjectCount(userTask);
//	}
//
//	@Override
//	public InputStream getProcessImage(String taskId) throws Exception {
//		return processEngineCore.getImageStream(taskId);
//	}
//
//	public void setCommonDao(CommonDao commonDao) {
//		this.commonDao = commonDao;
//	}
//
//	public void setProcessEngineCore(ProcessEngineCore processEngineCore) {
//		this.processEngineCore = processEngineCore;
//	}
//
//}
