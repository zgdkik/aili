package com.yimidida.ows.home.server.service;

import java.util.Map;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.OrderBase;
import com.yimidida.ows.home.share.entity.OrderInfo;

public interface IOrderInfoService extends IBaseService<OrderInfo, String>  {

	Map<String, Object> addOrder(OrderBase order,String userId);
	
	int deleteOrder(Map<String, Object> params);
	
	
	
}
