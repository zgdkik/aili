package org.hbhk.aili.workflow;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
public class BpmDefinitionTest extends BaseTestCase{
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource 
	TaskService taskService;
	
	@Test
	public void testDeploy() throws IOException{
		InputStream is=readXmlFile();
		Assert.assertNotNull(is);
		//发布流程
		Deployment deployment=repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml", is).name("holiday-request").deploy();
		Assert.assertNotNull(deployment);
		System.out.println("deployId:" + deployment.getId());
		//查询流程定义
		ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		Long businessKey=new Double(1000000*Math.random()).longValue();
		//启动流程
		runtimeService.startProcessInstanceById(processDefinition.getId(),businessKey.toString());
		//查询任务实例
		List<Task> taskList=taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).list();
		Assert.assertNotNull(taskList==null);
		Assert.assertTrue(taskList.size()>0);
		for(Task task:taskList){
			System.out.println("task name is " + task.getName() + " ,task key is " + task.getTaskDefinitionKey());
		}
		
		//生成图片
		InputStream input = repositoryService.getProcessDiagram(processDefinition.getId());
		ProcessDiagramGenerator processDiagramGenerator  = new DefaultProcessDiagramGenerator();
		
		BufferedImage bi1 =  ImageIO.read(input);//processDiagramGenerator.generatePngImage(null, 1.0);
		File w2 = new File("D://activiti.png");// 可以是jpg,png,gif格式
		ImageIO.write(bi1, "png", w2);//
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
