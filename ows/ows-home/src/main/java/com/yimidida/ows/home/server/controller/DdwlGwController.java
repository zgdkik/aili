package com.yimidida.ows.home.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jetbrick.template.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.home.server.service.ICityService;
import com.yimidida.ows.home.server.service.IHtmlService;
import com.yimidida.ows.home.server.service.INoticeService;
import com.yimidida.ows.home.server.service.IRecruitService;
import com.yimidida.ows.home.share.entity.City;
import com.yimidida.ows.home.share.entity.Html;

@Controller
public class DdwlGwController extends AbstractController {
	@Autowired IHtmlService htmlService;
	@Autowired IRecruitService recruitService;
	@Autowired INoticeService noticeService;
	@Autowired ICityService cityService;
	
	
	//企业简介，企业理念，大事纪要
	@RequestMapping("/home")
	public String home(Model model,String roleCode,String title) {
		return "home";
	}
	//企业简介，企业理念，大事纪要
	@RequestMapping("/introduce")
	public String introduce(Model model,String roleCode,String title,HttpServletRequest request) {
		List<Html> list=htmlService.getHtmlByMenuId(roleCode);
		if(list.size()>0){
			model.addAttribute("htmlContent", list.get(0).getHtmlContent().replaceAll("/common", request.getContextPath()+"/common"));
			model.addAttribute("title", title);
		}
		return "introduce/introduce";
	}
	//文化生活  noticeType
	@RequestMapping("/culturalLife")
	public String culturalLife(Model model ,String noticeType,String title){
		model.addAttribute("noticeType", noticeType);
		model.addAttribute("title", title);
		return "introduce/culturalLife";
	}
	//文化生活  noticeType
	@RequestMapping("/culturalLifeDetail")
	public String culturalLifeDetail(Model model ,String noticeId,String title,String rowsId){
		model.addAttribute("noticeId", noticeId);
		model.addAttribute("rowsId", rowsId);
		model.addAttribute("title", title);
		return "introduce/culturalLifeDetail";
	}
	@RequestMapping("/productService")
	public String productService(Model model,String roleCode,String title,HttpServletRequest request) {
		List<Html> list=htmlService.getHtmlByMenuId(roleCode);
		if(list.size()>0){
			model.addAttribute("htmlContent", list.get(0).getHtmlContent().replaceAll("/common", request.getContextPath()+"/common"));
			model.addAttribute("title", title);
		}
		return "productService/productService";
	}
	//招聘信息
	@RequestMapping("/recruit")
	public String recruit(Model model,String type,String title) {
		model.addAttribute("title", title);
		return "recruit/recruit";
	}
	
	//获取招聘信息详情
	@RequestMapping("/queryRecruitDetail")
	public String queryRecruitDetail(Model model,String id,String title) {
		
		model.addAttribute("title", title);
		model.addAttribute("id",id);
		return "recruit/recruit_detail";
	}
	//优秀员工
	@RequestMapping("/goodEmployee")
	public String goodEmployee(Model model,String title,String noticeType) {
		model.addAttribute("title", title);
		model.addAttribute("noticeType", noticeType);
		return "recruit/goodEmployee";
	}	
	//新闻的上下页
	@RequestMapping("/goodEmployeeDetail")
	public String goodEmployeeDetail(Model model , String noticeId,String rowsId,String title){
		model.addAttribute("noticeId", noticeId);
		model.addAttribute("rowsId", rowsId);
		model.addAttribute("title", title);
		return "recruit/goodEmployeeDetail";
	}
	//注册页面
	@RequestMapping("/register")
	public String register(Model model,String type,String title) {
		model.addAttribute("title", title);
		return "register/register";
	}
	//公司新闻
	@RequestMapping("/notice")
	public String notice(Model model,String noticeType,String title) {
		model.addAttribute("noticeType", noticeType);
		model.addAttribute("title", title);
		return "notice/notice";
	}
	//新闻的上下页
	@RequestMapping("/queryNoticeDetail")
	public String queryNoticeDetail(Model model , String noticeId,String rowsId,String title){
		model.addAttribute("noticeId", noticeId);
		model.addAttribute("rowsId", rowsId);
		model.addAttribute("title", title);
		return "notice/noticeDetail";
	}
	//订单跟踪
	@RequestMapping("/waybillTrack")
	public String waybillTrack(Model model , String title,String waybillNos){
		model.addAttribute("waybillNos", waybillNos);
		model.addAttribute("title", title);
		return "customerCenter/waybillTrack";
	}
	//网点查询
	@RequestMapping("/deptSearch")
	public String deptSearch(Model model , String title,String city){
		model.addAttribute("title", title);
		if(StringUtils.isNotEmpty(city)){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("city", "%"+city+"%");
			List<City> list=cityService.getCityByName(params);
			if(list.size()>0){
				model.addAttribute("cityCode", list.get(0).getCode());
				model.addAttribute("provinceCode", list.get(0).getProvinceCode());
			}
		}
		
		return "customerCenter/deptSearch";
	}
	//预约订单
	@RequestMapping("/advanceOrder")
	public String advanceOrder(Model model , String title,HttpSession session){
		if(session.getAttribute("user")==null){
			model.addAttribute("title", "预约订单");
			return "register/register";
		}else{
			model.addAttribute("title", title);
			return "customerCenter/advanceOrder";
		}
		
	}
	//订单管理
	@RequestMapping("/orderManage")
	public String orderManage(Model model , String title,HttpSession session){
		if(session.getAttribute("user")==null){
			model.addAttribute("title", "订单管理");
			return "register/register";
		}else{
			model.addAttribute("title", title);
			return "customerCenter/orderManage";
		}
		
	}
	//联系我们
	@RequestMapping("/contactUs")
	public String contactUs(Model model , String title,String roleCode){
		List<Html> list=htmlService.getHtmlByMenuId(roleCode);
		if(list.size()>0){
			model.addAttribute("htmlContent", list.get(0).getHtmlContent());
			model.addAttribute("title", title);
		}
		return "customerCenter/contactUs";
	}
	//运价查询
	@RequestMapping("/queryFreight")
	public String queryFreight(Model model , String title,String sourceDistCode
			,String sourceDistValue,String destDistCode,String destDistValue){
		model.addAttribute("title", title);
		model.addAttribute("sourceDistCode", sourceDistCode);
		model.addAttribute("sourceDistValue", sourceDistValue);
		model.addAttribute("destDistCode", destDistCode);
		model.addAttribute("destDistValue", destDistValue);
		return "customerCenter/queryFreight";
	}
	//代收货款查询
	@RequestMapping("/queryCOD")
	public String queryCOD(Model model , String title,String waybillNo){
		if(StringUtils.isNotEmpty(waybillNo)){
			model.addAttribute("waybillNo", waybillNo);
		}
		model.addAttribute("title", title);
		return "customerCenter/queryCOD";
	}

	//首页banner
	@RequestMapping("/pageBanner")
	public String pageBanner(Model model,HttpServletRequest request,String title) {
		String bannerId = request.getParameter("bannerId");
		model.addAttribute("bannerId",bannerId);
		model.addAttribute("title", title);
		return "pageBanner/pageBanner";
	}
	//收寄人管理
	@RequestMapping("/owsUserPeople")
	public String userPeople(Model model , String title,String userType,HttpSession session){
		if(session.getAttribute("user")==null){
			model.addAttribute("title", "收寄人管理");
			return "register/register";
		}else{
			model.addAttribute("title",title);
			model.addAttribute("userType",userType);
			return "customerCenter/owsUserPeople";
		}
	}
	
	//修改密码
	@RequestMapping("/changePassPord")
	public String changePassPord(Model model,String title,HttpSession session){
		if(session.getAttribute("user")==null){
			model.addAttribute("title", "修改密码");
			return "register/register";
		}else{
			model.addAttribute("title",title);
			return "customerCenter/changePassPord";
		}
		
	}
	
	//忘记密码
	@RequestMapping("/forgetPassPord")
	public String forgetPassPord(Model model,String title){
		model.addAttribute("title",title);
		return "online/findback";
	}
	
	//忘记密码附属页面
	@RequestMapping("/forgetPassPord_0")
	public String forgetPassPord_0(Model model,String title ,HttpServletRequest request){
		model.addAttribute("title",title);
		request.setAttribute("owsUser",request.getSession().getAttribute("owsUser"));
		return "online/findback_o";
	}
	
	@RequestMapping("/forgetPassPord_t")
	public String forgetPassPord_t(Model model,String title){
		model.addAttribute("title",title);
		return "online/findback_t";
	}
	
	//个人资料
	@RequestMapping("/personalData")
	public String personalData(Model model,String title,HttpSession session){
		if(session.getAttribute("user")==null){
			model.addAttribute("title", "个人资料");
			return "register/register";
		}else{
			model.addAttribute("title", title);
			return "customerCenter/personalData";
		}
		
	}
	
	//投诉建议
	@RequestMapping("/tsjyJSP")
	public String tsjy(Model model,String title,HttpSession session){
		//暂时做成不登录的，如果需要登录就解注注释的代码
//		if(session.getAttribute("user")==null){
//			model.addAttribute("title", "投诉建议");
//			return "register/register";
//		}else{
			model.addAttribute("title", title);
			return "customerCenter/tsjy";
//		}
	}
	
}
