package org.hbhk.aili.workflow.server.controller;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.support.server.file.IFileService;
import org.hbhk.aili.workflow.server.service.IProcessService;
import org.hbhk.aili.workflow.share.vo.UserTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/workflow")
public class WorkflowController extends AbstractController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IProcessService processService;

	@Autowired
	private IFileService fileService;

	@RequestMapping("/usertask")
	public String usertask() {
		return "usertask";
	}

	@RequestMapping("/queryUsertask")
	@ResponseBody
	public Pagination<UserTaskVo> queryUsertask(
			@QueryPage QueryPageVo queryPageVo) {
		queryPageVo.getParaMap().put("assignee",
				UserContext.getCurrentUser().getUserName());
		return processService.queryUserTaskList(queryPageVo);
	}

	@RequestMapping("/view/{taskId}")
	public void view(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String taskId) {
	
		InputStream input = processService.getProcessImage(taskId);
		if(input==null){
			throw new BusinessException("流程图不存在");
		}
		viewImage(request, response, input);
	}
	@RequestMapping("/processView/{taskId}")
	public String processView(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String taskId) {
		request.setAttribute("imageurl", "/workflow/view/"+taskId);
		return "processimage";
	}

	@RequestMapping("/handlUserTask")
	@ResponseBody
	public ResultEntity handlUserTask(HttpServletRequest request,
			HttpServletResponse response, UserTaskVo userTaskVo,String workflowType) {
		userTaskVo.setAssignee(UserContext.getCurrentUser().getUserName());
		processService.handlUserTask(userTaskVo, workflowType);
		return returnSuccess("审批成功");
	}

	public void viewImage(HttpServletRequest request,
			HttpServletResponse response, InputStream input) {
		// pic为读取到图片的存储路径（数据库中存储的字段值）
		ServletOutputStream out = null;
		try {
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// 读取文件流
			int i = 0;
			byte[] buffer = new byte[input.available()];
			while ((i = input.read(buffer)) != -1) {
				// 写文件流
				out.write(buffer, 0, i);
			}
			out.flush();
			input.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	@RequestMapping("/publish")
	@ResponseBody
	public ResultEntity publish(){
		return returnSuccess("发布成功");
	}
}
