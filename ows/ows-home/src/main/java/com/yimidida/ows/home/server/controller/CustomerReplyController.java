package com.yimidida.ows.home.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.ICustomerReplyService;
import com.yimidida.ows.home.share.entity.CustomerReply;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

/**
 * 投诉建议-web层
 * @author zhangm
 *
 */
@Controller
@RequestMapping("complaintAdvice")
public class CustomerReplyController extends AbstractController{
	@Autowired
	private ICustomerReplyService customerReplyService;
	
	//查询所有投诉建议
	@RequestMapping("/getAllComplaintAdvice")
	@ResponseBody
	public Pagination<CustomerReply> getAllBanner(Model model,@QueryPage  QueryPageVo queryPageVo){
		Pagination<CustomerReply> pageOrder=customerReplyService.getPagination(queryPageVo.getParaMap(), queryPageVo.getPage(), null);
		return pageOrder;
	}
	//回显
	@RequestMapping("/echoProblem")
	@ResponseBody
	public ResultEntity echoProblem(String id){
		CustomerReply reply = customerReplyService.getById(id);
		return returnSuccess(reply);
	}
	//客户回复-保存
	@RequestMapping("/customerReplySave")
	@ResponseBody
	public ResultEntity customerReplySave(CustomerReply cr){
		int flag = 0;
		String userName=UserContext.getCurrentUser().getUserName();//获取后台用户的user
		Date now = new Date();
		cr.setUpdateTime(now);
		cr.setUpdateUser(userName);
		cr.setCustomerReplyType("1");
		flag = customerReplyService.update(cr);
		System.out.println(flag);
		return returnSuccess(flag);
	}
	//关闭投诉建议-逻辑删除
	@RequestMapping("/deleteComplaintAdvice")
	@ResponseBody
	public ResultEntity deleteComplaintAdvice(String id){
		int flag = 0;
		flag = customerReplyService.deleteStatusById(id);
		return returnSuccess(flag);
	}
	
	//physics delete
	@RequestMapping("/deleteAdice")
	@ResponseBody
	public ResultEntity deleteAdice(String id){
		int flag = 0;
		flag = customerReplyService.deleteById(id);
		return returnSuccess(flag);
	}
	/**
	 * 投诉建议前台列表
	 * @param model
	 * @param n
	 * @return
	 */
	@RequestMapping("/queryTsjyList")
	@ResponseBody
	public ResultEntity queryTsjyList(Model model , int page,int rows){
		Page pag=new Page();
		pag.setPageNum(page);
		pag.setPageSize(rows);
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("status","1");
		Sort sorts=new Sort();
		sorts.setField("create_time");
		sorts.setType("desc");
		Pagination<CustomerReply> pagi=customerReplyService.getPagination(params,pag,sorts);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("rows", pagi.getList());
		map.put("total", pagi.getCount());
		return returnSuccess(map);
	}
	
	@RequestMapping(value="saveTsjy",method = RequestMethod.POST)
	@ResponseBody
	public Object addComplaints(CustomerReply cs){
		if(checkInputByCs(cs)==3){
			return 3;
		}
		Integer state;//1成功  2失败
		try {
			cs.setId(UuidUtil.getUuid());
			cs.setCreateTime(new Date());
			cs.setStatus("1");
			cs.setPid("0");
			cs.setCustomerReplyType("0");
			if(customerReplyService.insert(cs)==1){
				state = 1;
			}else{
				state =2;
			}
		} catch (Exception e) {
			state= 2;
		}
		return state;
		
	}
	private Integer checkInputByCs(CustomerReply cs){
		boolean b_name = cs.getName().matches("^[a-zA-Z0-9\u4E00-\u9FA5]+$");
		if(b_name==false){
			return 3;
		}
		
		boolean b_phone = cs.getMobilePhone().matches("^1[2-9]{1}[0-9]{9}$");
		if(b_phone==false){
			return 3;
		}
		
		if(StringUtils.isNotBlank(cs.getEmail())){
			boolean b_mail = cs.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
			if(b_mail==false){
				return 3;
			}
		}
		if(StringUtils.isNotBlank(cs.getSingleNumber())){
			boolean b_number = cs.getSingleNumber().matches("^[a-zA-Z0-9\u4E00-\u9FA5]+$");
			if(b_number==false){
				return 3;
			}
		}
		boolean b_content = cs.getProblemDescription().matches(".*<\\w+.*>.*");
		if(b_content){
			return 3;
		}
		return 4;
	}
	
}
