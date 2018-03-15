package org.hbhk.aili.org.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.util.ChineseUtil;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.org.server.service.IDepartmentService;
import org.hbhk.aili.org.share.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/dept")
@Controller
public class DepartmentController extends AbstractController {

	@Autowired
	IDepartmentService departmentService;

	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree(String id) {
		return departmentService.getTree(id);

	}

	@RequestMapping("/page")
	@ResponseBody
	public Pagination<DepartmentEntity> getPagination(
			@QueryPage QueryPageVo queryPageVo) {
		return departmentService.getPagination(queryPageVo.getParaMap(),
				queryPageVo.getPage(), queryPageVo.getSorts());
	}

	@RequestMapping("/selector")
	@ResponseBody
	public List<DepartmentEntity> selector(String q) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (ChineseUtil.isContainChinese(q)) {
			params.put("deptName", "%" + q + "%");
		} else {
			if (StringUtils.isNotEmpty(q)) {
				params.put("deptCode", q);
			}
		}
		return departmentService.getPage(params, 1, 10);
	}

	@RequestMapping("/deptList")
	public String deptList() {
		return "deptList";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public ResultEntity edit(DepartmentEntity dept) {
		String id = dept.getId();
		String msg = "新增成功";
		if (StringUtils.isNotEmpty(id)) {
			msg = "修改成功";
		}
		departmentService.insert(dept);
		return returnSuccess(msg);
	}

	@RequestMapping("/updateStatus")
	@ResponseBody
	public ResultEntity updateStatusById(String id, int status) {
		departmentService.updateStatusById(id, status);
		return returnSuccess();
	}
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public ResultEntity getById(@PathVariable String id) {
		return returnSuccess(departmentService.getById(id));
	}
}
