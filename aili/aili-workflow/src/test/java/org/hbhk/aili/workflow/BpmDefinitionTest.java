package org.hbhk.aili.workflow;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
public class BpmDefinitionTest extends BaseTestCase{
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource 
	TaskService taskService;
	@Resource 
	private ProcessEngine processEngine;
	
	@Test
	public void testDeploy() throws IOException{
		InputStream is=readXmlFile();
		Assert.assertNotNull(is);
		//发布流程
		Deployment deployment=repositoryService.createDeployment().addInputStream("myflow.bpmn20.xml", is).name("holiday-request").deploy();
		Assert.assertNotNull(deployment);
		System.out.println("deployId:" + deployment.getId());
		//查询流程定义
		ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		
		System.out.println("processDefinitionId:"+processDefinition.getId());
	
	}
	/**
	 * 
	* @author 何波
	* @Description: 开启新的流程
	* @throws Exception   
	* void   
	* @throws
	 */
	@Test
	public void startProcess() throws Exception {
		String processDefinitionId="myProcess:2:2504";
		Long businessKey=new Double(1000000*Math.random()).longValue();
		//启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,businessKey.toString());
		System.out.println("processInstanceId:"+processInstance.getId());
		
	}
	@Test
	public void queryTask() throws Exception {
		String executionId="22501";
		//查询任务实例
		Task task=taskService.createTaskQuery().executionId(executionId).singleResult();
		System.out.println("taskId:"+task.getId());
		System.out.println("task name is " + task.getName() + " ,task key is " + task.getTaskDefinitionKey());
	}
	@Test
	@Rollback(value=true)
	public void generatorImg() throws Exception {
		//生成图片
		String processDefinitionId="myProcess:2:2504";
		String processInstanceId="22501";
		InputStream input = null ;//repositoryService.getProcessModel(processDefinition.getId());
		ProcessDiagramGenerator processDiagramGenerator  = new DefaultProcessDiagramGenerator();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration());
		input = processDiagramGenerator.generateDiagram(bpmnModel, "png", runtimeService.getActiveActivityIds(processInstanceId));
		BufferedImage bi1 = ImageIO.read(input);
		File w2 = new File("D://activiti.png");// 可以是jpg,png,gif格式
		ImageIO.write(bi1, "png", w2);//
	}
	
	/**
	 * 
	* @author 何波
	* @Description:完成当前节点任务 
	* @throws Exception   
	* void   
	* @throws
	 */
	@Test
	public void completeProcess() throws Exception {
		String taskId="27502";
		taskService.complete(taskId);
		generatorImg();
	}
	
	/**
	 * 
	* @author 何波
	* @Description: 分配流程
	* @throws Exception   
	* void   
	* @throws
	 */
	@Test
	public void claim() throws Exception {
		String taskId="22504";
		taskService.claim(taskId, "11");
	}
	public InputStream readXmlFile() throws IOException{
		String filePath="holiday-request.bpmn";
		return Class.class.getClass().getResource("/"+filePath).openStream();
	}
	
	 @Test
	  public void showImg() throws IOException{
	    String deploymentId = "30001";
	    //通过deploymentId获取资源名称
	    List<String> names =repositoryService
	          .getDeploymentResourceNames(deploymentId);
	    String imgName = null;
	    for (String name : names) {
	      System.out.println("name:"+name);
	      if (name.endsWith(".png")) {
	        imgName = name;
	      }
	    }
	    System.out.println("imgName:"+imgName);
	    if (imgName != null) {
	      File file = new File("e:/"+imgName);
	      //获取资源文件的流
	      InputStream in = repositoryService.getResourceAsStream(deploymentId, imgName);
	      //通过FileUtils将资源文件以流的信息输出到指定文件
	      FileUtils.copyInputStreamToFile(in, file);
	    }
	  }
}
