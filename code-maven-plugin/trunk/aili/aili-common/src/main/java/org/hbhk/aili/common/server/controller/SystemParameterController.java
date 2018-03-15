package org.hbhk.aili.common.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.common.server.service.ISystemParameterService;
import org.hbhk.aili.common.share.entity.SystemParameterEntity;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class SystemParameterController extends AbstractController {

	@Autowired
	private ISystemParameterService systemParameterService;

	/**
	 * @Description:进入列表页面
	 */
	@RequestMapping("systemParameterList")
	public String findSystemParameterList() {
		return "/systemParameter/systemParameterList";
	}

	/**
	 * @Description:执行查询
	 */
	@RequestMapping("getSystemParameterList")
	@ResponseBody
	public Pagination<SystemParameterEntity> getPagination(
			@QueryPage QueryPageVo queryPageVo) {
		Pagination<SystemParameterEntity> pageInfo = systemParameterService
				.getPagination(queryPageVo.getParaMap(), queryPageVo.getPage(),
						queryPageVo.getSorts());
		return pageInfo;
	}

	/**
	 * @Description:跳转到添加页面
	 */
	@RequestMapping("/showSystemParameterForm")
	@ResponseBody
	public ResultEntity showSystemParameterForm(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		SystemParameterEntity systemParameter = null;
		if (StringUtils.isNotEmpty(id)) {
			systemParameter = systemParameterService.getById(id);
			model.addAttribute("systemParameter", systemParameter);
			model.addAttribute("title", "修改参数");
		} else {
			model.addAttribute("title", "新增参数");
		}			
		return returnSuccess(systemParameter);
	}
	/**
	 * 新增或者修改
	 */
	@RequestMapping("/addSystemParameter")
	@ResponseBody
	public ResultEntity addSystemParameter(@Valid SystemParameterEntity systemParameterEntity ,BindingResult bindingResult) {
		validator(bindingResult);
		String  msg ="";
		if(StringUtils.isNotEmpty(systemParameterEntity.getId())){
			int num = systemParameterService.insert(systemParameterEntity);
			msg = "系统参数修改成功!";
		}else{
			boolean flag = systemParameterService.checkSysKey(systemParameterEntity.getSysKey());
			if(flag){
				int num = systemParameterService.insert(systemParameterEntity);
				msg="系统参数添加成功";
			}else{
				msg="系统参数的键已存在";
			}
		}
		return returnSuccess(msg, systemParameterEntity.getId());
	}
	/**
	 * 删除
	 */
	@RequestMapping("/deleteSystemParameter/{id}")
	@ResponseBody
	public ResultEntity deleteSystemParameter(@PathVariable("id") String id) {
		int num = systemParameterService.deleteById(id);           
		if (num == 0) {
			return returnException("数据不存在，请重新选择一条!");
		} else {
			return returnSuccess("系统参数删除成功！");
		}
	}
//	/**
//	 * 校验鍵的唯一性
//	 */
//	@RequestMapping("/checkSysKey")
//	@ResponseBody
//	public ResultEntity checkSysKey(String sysKey, String id) {
//		if (StringUtils.isEmpty(id)) {
//			boolean flag = systemParameterService.checkSysKey(sysKey);
//			if (!flag) {
//				return returnException("系统参数的键已存在");
//			} else {
//				return returnSuccess("系统参数的键可用");
//			}
//		}
//		return returnSuccess();
//	}

}
