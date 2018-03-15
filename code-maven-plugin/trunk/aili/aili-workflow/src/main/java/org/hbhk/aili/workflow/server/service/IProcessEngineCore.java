package org.hbhk.aili.workflow.server.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface IProcessEngineCore {
	ProcessDefinition deploymentInstance(InputStream input,String name);

	ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId);

	ProcessInstance startInstance(String processInstanceByKey,
			Map<String, Object> proMap);

	ProcessInstance findProcessInstanceByTaskId(String taskId);

	List<Task> queryByExecutionId(String executionId);

	Task queryByExecutionIdSingle(String executionId);

	List<Task> queryUserTaskList(String userName);

	void completeUserTask(String taskId, Map<String, Object> proMap);

	boolean queryProcessIsEnd(String processInstanceId);

	InputStream getImageStream(String taskId);

}
