package org.hbhk.aili.workflow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.workflow.server.service.ILeaveService;
import org.hbhk.aili.workflow.server.service.IProcessEngineCore;
import org.hbhk.aili.workflow.server.service.IProcessService;
import org.hbhk.aili.workflow.share.entity.Leave;
import org.hbhk.aili.workflow.share.vo.UserTaskVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class BpmDefinitionTest1 extends BaseTestCase {
	@Autowired
	IProcessEngineCore processEngineCore;
	
	@Autowired
	ILeaveService leaveService;
	
	@Autowired
	private IProcessService processService;
	
	@Test
	public void testDeploy() throws IOException {
		InputStream is = readXmlFile();
		ProcessDefinition pd =processEngineCore.deploymentInstance(is, "hbhk1");
		System.out.println("processDefinitionId:"+pd.getId());
	}

	/**
	 * 
	 * @author 何波
	 * @Description: 开启新的流程
	 * @throws Exception
	 *             void
	 * @throws
	 */
	@Test
	public void startProcess() throws Exception {
		Leave leave = new Leave();
		leave.setAssignee("hbhk");
		leave.setApplyUser("hbhk1");
		leave.setReason("喝醉了");
		leave.setDays(3);
		leave.setStartTime(new Date());
		leave.setEndTime(DateUtils.addDays(new Date(), 3));
		leave.setStatus(2);
		leave.setDeptCode("it");
		leave.setDeptName("IT开发部");
		leave.setType("thing");
		leave.setPost("java工程师");
		String next = leaveService.insertLeave(leave,"myProcess:1:2504");
		System.out.println(next);
 
	}

	@Test
	public void queryTask() throws Exception {
	//	processEngineCore.queryByExecutionIdSingle(executionId)
	}

	@Test
	@Rollback(value = true)
	public void generatorImg() throws Exception {
		// 生成图片 
		String taskId = "50009";
		processEngineCore.getImageStream(taskId);
		InputStream input = processEngineCore.getImageStream(taskId);
		BufferedImage bi1 = ImageIO.read(input);
		File w2 = new File("D://activiti.png");
		ImageIO.write(bi1, "png", w2);//
	}

	/**
	 * 
	 * @author 何波
	 * @Description:完成当前节点任务
	 * @throws Exception
	 *             void
	 * @throws
	 */
	@Test
	public void completeUserTask() throws Exception {
		UserTaskVo userTask = new UserTaskVo();
		Leave leave = new Leave();
		//processService.handlUserTask(userTask, leave);
	}

	/**
	 * 
	 * @author 何波
	 * @Description: 分配流程
	 * @throws Exception
	 *             void
	 * @throws
	 */
	@Test
	public void claim() throws Exception {
		String taskId = "2504";
	}

	public InputStream readXmlFile() throws IOException {
		String filePath = "holiday-request.bpmn";
		String str = FileUtils.readFileToString(new File("E:/workspace/aili00/aili/aili-workflow/src/test/resources/holiday-request.bpmn"));
		System.out.println(str);
		return Class.class.getClass().getResource("/" + filePath).openStream();
	}

	
}
