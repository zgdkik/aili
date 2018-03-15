package org.hbhk.aili.crud.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.ArrayCommand;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.crud.server.dao.IColumDao;
import org.hbhk.aili.crud.server.dao.IConditionDao;
import org.hbhk.aili.crud.server.service.ICrudManagerService;
import org.hbhk.aili.crud.server.service.IReportService;
import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.user.server.service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/crud")
public class CrudController extends AbstractController {

	@Autowired
	private IReportService crudService;

	@Autowired
	private ICrudManagerService crudManagerService;

	@Autowired
	private IColumDao columDao;

	@Autowired
	private IConditionDao conditionDao;

	@Autowired
	private IPrivilegeService privilegeService;

	@RequestMapping("/dataList/{code}")
	@ResponseBody
	public Pagination<Map<String, Object>> dataList(
			@QueryPage QueryPageVo queryPageVo, @PathVariable String code) {
		return crudService.getReportData(code, queryPageVo.getParaMap(),
				queryPageVo.getPage());
	}

	@RequestMapping("/{code}")
	public String report(@PathVariable String code, Model model) {
		CrudEntity crud = crudService.getByCode(code);
		model.addAttribute("crud", crud);
		Map<String, Object> params = new HashMap<>();
		params.put("crudCode", crud.getCode());
		model.addAttribute("cdts", conditionDao.get(params));
		List<ColumnEntity> cols = columDao.get(params);
		StringBuffer colStr = new StringBuffer();
		colStr.append("[[");
		for (int i = 0; i < cols.size(); i++) {
			ColumnEntity col = cols.get(i);
			String type = col.getType();
			colStr.append("{");
			colStr.append("field : '" +col.getField()+"',");
			if("time".equals(type)){
				colStr.append("title : '" +col.getTitle()+"',");
				colStr.append("formatter : function(val){");
				colStr.append("	 return aili.formatTime(val);");
				colStr.append("}");
			}else{
				colStr.append("title : '" +col.getTitle()+"'");
			}
			if((i+1) ==cols.size() ){
				colStr.append("}");
			}else{
				colStr.append("},");
			}
		}
		colStr.append("]]");
		model.addAttribute("columns", colStr);
		model.addAttribute("code", code);
		model.addAttribute("report", crud);
		return "crud";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		// 加载菜单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("type", "module");
		model.addAttribute("menus", privilegeService.getMenuService(map));
		return "list";
	}

	@RequestMapping("/pagination")
	@ResponseBody
	public Pagination<CrudEntity> pagination(@QueryPage QueryPageVo queryPageVo) {
		queryPageVo.getParaMap().put("status", 1);
		return crudManagerService.getPagination(queryPageVo.getParaMap(),
				queryPageVo.getPage(), queryPageVo.getSorts());
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity edit(CrudEntity report,
			@ArrayCommand(name = "col") ColumnEntity[] cols,
			@ArrayCommand(name = "cdt") Condition[] cdts) {
		String id = report.getId();
		String msg = "新增成功";
		if (StringUtils.isNotEmpty(id)) {
			msg = "修改成功";
		}
		crudManagerService.edit(report, cols, cdts);
		return returnSuccess(msg);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(Model model, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("type", "module");
		model.addAttribute("menus", privilegeService.getMenuService(map));
		if (StringUtils.isNotEmpty(id)) {
			CrudEntity crud = crudManagerService.getById(id);
			model.addAttribute("crud", crud);
			Map<String, Object> params = new HashMap<>();
			params.put("crudCode", crud.getCode());
			model.addAttribute("cols", columDao.get(params));
			model.addAttribute("cds", conditionDao.get(params));
		}
		return "edit";
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public ResultEntity get(@PathVariable String id) {
		return returnSuccess(crudManagerService.getById(id));

	}

	@RequestMapping("/updateStatus/{id}")
	@ResponseBody
	public ResultEntity updateStatus(@PathVariable String id) {
		crudManagerService.deleteById(id);
		return returnSuccess("删除成功");

	}

	@RequestMapping("/validateSql")
	@ResponseBody
	public ResultEntity validateSql(String sql) {
		return returnSuccess("验证成功", crudService.validateSql(sql));

	}

}
