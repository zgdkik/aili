package com.yimidida.ows.home.server.dao;

import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.OrderInfo;

public interface IOrderInfoDao extends IBaseDao<OrderInfo, String> {
	int deleteOrder(Map<String, Object> params);
	
}
