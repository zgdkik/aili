package com.yimidida.ows.home.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.IHtmlService;
import com.yimidida.ows.home.share.entity.Html;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

/**
 * 新闻动态的web层
 * @author zhangm
 *
 */
@Controller
@RequestMapping("/html")
public class HtmlController extends AbstractController {
	@Autowired IHtmlService htmlService;
	
	
	
	@RequestMapping("/updateHtml")
	@ResponseBody
	public ResultEntity introduce(Model model,String htmlContent,String menuId) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("menuId", menuId);
		List<Html> list=htmlService.getHtmlByMenuId(menuId);
		int flag=0;
		if(list.size()>0){
			Html html=list.get(0);
			html.setHtmlContent(htmlContent);
			html.setChangeDate(new Date());
			flag=htmlService.update(html);
		}
		else{
			Html html=new Html();
			html.setHtmlId(UuidUtil.getUuid());
			html.setCompCode(UserContext.getCurrentUser().getCompCode());
			html.setChangeUser(UserContext.getCurrentUser().getUserName());
			html.setHtmlContent(htmlContent);
			html.setChangeDate(new Date());
			html.setMenuId(menuId);
			flag=htmlService.insert(html);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag==1){
			map.put("msg", "保存成功");
			map.put("success", true);
		}else{
			map.put("msg", "保存失败");
			map.put("success", true);
		}
		return returnSuccess(map);
	}
	@RequestMapping("/getHtmlByMenuId")
	@ResponseBody
	public ResultEntity getHtmlByMenuId(Model model,String menuId) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("menuId", menuId);
		List<Html> list=htmlService.getHtmlByMenuId(menuId);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()>0){
			map.put("success", true);
			map.put("htmlContent", list.get(0).getHtmlContent());
		}else{
			map.put("success", false);
		}
			
		return returnSuccess(map);
	}
}
