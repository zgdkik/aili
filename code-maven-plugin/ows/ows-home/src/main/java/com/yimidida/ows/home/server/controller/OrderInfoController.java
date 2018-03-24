package com.yimidida.ows.home.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpRequest;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.home.server.service.IDepartmentService;
import com.yimidida.ows.home.server.service.IOrderInfoService;
import com.yimidida.ows.home.server.service.impl.DepartmentService;
import com.yimidida.ows.home.server.util.BeanWrapper;
import com.yimidida.ows.home.server.util.HttpUtil;
import com.yimidida.ows.home.share.entity.OrderBase;
import com.yimidida.ows.home.share.entity.OrderInfo;
import com.yimidida.ows.home.share.entity.OwsUser;
import com.yimidida.ows.home.share.vo.CODBack;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.JsonUtil;

/**
 * 订单
 * 
 * @author rhb
 *
 */
@Controller
@RequestMapping("order")
public class OrderInfoController extends AbstractController {

	@Autowired
	private IOrderInfoService orderInfoService;
	@Autowired
	private IDepartmentService departmentService;;

	// 预约订单
	@RequestMapping("addOrder")
	@ResponseBody
	public ResultEntity addOrder(OrderBase order, HttpSession session) {
		if ("1".equals(order.getDelivery())) {
			order.setDelivery("0");
		}
		order.setOrderChannel("1");
		String userId = "";
		OwsUser user = (OwsUser) session.getAttribute("user");
		if (user != null) {
			userId = user.getId();
		}
		Map<String, Object> map = orderInfoService.addOrder(order, userId);
		return returnSuccess(map);
	}

	// 微信预约订单
	@RequestMapping("addOrderForWx")
	@ResponseBody
	public ResultEntity getPhoneCode(HttpServletRequest request, HttpSession session) {
		String str = request.getParameter("jsonData");
		OrderInfo orderInfo = JsonUtil.parseJson(str, OrderInfo.class);
		if (!"1".equals(orderInfo.getDelivery())) {
			orderInfo.setDelivery("0");
		}
		OrderBase base = new OrderBase();
		BeanWrapper.copyProperties(orderInfo, base);
		Map<String, Object> map = orderInfoService.addOrder(orderInfo, orderInfo.getUserId());
		return returnSuccess(map);
	}

	// 微信预约订单
	@RequestMapping("getOrderForWx")
	@ResponseBody
	public ResultEntity getOrderForWx(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("orderState", "1");
		List<OrderInfo> list = orderInfoService.get(params);
		for (OrderInfo orderInfo : list) {
			orderInfo.setDestZoneCode(departmentService.getDepartmentName(orderInfo.getDestZoneCode()));
		}
		return returnSuccess(list);
	}

	// 预约订单
	@RequestMapping("getOrderByUser")
	@ResponseBody
	public ResultEntity getOrderByUser(int rows, int page, HttpSession session, Model model) {
		OwsUser user = (OwsUser) session.getAttribute("user");
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		if (user != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("orderState", "1");
			Page pag = new Page();
			pag.setPageSize(rows);
			pag.setPageNum(page);
			Sort sorts = new Sort();
			sorts.setField("create_Tm");
			sorts.setType("desc");
			Pagination<OrderInfo> pagi = orderInfoService.getPagination(params, pag, sorts);
			Map<String, Object> returnMap = new HashMap<String, Object>();
			List<OrderInfo> orderInfos = pagi.getList();
			if(orderInfos!=null && !orderInfos.isEmpty()) {
				// 将目的到达转为名称
				for (OrderInfo orderInfo : orderInfos) {
					orderInfo.setDestZoneCode(departmentService.getDepartmentName(orderInfo.getDestZoneCode()));
				}
			}
			
			returnMap.put("list", orderInfos);
			returnMap.put("total", pagi.getCount());
			return returnSuccess(returnMap);
		} else {
			throw new BusinessException("请先登录");
		}
	}

	// 处理订单 job调用
	@RequestMapping("updateOrderStatus")
	@ResponseBody
	public Map<String, Object> updateOrderStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = null;
		boolean flag = true;
		if (request.getParameter("orderIds") != null) {
			ids = request.getParameter("ids").trim();
			if (ids.indexOf(",") > 0) {
				String[] idArr = ids.split(",");
				for (int i = 0; i < idArr.length; i++) {
					OrderInfo orderInfo = new OrderInfo();
					orderInfo.setOrderId(idArr[i]);
					orderInfo.setDealFlg("1");
					if (orderInfoService.update(orderInfo) != 1) {
						flag = false;
					}

				}
			}
		}
		map.put("success", flag);
		return map;

	}

	// 处理订单
	@RequestMapping("deleteOrder")
	@ResponseBody
	public ResultEntity deleteOrder(String orderNo) {
		String param = "{orderNo:" + orderNo + "}";
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("jsonData", param);
		String resultStr = HttpUtil.sendPost(
				SystemParameterUtil.getValue("interface.ymdd") + "/pda/updatePdaOrderInfoStatus.action", params,
				String.class);
		JSONObject jasonObject = JSONObject.fromObject(resultStr);
		if ((boolean) jasonObject.get("success")) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("orderNo", orderNo);
			orderInfoService.deleteOrder(p);
			return returnSuccess(1);

		} else {
			return returnException(jasonObject.get("error").toString());
		}

//		Map<String, Object> p = new HashMap<String, Object>();
//		p.put("orderNo", orderNo);
//		orderInfoService.deleteOrder(p);
//		return returnSuccess(1);

	}

}
