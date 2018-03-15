package org.activiti.demo.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @Title: ProcessEngineCore.java
 * @Description: 流程引擎核心类
 * @Package org.activiti.demo.engine
 * @author hncdyj123@163.com
 * @date 2013-3-13
 * @version V1.0
 * 
 */
@Service("processEngineCore")
public class ProcessEngineCore {
	/**************** 日志对象 ****************/
	private static final Logger logger = LoggerFactory.getLogger(ProcessEngineCore.class);

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;

	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;

	@Resource(name = "taskService")
	private TaskService taskService;

	@Resource(name = "historyService")
	private HistoryService historyService;

	/**
	 * 部署流程实例
	 * 
	 * @param processFilePath
	 *            流程文件路径
	 * @throws FileNotFoundException
	 */
	public void deploymentInstance(String processFilePath) throws FileNotFoundException {
		InputStream in = new FileInputStream(new File(processFilePath));
		// 从文件流部署流程
		Deployment deployment = repositoryService.createDeployment().addInputStream(processFilePath.substring(processFilePath.lastIndexOf(File.separator) + 1, processFilePath.length()), in).deploy();
		logger.info("process deployment success & processID is " + deployment.getId() + ",createTime is " + deployment.getDeploymentTime());
	}

	/**
	 * 根据任务ID获取流程定义
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId) throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(findTaskById(taskId).getProcessDefinitionId());

		if (processDefinition == null) {
			throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}

	/**
	 * 启动流程实例
	 * 
	 * @param processInstanceByKey
	 *            流程部署KEY
	 * @param proMap
	 *            流程变量
	 */
	public ProcessInstance startInstance(String processInstanceByKey, Map<String, Object> proMap) {
		// 启动流程实例 ,注意这里的key是我们流程文件中的<process id="myProcess"
		// ,id属性,在Activiti术语叫key
		// yangjie 启动一个请假流程实例
		ProcessInstance processInstance = null;
		if (proMap != null) {
			processInstance = runtimeService.startProcessInstanceByKey(processInstanceByKey, proMap);
		} else {
			processInstance = runtimeService.startProcessInstanceByKey(processInstanceByKey);
		}
		logger.info("process start success  key [" + processInstance.getBusinessKey() + "]");
		return processInstance;
	}

	/**
	 * 根据任务ID获取对应的流程实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId) throws Exception {
		// 找到流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(findTaskById(taskId).getProcessInstanceId()).singleResult();
		if (processInstance == null) {
			throw new Exception("流程实例未找到!");
		}
		return processInstance;
	}

	/**
	 * 根据任务ID获得任务实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) throws Exception {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new Exception("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 根据executionId查询task
	 * 
	 * @param executionId
	 * @return
	 */
	public List<Task> queryByExecutionId(String executionId) {
		List<Task> taskList = taskService.createTaskQuery().executionId(executionId).list();
		return taskList;
	}

	/**
	 * 根据executionId查询task
	 * 
	 * @param executionId
	 * @return
	 */
	public Task queryByExecutionIdSingle(String executionId) {
		Task task = taskService.createTaskQuery().executionId(executionId).singleResult();
		return task;
	}

	/**
	 * 查询UserTask列表
	 * 
	 * @param userName
	 * @return
	 */
	public List<Task> queryUserTaskList(String userName) {
		// 查询当前用户任务列表
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).list();
		return taskList;
	}

	/**
	 * 完成UserTask
	 * 
	 * @param taskId
	 *            任务ID
	 * @param proMap
	 *            流程变量
	 */
	public void completeUserTask(String taskId, Map<String, Object> proMap) {
		if (proMap != null) {
			taskService.complete(taskId, proMap);
		} else {
			taskService.complete(taskId);
		}
	}

	/**
	 * 根据流程ID查看流程是否结束
	 * 
	 * @param processInstanceId
	 *            流程ID
	 */
	public boolean queryProcessIsEnd(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if (historicProcessInstance != null && historicProcessInstance.getStartTime() != null && historicProcessInstance.getEndTime() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 获取图片流
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public InputStream getImageStream(String taskId) throws Exception {
		ProcessDefinitionEntity pde = findProcessDefinitionEntityByTaskId(taskId);
		InputStream imageStream = ProcessDiagramGenerator.generateDiagram(pde, "png", runtimeService.getActiveActivityIds(findProcessInstanceByTaskId(taskId).getId()));
		return imageStream;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

}
