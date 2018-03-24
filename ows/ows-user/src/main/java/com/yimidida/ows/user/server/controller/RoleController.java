package com.yimidida.ows.user.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.base.share.vo.TreeVo;
import com.yimidida.ows.common.share.conts.CompConstants;
import com.yimidida.ows.user.server.service.IPrivilegeService;
import com.yimidida.ows.user.server.service.IRoleService;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;
import com.yimidida.ows.user.share.entity.RoleEntity;
import com.yimidida.ows.user.share.entity.RolePrivilegeEntity;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.JsonUtil;


@Controller
@RequestMapping("/role")
public class RoleController extends AbstractController {
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPrivilegeService functionService;

	@RequestMapping("/getRoleByid/{id}")
	@ResponseBody
	public ResultEntity getRoleByid(@PathVariable String id) {
		if(StringUtils.isEmpty(id)){
			throw new BusinessException("参数不能为空");
		}
		RoleEntity role = roleService.getRoleById(id);
		return returnSuccess(role);
	}
	
	@RequestMapping("/getRolePrivilege/{roleCode}")
	@ResponseBody
	public ResultEntity getRolePrivilege(@PathVariable String roleCode) {
		List<RolePrivilegeEntity> rolePrivilegeEntities = roleService
				.getRoleFunctionList(roleCode);
		List<String> funCodes = new ArrayList<>();
		if (rolePrivilegeEntities != null && !rolePrivilegeEntities.isEmpty()) {
			for (RolePrivilegeEntity rf : rolePrivilegeEntities) {
				funCodes.add(rf.getFunctionCode());
			}
		}
		return returnSuccess(funCodes);
	}
	/**
	 * @Description:跳转到添加页面
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public ResultEntity showRole(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object>  result = new HashMap<String, Object>();
		List<String> funCodes = new ArrayList<>();
		if (StringUtils.isNotEmpty(id)) {
			RoleEntity role = roleService.getRoleById(id);
			result.put("role", role);
			// 修改设置 角色已选全新啊
			if (role == null) {
				throw new BusinessException("修改角色已经不存在了");
			}
			List<RolePrivilegeEntity> rolePrivilegeEntities = roleService
					.getRoleFunctionList(role.getRoleCode());
			if (rolePrivilegeEntities != null && !rolePrivilegeEntities.isEmpty()) {
				for (RolePrivilegeEntity rf : rolePrivilegeEntities) {
					funCodes.add(rf.getFunctionCode());
				}
			}
		}
		List<TreeVo> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		List<PrivilegeEntity> functionEntityList = functionService
				.getMenuService(map);
		for (PrivilegeEntity privilegeEntity : functionEntityList) {
			TreeVo t = new TreeVo();
			t.setId(privilegeEntity.getPrivilegeCode());
			t.setName(privilegeEntity.getFunctionName());
			if(FrontendConstants.node.indexOf (privilegeEntity.getModuleType())<0 ){
				t.setIsParent(true);
			}
			t.setpId(privilegeEntity.getParentCode());
			if("0".equals(privilegeEntity.getPrivilegeCode())){
				t.setOpen(true);
			}
			if (StringUtils.isNotEmpty(id) && funCodes.size() > 0) {
				// 修改设置 角色已选全新啊
				if (funCodes.contains(privilegeEntity.getPrivilegeCode())) {
					t.setChecked(true);
					t.setOpen(true);
				}
			}
			list.add(t);
		}
		
		result.put("tree", list);
		return returnSuccess(result);
	}

	@RequestMapping("/checkRoleCode")
	@ResponseBody
	public ResultEntity checkRoleCode(String roleCode, String id) {
		if (StringUtils.isEmpty(id)) {
			boolean flag = roleService.checkRoleCode(roleCode);
			if (!flag) {
				return returnException("角色编码名已被占用");
			} else {
				return returnSuccess("角色编码可用");
			}
		}
		return returnSuccess();
	}

	/**
	 * @Description:添加角色
	 */
	@RequestMapping("/editRole")
	@ResponseBody
	public ResultEntity addRole(@Valid RoleEntity roleEntity,BindingResult bindingResult, String privilegeCodes) {
		
		validator(bindingResult);
		
		String  msg ="角色添加成功!";
		if(StringUtils.isNotEmpty(roleEntity.getId())){
			msg = "角色修改成功!";
		}
		roleService.addRole(roleEntity, privilegeCodes);
		return returnSuccess(msg, roleEntity.getId());
	}

	/**
	 * @Description:删除角色
	 */
	@RequestMapping("/deleteRole/{id}")
	@ResponseBody
	public ResultEntity deleteRole(@PathVariable("id") String id) {
		String[] ids = id.split(",");
		int num = roleService.deleteRoleById(Arrays.asList(ids));
		if (num == 0) {
			return returnException("数据不存在，请重新选择一条!");
		} else {

			return returnSuccess("角色删除成功！");
		}
	}

	/**
	 * @Description:修改角色
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public ResultEntity updateRole(String roleCode,String[] privilegeCodes) {
		int num = roleService.updateRole(roleCode,privilegeCodes);
		if (num > 0) {
			return returnSuccess("角色修改成功！", num);
		} else {
			return returnSuccess("请先添加再执行修改！", num);
		}
	}

	/**
	 * @Description:进入到查询列表页面
	 */
	@RequestMapping("/roleList")
	public String roleList(Model model) {
		//查询全部权限
		List<TreeVo> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		List<PrivilegeEntity> functionEntityList = functionService
				.getMenuService(map);
		for (PrivilegeEntity privilegeEntity : functionEntityList) {
			TreeVo t = new TreeVo();
			t.setId(privilegeEntity.getPrivilegeCode());
			t.setName(privilegeEntity.getFunctionName());
			if(FrontendConstants.node.indexOf (privilegeEntity.getModuleType())<0 ){
				t.setIsParent(true);
			}
			t.setpId(privilegeEntity.getParentCode());
			if("0".equals(privilegeEntity.getPrivilegeCode())){
				t.setOpen(true);
			}
			list.add(t);
		}
		model.addAttribute("tree", JsonUtil.toJson(list));
		return "/role/roleList";
	}

	/**
	 * @Description:分页查询角色列表
	 */
	@RequestMapping("getRoleList")
	@ResponseBody
	public Pagination<RoleEntity> getRoleList(@QueryPage QueryPageVo queryPageVo) {
		String compCode = UserContext.getCurrentUser().getCompCode();
		if(!CompConstants.YIMIDIDA.equals(compCode)){
			queryPageVo.getParaMap().put("compCode", compCode);
		}
		Pagination<RoleEntity> pageInfo = roleService.findRoleList(queryPageVo);
		return pageInfo;
	}

}
