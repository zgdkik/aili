package com.yimidida.ows.home.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.IUserPeopleService;
import com.yimidida.ows.home.share.entity.OwsUser;
import com.yimidida.ows.home.share.entity.UserPeople;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

import jetbrick.template.utils.JSONUtils;

/**
 * 收寄货人信息地址-web
 * @author zhangm
 *
 */
@Controller
@RequestMapping("userPeople")
public class OwsUserPeopleController extends AbstractController{

	@Autowired
	private IUserPeopleService userPeopleService;
	
	
	/**
	 * 前台-查询收寄人信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllUserPeople")
	@ResponseBody
	public ResultEntity getAllUserPeople(HttpSession session){
		OwsUser user=(OwsUser)session.getAttribute("user"); //获取当前用户信息-必须为登录状态
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("userId", user.getId());//获取当前用户的id
		List<UserPeople> list = userPeopleService.getByType(param);
		return returnSuccess(list);
	}
	
	/**
	 * 前台-收寄人信息新增 修改
	 * @param model
	 * @param u
	 * @param session
	 * @return
	 */
	@RequestMapping("/addOrUpdateUserPeople")
	@ResponseBody
	public ResultEntity addUserPeople(Model model,UserPeople u,HttpSession session){
		int flag = 0;
		Date now = new Date();
		OwsUser user=(OwsUser)session.getAttribute("user"); //获取当前用户信息-必须为登录状态
		if(StringUtils.isNotEmpty(u.getId())){
			u.setChangeTime(now);
			flag=userPeopleService.update(u);
			}else{
			u.setCreateTime(now);
			u.setChangeTime(now);
			u.setUserId(user.getId());
			u.setId(UuidUtil.getUuid());
			u.setCompCode("ddwl");
			flag = userPeopleService.insert(u);
		}
		return returnSuccess(flag);
	}
	
	/**
	 * 前台-收寄人管理的删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUserPeople")
	@ResponseBody
	public ResultEntity deleteUserPeople(String id){
		int flag = userPeopleService.deleteById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag==1){
			map.put("msg", "删除成功");
			map.put("success", true);
		}else{
			map.put("msg", "删除失败");
			map.put("success", false);
		}
		return returnSuccess(map);
	}
}