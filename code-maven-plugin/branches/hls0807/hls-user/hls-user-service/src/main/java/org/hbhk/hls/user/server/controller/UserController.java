package org.hbhk.hls.user.server.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.entity.IUser;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.hls.user.server.service.IRoleService;
import org.hbhk.hls.user.server.service.IUserRoleService;
import org.hbhk.hls.user.server.service.IUserService;
import org.hbhk.hls.user.share.entity.RoleEntity;
import org.hbhk.hls.user.share.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:用户管理
 */
@Controller
@RequestMapping("user")
public class UserController extends AbstractController {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleService userRoleService;

	/**
	 * @Description:跳转用户新增页面
	 */
	@RequestMapping("/toadduser")
	public String toAddUser(Model model) {
		IUser user = UserContext.getCurrentUser();

		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<RoleEntity> list = roleService.getRoleList(paraMap);
		if (!"admin".equals(user.getUserName())) {
			for (int i = 0; i < list.size(); i++) {
				if ("超级管理员".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if ("总账户".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if ("子账户".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if (!user.getRoleCodes().contains("admin")) {
					if ("otheradmin".equals(list.get(i).getRoleCode())) {
						list.remove(list.get(i));
					}
				}

			}
		}
		model.addAttribute("list", list);
		return "/user/adduser";
	}

	/**
	 * @Description:校验用户名是否存在
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public ResultEntity checkUserName(String userName) {
		boolean flag = userService.checkUserName(userName);
		if (!flag) {
			return returnException("用户名已被占用");
		} else {
			return returnSuccess("用户名可以使用");
		}
	}

	/**
	 * @Description:校验用户名是否存在
	 */
	@RequestMapping("/checkEnterpriseAccount")
	@ResponseBody
	public ResultEntity checkEnterpriseName(String enterpriseAccount) {
		boolean flag = userService.checkUserName(enterpriseAccount);
		if (!flag) {
			return returnException("用户名已被占用");
		} else {
			return returnSuccess("用户名可以使用");
		}
	}

	/**
	 * @Description:以json格式接收新增用户
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:06:53
	 */
	@RequestMapping("/adduser")
	@ResponseBody
	public ResultEntity addUser(@RequestBody UserEntity user) {
		userService.insert(user);
		return returnSuccess("用户增加成功");
	}

	/**
	 * @Description:删除用户
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:08:53
	 */
	@RequestMapping("/deleteuser/{id}")
	@ResponseBody
	public ResultEntity deleteUser(@PathVariable String id) {
		int count = userService.deleteById(id);
		if (count == 0) {
			return returnSuccess("删除失败");
		} else {
			return returnSuccess("删除成功");
		}
	}

	/**
	 * @Description:跳转到用户修改页面
	 */
	@RequestMapping("/toupdateuser")
	public String toUpdateUser(String id, Model model) {
		// 判断修改的用户是否是客户账号
		// 如果是只能选择客户角色
		UserEntity user = userService.queryUserInfoById(id);
		IUser user2 = UserContext.getCurrentUser();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<RoleEntity> list = roleService.getRoleList(paraMap);
		if (!"admin".equals(user2.getUserName())) {
			for (int i = 0; i < list.size(); i++) {
				if ("超级管理员".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if ("总账户".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if ("子账户".equals(list.get(i).getRoleName())) {
					list.remove(list.get(i));
				}
				if (!user2.getRoleCodes().contains("admin")) {
					if ("otheradmin".equals(list.get(i).getRoleCode())) {
						list.remove(list.get(i));
					}
				}

			}
		}

		model.addAttribute("list", list);
		model.addAttribute("user", user);
		return "/user/updateuser";
	}

	/**
	 * @Description:修改用户信息
	 */
	@RequestMapping("/updateuser")
	@ResponseBody
	public ResultEntity updateUser(@RequestBody UserEntity user) {
		userService.update(user);
		return returnSuccess("修改成功");
	}

	/**
	 * @Description:冻结用户
	 */
	@RequestMapping("/lockuser/{id}")
	@ResponseBody
	public ResultEntity lockUser(@PathVariable String id) {
		userService.updateStatusById(id, 2);
		return returnSuccess("已禁用");
	}

	/**
	 * @Description:解禁用户
	 */
	@RequestMapping("/unlockuser/{id}")
	@ResponseBody
	public ResultEntity unLockUser(@PathVariable String id) {
		userService.updateStatusById(id, 1);
		return returnSuccess("已解禁");
	}

	/**
	 * @Description:跳转到用户列表页面
	 */
	@RequestMapping("userlist")
	public String getUserList() {
		return "/user/userlist";
	}

	/**
	 * @Description:异步查询用户列表分页
	 */
	@RequestMapping("/getuserlist")
	@ResponseBody
	public Pagination<UserEntity> findUserList(
			@QueryPage QueryPageVo queryPageVo) {
		Pagination<UserEntity> pageInfo = userService.getPage(queryPageVo);
		return pageInfo;
	}

	@RequestMapping("/grain/{userName}")
	@ResponseBody
	public ResultEntity getUserRoe(@PathVariable String userName) {
		return returnSuccess("获取成功",
				userRoleService.getRoleIdByUserName(userName));
	}

	@RequestMapping("/grain")
	@ResponseBody
	public ResultEntity grain(String userName, String[] roleCodes) {

		userRoleService.addRoleRe(Arrays.asList(roleCodes), userName);
		return returnSuccess("授权成功");
	}

}
