package org.hbhk.aili.org.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.util.ChineseUtil;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.org.server.service.IEmployeeService;
import org.hbhk.aili.org.share.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/emp")
@Controller
public class EmployeeController extends AbstractController {

	@Autowired
	IEmployeeService employeeService;

	@RequestMapping("/empList")
	public String empList() {
		return "empList";
	}

	@RequestMapping("/page")
	@ResponseBody
	public Pagination<EmployeeEntity> getPagination(@QueryPage QueryPageVo queryPageVo) {
		return employeeService.getPagination(queryPageVo.getParaMap(), queryPageVo.getPage(), queryPageVo.getSorts());
	}

	@RequestMapping("/edit")
	@ResponseBody
	public ResultEntity edit(EmployeeEntity emp) {
		String id = emp.getId();
		String msg = "新增成功";
		if(StringUtils.isNotEmpty(id)){
			msg="修改成功";
		}
		employeeService.insert(emp);
		return returnSuccess(msg);
	}

	@RequestMapping("/updateStatus")
	@ResponseBody
	public ResultEntity updateStatusById(String id, int status) {
		employeeService.updateStatusById(id, status);
		return returnSuccess();
	}
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public ResultEntity getById(@PathVariable String id) {
		return returnSuccess(employeeService.getById(id));
	}
	@RequestMapping("/selector")
	@ResponseBody
	public List<EmployeeEntity>selector(String q) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (ChineseUtil.isContainChinese(q)) {
			params.put("empName", "%" + q + "%");
		} else {
			if(StringUtils.isNotEmpty(q)){
				params.put("empCode", q);
			}
		}
		return employeeService.getPage(params, 1, 10);
	}
}
