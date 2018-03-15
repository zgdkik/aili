/**
 * GetEnpController.java
 * Created at 2016年1月6日
 * Created by lixiang
 * Copyright (C) 2016 NO.1 VAN, All rights reserved.
 */
package com.feisuo.sds.common.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.base.server.annotation.QueryPage;
import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.entity.IUser;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.base.share.vo.SearchSelectVo;
import com.feisuo.sds.common.server.service.IEnpService;
import com.feisuo.sds.common.share.vo.EnpInfoVo;

/**
 * ClassName: GetEnpController
 * Description: 根据客户名称查询客户信息
 * Author: lixiang
 * Date: 2016年1月6日
 */
@Controller
@RequestMapping(value="/enterprise")
public class GetEnpController {
	@Autowired
	IEnpService enpService;
	
	@RequestMapping("/getEnpName")
	@ResponseBody
	public Pagination<SearchSelectVo> getSubName(Model model,@QueryPage QueryPageVo queryPageVo) {
		String enpName = (String) queryPageVo.getParaMap().get("name");
		Pagination<SearchSelectVo> pagination = new Pagination<>();
		IUser user =  UserContext.getCurrentUser();
		List<EnpInfoVo> list = new ArrayList<EnpInfoVo>();
		List<SearchSelectVo> sList = new ArrayList<SearchSelectVo>();
		if(user.getUserType() != null && "1".equals(user.getUserType())){
			list = this.enpService.findByEnpName(enpName);
			SearchSelectVo sso = null;
			for (EnpInfoVo enpInfoVo : list) {
				sso = new SearchSelectVo();
				sso.setId(enpInfoVo.getEnpAccount());
				sso.setName(enpInfoVo.getEnpName());
				sList.add(sso);
			}
			pagination.setList(sList);
		}
		return pagination;
	}
}
