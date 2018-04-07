package com.yimidida.ows.home.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.home.server.service.IMenuService;
import com.yimidida.ows.home.share.vo.MenuVo;
import com.yimidida.ows.user.server.service.IPrivilegeService;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractController {
	@Autowired IPrivilegeService privilegeService;
	@Autowired IMenuService menuService;
	
	//注册短信
	@RequestMapping("getMenu")
	@ResponseBody
	public ResultEntity getPhoneCode(String compCode) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("isWebMenu", 1);
		params.put("parentCode", "daiyun");
		List<PrivilegeEntity> list=privilegeService.getMenuService(params);
		
		params.clear();
		params.put("privilegeCode", "daiyun");

		List<PrivilegeEntity> list1=privilegeService.getMenuService(params);
		list.addAll(list1);
		MenuVo m=menuService.getMenuVo(list);
		return returnSuccess(m);
	}
}
