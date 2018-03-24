package com.yimidida.ows.home.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jetbrick.template.utils.StringUtils;

import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.IRecruitService;
import com.yimidida.ows.home.share.entity.Recruit;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

@Controller
@RequestMapping("/recruit")
public class RecruitController extends AbstractController {
	@Autowired IRecruitService recruitService;
	
	@RequestMapping("/getAllrecruit")
	@ResponseBody
	public Pagination<Recruit> getAllrecruit(Model model,@QueryPage  QueryPageVo queryPageVo ){
		Pagination<Recruit> pageOrder=recruitService.getAllrecruit(queryPageVo.getPage());
		return pageOrder;
	}
	@RequestMapping("/insertOrUpdateRecruit")
	@ResponseBody
	public ResultEntity insertOrUpdateRecruit(Model model,Recruit r) {
		int flag=0;
		String userName=UserContext.getCurrentUser().getUserName();
		Date now=new Date();
		if(StringUtils.isNotEmpty(r.getId())){
			r.setChangeDate(now);
			r.setChangeUser(userName);
			flag=recruitService.update(r);
		}else{
			r.setCreateDate(now);
			r.setChangeUser(userName);
			r.setChangeDate(now);
			r.setChangeUser(userName);
			r.setId(UuidUtil.getUuid());
			r.setRecruitStatus(1);
			//r.setCompCode(UserContext.getCurrentUser().getCompCode());
			r.setCompCode("ddwl");
			flag=recruitService.insert(r);
		}
		return returnSuccess(flag);
	}
	//获取招聘信息
	@RequestMapping("/queryRecruitList")
	@ResponseBody
	public ResultEntity queryRecruitList(String compCode,int page,int rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		Pagination<Recruit> pg=recruitService.queryRecruitList(compCode,page,rows);
		map.put("rows", pg.getList());
		map.put("total", pg.getCount());
		return returnSuccess(map);
	}
	//删除招聘信息
	@RequestMapping("/deleteRecruit")
	@ResponseBody
	public ResultEntity deleteRecruit(String recruitId) {
		int flag=0;
		flag=recruitService.deleteById(recruitId);
		return returnSuccess(flag);
	}
	//删除招聘信息
		@RequestMapping("/getRecruitById")
		@ResponseBody
		public ResultEntity getRecruitById(String recruitId) {
			
			
			Recruit r=recruitService.getById(recruitId);
			return returnSuccess(r);
		}
}
