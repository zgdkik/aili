package org.hbhk.aili.workflow.server.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.workflow.server.service.IWorkflowPublishService;
import org.hbhk.aili.workflow.share.entity.WorkflowPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author  何波
 *
 */
@Controller
@RequestMapping("/workflowPublish")
public class WorkflowPublishController extends AbstractController {

	@Autowired
	private IWorkflowPublishService workflowPublishService;

	@RequestMapping("/list")
	public String client() {

		return "/list";
	}

	@RequestMapping("/getPagination")
	@ResponseBody
	public Pagination<WorkflowPublish> getPagination(@QueryPage QueryPageVo queryPageVo) {
		queryPageVo.getParaMap().put("status", 1);
		Pagination<WorkflowPublish> pageInfo = workflowPublishService.getPagination(
				queryPageVo.getParaMap(), queryPageVo.getPage(),queryPageVo.getSorts());
		return pageInfo;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public ResultEntity edit(WorkflowPublish entity, BindingResult bindingResult,MultipartFile file) throws IOException {
		validator(bindingResult);
		String msg = "新增成功";
		if (StringUtils.isNotEmpty(entity.getId())) {
			msg = "修改成功";
		}
		if(file!=null){
//			InputStream input = file.getInputStream();
//			String content = InputSteamUtil.getStrFromInputSteam(input);
//			entity.setContent(content);
		}
		workflowPublishService.insert(entity);
		return returnSuccess(msg);
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public ResultEntity getById(@PathVariable String id) {
		return returnSuccess(workflowPublishService.getById(id));
	}
	
	@RequestMapping("/updateStatus/{id}")
	@ResponseBody
	public ResultEntity updateStatusById(@PathVariable String id) {
		workflowPublishService.updateStatusById(id,0);
		return returnSuccess("删除成功");
	}
}
