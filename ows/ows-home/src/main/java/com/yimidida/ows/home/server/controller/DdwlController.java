package com.yimidida.ows.home.server.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;

@Controller
@RequestMapping("/ddwl")
public class DdwlController extends AbstractController {

	@RequestMapping("/introduce")
	public String introduce(Model model,String roleCode) {
		model.addAttribute("roleCode", roleCode);
		return "introduce";
	}
	@RequestMapping("/noticeManage")
	public String notice(Model model,String type) {
		model.addAttribute("type", type);
		return "noticeManage";
	}
	@RequestMapping("/recruitManage")
	public String recruitManage(Model model) {
		return "recruitManage";
	}
	@RequestMapping("/noticeCapacity")
	public String noticeCapacity(Model model,String noticeId,String noticeType) {
		model.addAttribute("noticeId", noticeId);
		model.addAttribute("noticeType", noticeType);
		return "notice_edit";
	}
	@RequestMapping("/homePage")
	public String banner(Model model,String bannerType){
		model.addAttribute("bannerType",bannerType);
		return "homePage";
	}
	@RequestMapping("/homePageCapacity")
	public String homePageCapacity(Model model,String bannerId,String bannerType){
		model.addAttribute("bannerId",bannerId);
		model.addAttribute("bannerType",bannerType);
		return "homePageCapacity";
	}
	@RequestMapping("/leagueManage")
	public String leagueManage(Model model,String leagueId){
		return "leagueManage";
	}
	
	@RequestMapping("/league_edit")
	public String league_edit(Model model,String leagueId){
		if(StringUtils.isNotEmpty(leagueId)){
			model.addAttribute("leagueId", leagueId);
		}
		return "league_edit";
	}
	//投诉建议
	@RequestMapping("/complaintAdviceManage")
	public String complaintAdviceManage(Model model){
		return "complaintAdviceManage";
	}
	//客户回复
	@RequestMapping("/customerReply")
	public String customerReply(Model model,String id){
		model.addAttribute("id", id);
		return "customerReply";
	}
	
	//图片管理
	@RequestMapping("/imgManage")
	public String imgManage(Model model){
		return "imgManage";
	}
	//图片管理编辑
	@RequestMapping("/imgManage_edit")
	public String imgManage_edit(Model model,String imgId){
		model.addAttribute("imgId",imgId);
		return "imgManage_edit";
	}
}
