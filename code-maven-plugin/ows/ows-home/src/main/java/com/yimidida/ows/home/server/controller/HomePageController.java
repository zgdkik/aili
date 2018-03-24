package com.yimidida.ows.home.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.yimidida.ows.home.server.service.IHomePageService;
import com.yimidida.ows.home.share.entity.HomePage;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

/**
 * 官网 - banner展示
 * @author zhangm
 *
 */
@Controller
@RequestMapping("/homePage")
public class HomePageController extends AbstractController{
	
	@Autowired
	private IHomePageService homePageService;
	
	//查询所有banner数据
	@RequestMapping("/getAllBanner")
	@ResponseBody
	public Pagination<HomePage> getAllBanner(Model model,@QueryPage  QueryPageVo queryPageVo ){
		Pagination<HomePage> pageOrder=homePageService.getPagination(queryPageVo.getParaMap(), queryPageVo.getPage(), null);
		return pageOrder;
	}
	
	/**
	 * 后台-添加修改图banner
	 * @param notice
	 * @return
	 */
	@RequestMapping("/addOrUpdateHomePage")
	@ResponseBody
	public ResultEntity addOrUpdateHomePage(Model model,HomePage h){
		int flag=0;
		String userName=UserContext.getCurrentUser().getUserName();        
		Date now=new Date();
		if(StringUtils.isNotEmpty(h.getId())){
			h.setChangeDate(now);
			h.setChangeUser(userName);
			flag=homePageService.update(h);
		}else{
			h.setId((UuidUtil.getUuid()));
			String url = "/ddwlGw/pageBanner?&title="+h.getSubTitle()+"&bannerId="+h.getId();
			h.setBannerUrl(url);
			h.setCreateDate(now);
			h.setCreateUser(userName);
			h.setChangeDate(now);
			h.setChangeUser(userName);
			h.setCompCode(UserContext.getCurrentUser().getCompCode());
			h.setCompCode("ddwl");
			flag=homePageService.insert(h);
		}
		return returnSuccess(flag);
	}
	/**
	 * 后台-banner修改时的回显
	 * @param bannerId
	 * @return
	 */
	@RequestMapping("/getHomePageById")
	@ResponseBody
	public ResultEntity getHomePageById(String id){
		HomePage homePage = homePageService.getById(id);
		return returnSuccess(homePage);
	}
	/**
	 * 后台-banner删除
	 * @param bannerId
	 * @return
	 */
	@RequestMapping("/deleteBannerById")
	@ResponseBody
	public ResultEntity deleteBannerById(String bannerId){
		int flag = homePageService.deleteById(bannerId);
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
	
	/**
	 * 前台-展示banner
	 * @return
	 */
	@RequestMapping("getHomeBanner")
	@ResponseBody
	public ResultEntity getHomeBanner(String bannerType){   //根据type进行展示
		//点击登录首页、type为1的所有符合标准的数据发送给前台  
		List<HomePage> list = homePageService.getHomeBanner(bannerType);
		return returnSuccess(list);
	}
}
