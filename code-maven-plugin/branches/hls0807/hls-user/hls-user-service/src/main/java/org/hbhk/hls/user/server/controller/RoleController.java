package org.hbhk.hls.user.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.hls.user.server.service.IPrivilegeService;
import org.hbhk.hls.user.server.service.IRoleService;
import org.hbhk.hls.user.share.entity.PrivilegeEntity;
import org.hbhk.hls.user.share.entity.RoleEntity;
import org.hbhk.hls.user.share.entity.RolePrivilegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		if (StringUtils.isEmpty(id)) {
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
		Map<String, Object> result = new HashMap<String, Object>();
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
			if (rolePrivilegeEntities != null
					&& !rolePrivilegeEntities.isEmpty()) {
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
			t.setName(privilegeEntity.getPrivilegeName());
			if (FrontendConstants.node.indexOf(privilegeEntity.getType()) < 0) {
				t.setIsParent(true);
			}
			t.setpId(privilegeEntity.getParentCode());
			if ("0".equals(privilegeEntity.getPrivilegeCode())) {
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
	public ResultEntity addRole(@Valid RoleEntity roleEntity,
			BindingResult bindingResult, String privilegeCodes) {

		validator(bindingResult);

		String msg = "角色添加成功!";
		if (StringUtils.isNotEmpty(roleEntity.getId())) {
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
	public ResultEntity updateRole(String roleCode, String[] privilegeCodes) {
		int num = roleService.updateRole(roleCode, privilegeCodes);
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
		// 查询全部权限
		List<TreeVo> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		List<PrivilegeEntity> functionEntityList = functionService
				.getMenuService(map);
		for (PrivilegeEntity privilegeEntity : functionEntityList) {
			TreeVo t = new TreeVo();
			t.setId(privilegeEntity.getPrivilegeCode());
			t.setName(privilegeEntity.getPrivilegeName());
			if (FrontendConstants.node.indexOf(privilegeEntity.getType()) < 0) {
				t.setIsParent(true);
			}
			t.setpId(privilegeEntity.getParentCode());
			if ("0".equals(privilegeEntity.getPrivilegeCode())) {
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
		Pagination<RoleEntity> pageInfo = roleService.findRoleList(queryPageVo);
		return pageInfo;
	}

}
