package com.yimidida.ows.home.server.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.INoticeService;
import com.yimidida.ows.home.share.entity.Notice;
import com.yimidida.ymdp.core.share.entity.ResultEntity;


/**
 * 新闻动态的web层
 * @author zhangm
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends AbstractController{
	
	@Autowired
	private INoticeService noticeService;
	
	/**
	 * 后台-查询所有
	 * @param model
	 * @param queryPageVo
	 * @return
	 */
	@RequestMapping("/getAllNotice")
	@ResponseBody
	public Pagination<Notice> getAllNotice(Model model,@QueryPage  QueryPageVo queryPageVo ){
		Pagination<Notice> pageOrder=noticeService.getAllNotice(queryPageVo.getParaMap(), queryPageVo.getPage(), null);
		return pageOrder;
	}
	
	/**
	 * 后台-增加修改新闻
	 * @param notice
	 * @return
	 */
	@RequestMapping("/addOrUpdateNotice")
	@ResponseBody
	public ResultEntity addOrUpdateNotice(Model model,Notice n){
		int flag=0;
//		System.out.println(n.getNoticeImg());
		String userName=UserContext.getCurrentUser().getUserName();        
		Date now=new Date();
		if(StringUtils.isNotEmpty(n.getId())){
			n.setChangeDate(now);
			n.setChangeUser(userName);
			flag=noticeService.update(n);
		}else{
			n.setCreateDate(now);
			n.setChangeUser(userName);
			n.setChangeDate(now);
			n.setChangeUser(userName);
			n.setId(UuidUtil.getUuid());
			n.setNoticeStatus("1");
			n.setCompCode(UserContext.getCurrentUser().getCompCode());
			n.setCompCode("ddwl");
			flag=noticeService.insert(n);
		}
		return returnSuccess(flag);
	}
	/**
	 * 后台-关于修改新闻时的新闻回显
	 * @param model
	 * @param noticeId
	 * @return
	 */
	@RequestMapping("/getNoticeById")
	@ResponseBody
	public ResultEntity getNoticeById(String noticeId) {
		Map<String, Object> param=new HashMap<String, Object>();
//		param.put("noticeId", noticeId);
		Notice notice=noticeService.getById(noticeId);
		return returnSuccess(notice);
	}

	/**
	 * 后台-更改状态-删除
	 * @param noticeId
	 * @return
	 */
	@RequestMapping("/deleteStatusByid")
	@ResponseBody
	public ResultEntity deleteStatusByid(String noticeId){
		Notice notice=new Notice();
		notice.setId(noticeId);
		notice.setNoticeStatus("0");
		int flag = noticeService.update(notice);
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag==1){
			map.put("msg", "停用成功");
			map.put("success", true);
		}else{
			map.put("msg", "停用失败");
			map.put("success", false);
		}
		return returnSuccess(map);
	}
	
	/**
	 * 后台- 更改状态-启用
	 * @param noticeId
	 * @return
	 */
	@RequestMapping("/updateStatusByid")
	@ResponseBody
	public ResultEntity updateStatusByid(String noticeId){
		Notice notice=new Notice();
		notice.setId(noticeId);
		notice.setNoticeStatus("1");
		int flag = noticeService.update(notice);
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag==1){
			map.put("msg", "启动成功");
			map.put("success", true);
		}else{
			map.put("msg", "启动失败");
			map.put("success", false);
		}
		return returnSuccess(map);
	}
	
	/**
	 * 后台-删除
	 * @return
	 */
	@RequestMapping("deleteNoticeById")
	@ResponseBody
	public ResultEntity deleteNoticeById(String noticeId){
		int flag = noticeService.deleteById(noticeId);
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
	 *  前台-新闻显示
	 * @param model
	 * @param n
	 * @return
	 */
	@RequestMapping("/queryNoticeList")
	@ResponseBody
	public ResultEntity queryNoticeList(Model model , int page,int rows,String noticeType,String compCode){
		Page pag=new Page();
		pag.setPageNum(page);
		pag.setPageSize(rows);
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("compCode", compCode);
		params.put("noticeType", noticeType);
		Sort sorts=new Sort();
		sorts.setField("release_time");
		sorts.setType("desc");
		Pagination<Notice> pagi=noticeService.getPagination(params, pag, sorts);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("rows", pagi.getList());
		map.put("total", pagi.getCount());
		return returnSuccess(map);
	}
	
	/**
	 *  前台-获取新闻内容
	 * @param model
	 * @param n
	 * @return
	 */
	@RequestMapping("/queryDetail")
	@ResponseBody
	public ResultEntity queryDetail(Model model , String noticeId){
		Notice notice=noticeService.getById(noticeId);
		return returnSuccess(notice);
	}
	
	/**
	 * 
	 * 获取前三条新闻
	 * @return
	 */
	@RequestMapping("/newNoticeList")
	@ResponseBody
	public ResultEntity newNoticeList(){
		List<Notice> newNoticeList = noticeService.getNewNoticeList();
		return returnSuccess(newNoticeList);
	}
}
