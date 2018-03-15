package org.hbhk.aili.workflow.server.controller;

import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.support.server.file.IFileService;
import org.hbhk.aili.workflow.server.service.ILeaveService;
import org.hbhk.aili.workflow.server.service.IProcessService;
import org.hbhk.aili.workflow.share.entity.Leave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/leave")
public class leaveController extends AbstractController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IProcessService processService;

	@Autowired
	private ILeaveService leaveService;
	
	@Autowired
	private IFileService fileService;
	

	@RequestMapping("/leaveList")
	public String leaveList() {
		return "leaveList";
	}

	@RequestMapping("/queryPage")
	@ResponseBody
	public Pagination<Leave> queryPage(@QueryPage QueryPageVo queryPageVo) {
		return leaveService.getPagination(queryPageVo.getParaMap(),
				queryPageVo.getPage(), queryPageVo.getSorts());
	}

	@RequestMapping("/apply")
	@ResponseBody
	public ResultEntity apply(Leave leave, MultipartFile file) throws Exception {
		String workflowNo ="leave-"+UserContext.getCurrentUser().getUserName()+"-"+System.currentTimeMillis();
		String path = "/workflow/leave/"+UserContext.getCurrentUser().getUserName()+"/"+workflowNo+"/";
		String filePath =  fileService.saveFile(file, path,file.getOriginalFilename());
		leave.setFilePath(filePath);
		leave.setWorkflowNo(workflowNo);
		leaveService.insertLeave(leave, "myProcess:1:2504");
		return returnSuccess("申请成功");
	}
}
