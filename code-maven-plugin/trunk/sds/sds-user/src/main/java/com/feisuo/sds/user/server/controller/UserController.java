package com.feisuo.sds.user.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.base.server.annotation.QueryPage;
import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.server.controller.AbstractController;
import com.feisuo.sds.base.share.entity.IUser;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.base.share.vo.SearchSelectVo;
import com.feisuo.sds.user.server.service.IRoleService;
import com.feisuo.sds.user.server.service.IUserService;
import com.feisuo.sds.user.share.entity.RoleEntity;
import com.feisuo.sds.user.share.entity.UserEntity;
/**
 * @ClassName: UserController
 * @Description:用户管理
 * @author nizhenghua
 * @date 2015年11月25日 下午4:46:14
 */
@Controller
@RequestMapping("user")
public class UserController extends AbstractController{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	
	/**
	 * @Description:跳转用户新增页面
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:07:45
	 */
	@RequestMapping("/toadduser")
	public String toAddUser(Model model){
		IUser user = UserContext.getCurrentUser();
		
		Map<String, Object> paraMap = new HashMap<String,Object>();
		List<RoleEntity> list = roleService.getRoleList(paraMap);
		if(!"admin".equals(user.getUserName())){
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
	 * @Author:nizhenghua
	 * @date 2015-12-4下午6:55:25
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public ResultEntity checkUserName(String userName){
		boolean flag = userService.checkUserName(userName);
		if(!flag){
			return returnException("用户名已被占用");
		}else{
			return returnSuccess("用户名可以使用");
		}
	}
	
	/**
	 * @Description:校验用户名是否存在
	 * @Author:nizhenghua
	 * @date 2015-12-4下午6:55:25
	 */
	@RequestMapping("/checkEnterpriseAccount")
	@ResponseBody
	public ResultEntity checkEnterpriseName(String enterpriseAccount){
		boolean flag = userService.checkUserName(enterpriseAccount);
		if(!flag){
			return returnException("用户名已被占用");
		}else{
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
	public ResultEntity addUser(@RequestBody UserEntity user){
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
	public ResultEntity deleteUser(@PathVariable String id){
		int count = userService.deleteById(id);
		if(count==0){
			return returnSuccess("删除失败");
		}else{
			return returnSuccess("删除成功");
		}
	}
	
	/**
	 * @Description:跳转到用户修改页面
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:09:19
	 */
	@RequestMapping("/toupdateuser")
	public String toUpdateUser(String id,Model model){
		//判断修改的用户是否是客户账号
		//如果是只能选择客户角色
		UserEntity user = userService.queryUserInfoById(id);
		IUser user2 = UserContext.getCurrentUser();
		int type = user.getType();
		Map<String, Object> paraMap = new HashMap<String,Object>();
		if(type == 2 || type ==3){
			paraMap.put("roleType", "customer");
		}
		List<RoleEntity> list = roleService.getRoleList(paraMap);
		if(!"admin".equals(user2.getUserName())){
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
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:09:53
	 */
	@RequestMapping("/updateuser")
	@ResponseBody
	public ResultEntity updateUser(@RequestBody UserEntity user){
		userService.update(user);
		return returnSuccess("修改成功");
	}
	
	/**
	 * @Description:冻结用户
	 * @Author:nizhenghua
	 * @date 2015-12-4下午7:26:24
	 */
	@RequestMapping("/lockuser/{id}")
	@ResponseBody
	public ResultEntity lockUser(@PathVariable String id){
		userService.updateStatusById(id, 2);
		return returnSuccess("已禁用");
	}
	/**
	 * @Description:解禁用户
	 * @Author:nizhenghua
	 * @date 2015-12-4下午7:26:41
	 */
	@RequestMapping("/unlockuser/{id}")
	@ResponseBody
	public ResultEntity unLockUser(@PathVariable String id){
		userService.updateStatusById(id, 1);
		return returnSuccess("已解禁");
	}
	
	/**
	 * @Description:跳转到用户列表页面
	 * @Author:nizhenghua
	 * @date 2015-12-2上午10:10:12
	 */
	@RequestMapping("userlist")
	public String getUserList(){
		return "/user/userlist";
	}
	
	/**
	 * @Description:异步查询用户列表分页
	 * @Author nizhenghua
	 * @date 2015-12-2上午10:10:50
	 */
	@RequestMapping("/getuserlist")
	@ResponseBody
	public Pagination<UserEntity> findUserList(@QueryPage QueryPageVo queryPageVo){
		Pagination<UserEntity> pageInfo = userService.getPage(queryPageVo);
		return pageInfo;
	}
	
	
}
