//package org.activiti.demo.action;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.activiti.demo.base.BaseAction;
//import org.activiti.demo.domain.FlowLog;
//import org.activiti.demo.domain.Leave;
//import org.activiti.demo.domain.SysUser;
//import org.activiti.demo.domain.UserTask;
//import org.activiti.demo.service.LeaveService;
//import org.activiti.demo.service.ProcessService;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.apache.struts2.convention.annotation.Result;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import ch.qos.logback.classic.Logger;
//
///**
// * 
// * @Title: ProcessAction.java
// * @Description: 流程相关action
// * @Package org.activiti.demo.action
// * @author hncdyj123@163.com
// * @date 2013-3-15
// * @version V1.0
// * 
// */
//@Controller("processAction")
//@Scope("protype")
//@Namespace("/activiti/process")
//@ParentPackage("json-default")
//public class ProcessAction extends BaseAction {
//	/** 日志对象 **/
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(SysUserAction.class);
//	/** 实体对象 **/
//	private Leave leave;
//	private UserTask userTask;
//	private ProcessInstance processInstance;
//	private FlowLog flowLog;
//	/** service **/
//	@Resource(name = "leaveService")
//	private LeaveService leaveService;
//	@Resource(name = "processService")
//	private ProcessService processService;
//	/** 返回文件流 **/
//	private ByteArrayInputStream inputStream;
//
//	/**
//	 * 新增请假
//	 * 
//	 * @return
//	 */
//	@Action(value = "insertLeave", results = { @Result(type = "json", params = { "root", "result" }) })
//	public String insertLeave() {
//		long startTime = System.currentTimeMillis();
//		if (leave == null) {
//			leave = new Leave();
//		}
//		try {
//			SysUser user = (SysUser) super.getSession().get("user");
//			leave.setUserID(String.valueOf(user.getUsername()));
//			String nextApp = leaveService.insertLeave(leave);
//			this.setMessage(nextApp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("insertLeave method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 修改请假
//	 * 
//	 * @return
//	 */
//	@Action(value = "updateLeave", results = { @Result(type = "json", params = { "root", "result" }) })
//	public String updateLeave() {
//		long startTime = System.currentTimeMillis();
//		if (leave == null) {
//			leave = new Leave();
//		}
//		try {
//			SysUser user = (SysUser) super.getSession().get("user");
//			leave.setUserID(String.valueOf(user.getUsername()));
//			String nextApp = leaveService.updateLeave(leave);
//			this.setMessage(nextApp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("updateLeave method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 删除请假
//	 * 
//	 * @return
//	 */
//	@Action(value = "deleteLeave", results = { @Result(type = "json", params = { "root", "result" }) })
//	public String deleteLeave() {
//		long startTime = System.currentTimeMillis();
//		if (leave == null) {
//			leave = new Leave();
//		}
//		try {
//			leaveService.deleteLeave(leave);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("deleteLeave method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 根据ID获取请假记录
//	 * 
//	 * @return
//	 */
//	@Action(value = "getByIDLeave", results = { @Result(type = "json", params = { "root", "leave" }) })
//	public String getByIDLeave() {
//		long startTime = System.currentTimeMillis();
//		if (leave == null) {
//			leave = new Leave();
//		}
//		try {
//			leave = leaveService.getByIDLeave(leave);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("getByIDLeave method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 获取所有的请假记录
//	 * 
//	 * @return
//	 */
//	@Action(value = "listLeave", results = { @Result(type = "json", params = { "root", "datagrid" }) })
//	public String listLeave() {
//		long startTime = System.currentTimeMillis();
//		if (leave == null) {
//			leave = new Leave();
//		}
//		leave.setUserID(((SysUser) super.getSession().get("user")).getUsername());
//		try {
//			// 获取记录
//			List<Leave> list = leaveService.listLeave(leave);
//			setList(list);
//			if (list != null && list.size() > 0) {
//				// 设置总记录数
//				setTotal(leaveService.listLeaveCount(leave));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("listLeave method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 查询个人代办
//	 * 
//	 * @return
//	 */
//	@Action(value = "queryPersonalTask", results = { @Result(type = "json", params = { "root", "datagrid" }) })
//	public String queryPersonalTask() {
//		long startTime = System.currentTimeMillis();
//		if (userTask == null) {
//			userTask = new UserTask();
//		}
//		try {
//			userTask.setAssignee(((SysUser) super.getSession().get("user")).getUsername());
//			// 获取记录
//			List<UserTask> list = processService.queryPersonalTask(userTask);
//			setList(list);
//			if (list != null && list.size() > 0) {
//				// 设置总记录数
//				setTotal(processService.queryPersonalTaskCount(userTask));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("queryPersonalTask method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 处理个人代办
//	 * 
//	 * @return
//	 */
//	@Action(value = "runPersonalTask", results = { @Result(type = "json", params = { "root", "result" }) })
//	public String runPersonalTask() {
//		long startTime = System.currentTimeMillis();
//		if (userTask == null) {
//			userTask = new UserTask();
//		}
//		try {
//			userTask.setAssignee(((SysUser) super.getSession().get("user")).getUsername());
//			userTask.setUserID(((SysUser) super.getSession().get("user")).getUsername());
//			// 处理个人代办
//			String nextApp = processService.handlePersonalTask(userTask);
//			this.setMessage(nextApp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("runPersonalTask method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 获取流程日志
//	 * 
//	 * @return
//	 */
//	@Action(value = "listFlowLog", results = { @Result(type = "json", params = { "root", "msgList" }) })
//	public String listFlowLog() {
//		long startTime = System.currentTimeMillis();
//		if (flowLog == null) {
//			flowLog = new FlowLog();
//		}
//		try {
//			// 获取流程日志
//			List list = processService.listFlowLog(flowLog);
//			setMsgList(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("listFlowLog method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 获取流程日志
//	 * 
//	 * @return
//	 */
//	@Action(value = "runProcess", results = { @Result(type = "json", params = { "root", "result" }) })
//	public String runProcess() {
//		long startTime = System.currentTimeMillis();
//		if (flowLog == null) {
//			flowLog = new FlowLog();
//		}
//		try {
//			// 获取流程日志
//			List list = processService.listFlowLog(flowLog);
//			setMsgList(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("listFlowLog method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	/**
//	 * 获取流程图片
//	 * 
//	 * @return
//	 */
//	@Action(value = "getProessImage", results = { @Result(type = "stream", params = { "contentType", "image/jpeg", "inputName", "inputStream" }) })
//	public String getProessImage() {
//		long startTime = System.currentTimeMillis();
//		if (userTask == null) {
//			userTask = new UserTask();
//		}
//		try {
//			InputStream in = processService.getProcessImage(userTask.getID());
//			byte[] b = new byte[1024];
//			int len = -1;
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			while ((len = in.read(b, 0, 1024)) != -1) {
//				outputStream.write(b, 0, len);
//			}
//			inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//			inputStream.close();
//			outputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			return ERROR;
//		}
//		logger.debug("getProessImage method running time is " + String.valueOf(System.currentTimeMillis() - startTime) + " ms");
//		return SUCCESS;
//	}
//
//	public Leave getLeave() {
//		return leave;
//	}
//
//	public void setLeave(Leave leave) {
//		this.leave = leave;
//	}
//
//	public UserTask getUserTask() {
//		return userTask;
//	}
//
//	public void setUserTask(UserTask userTask) {
//		this.userTask = userTask;
//	}
//
//	public ProcessInstance getProcessInstance() {
//		return processInstance;
//	}
//
//	public void setProcessInstance(ProcessInstance processInstance) {
//		this.processInstance = processInstance;
//	}
//
//	public FlowLog getFlowLog() {
//		return flowLog;
//	}
//
//	public void setFlowLog(FlowLog flowLog) {
//		this.flowLog = flowLog;
//	}
//
//	public LeaveService getLeaveService() {
//		return leaveService;
//	}
//
//	public void setLeaveService(LeaveService leaveService) {
//		this.leaveService = leaveService;
//	}
//
//	public void setProcessService(ProcessService processService) {
//		this.processService = processService;
//	}
//
//	public ByteArrayInputStream getInputStream() {
//		return inputStream;
//	}
//
//	public void setInputStream(ByteArrayInputStream inputStream) {
//		this.inputStream = inputStream;
//	}
//
//}
