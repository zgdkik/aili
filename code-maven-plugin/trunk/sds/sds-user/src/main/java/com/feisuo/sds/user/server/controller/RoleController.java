package com.feisuo.sds.user.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.base.server.annotation.QueryPage;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.base.share.vo.TreeVo;
import com.feisuo.sds.user.server.service.IPrivilegeService;
import com.feisuo.sds.user.server.service.IRoleService;
import com.feisuo.sds.user.share.entity.PrivilegeEntity;
import com.feisuo.sds.user.share.entity.RoleEntity;
import com.feisuo.sds.user.share.entity.RolePrivilegeEntity;

/**
 * @author fanhoutao
 */

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPrivilegeService functionService;

	/**
	 * @Description:跳转到添加页面
	 */
	@RequestMapping("/showRoleForm")
	public String showRole(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (StringUtils.isNotEmpty(id)) {
			RoleEntity role = roleService.getRoleById(id);
			model.addAttribute("role", role);
			model.addAttribute("title", "修改角色");
		} else {
			model.addAttribute("title", "新增角色");
		}
		List<String> funCodes = new ArrayList<>();
		if (StringUtils.isNotEmpty(id)) {
			// 修改设置 角色已选全新啊
			RoleEntity role = roleService.getRoleById(id);
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
		model.addAttribute("id", id);
		List<TreeVo> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("active", "Y");
		List<PrivilegeEntity> functionEntityList = functionService
				.getMenuService(map);
		for (PrivilegeEntity privilegeEntity : functionEntityList) {
			TreeVo t = new TreeVo();
			t.setId(privilegeEntity.getPrivilegeCode());
			t.setName(privilegeEntity.getFunctionName());
			if ("Y".equals(privilegeEntity.getFunctionType())) {
				t.setIsParent(true);
			}
			t.setpId(privilegeEntity.getParentCode());
			if (StringUtils.isNotEmpty(id) && funCodes.size() > 0) {
				// 修改设置 角色已选全新啊
				if (funCodes.contains(privilegeEntity.getPrivilegeCode())) {
					t.setChecked(true);
				}
			}
			t.setOpen(true);
			list.add(t);
		}
		model.addAttribute("funs", JsonUtil.toJson(list));
		return "/role/addRole";
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
	 * @Author fanhoutao
	 * @date 2015年11月25日
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public ResultEntity addRole(RoleEntity roleEntity, String funCodes) {
		String  msg ="角色添加成功!";
		if(StringUtils.isNotEmpty(roleEntity.getId())){
			msg = "角色修改成功!";
		}
		int num = roleService.addRole(roleEntity, funCodes);
		return returnSuccess(msg, roleEntity.getId());
	}

	/**
	 * @Description:删除角色
	 * @Author fanhoutao
	 * @date 2015年11月25日
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
	 * @Author fanhoutao
	 * @date 2015年11月25日
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public ResultEntity updateRole(RoleEntity role) {
		int num = roleService.updateRole(role);
		if (num > 0) {
			return returnSuccess("角色修改成功！", num);
		} else {
			return returnSuccess("请先添加再执行修改！", num);
		}
	}

	/**
	 * @Description:进入到查询列表页面
	 */
	@RequestMapping("roleList")
	public String findRoleList() {
		return "/role/role";
	}

	/**
	 * @Description:分页查询角色列表
	 * @Author fanhoutao
	 * @date 2015年11月25日
	 */
	@RequestMapping("getRoleList")
	@ResponseBody
	public Pagination<RoleEntity> getRoleList(@QueryPage QueryPageVo queryPageVo) {
		Pagination<RoleEntity> pageInfo = roleService.findRoleList(queryPageVo);
		return pageInfo;
	}

}
