package com.yimidida.ows.home.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.CustomerReply;

/**
 * 投诉建议 DAO层
 * @author zhangm
 *
 */
public interface ICustomerReplyDao extends IBaseDao<CustomerReply, String>{

	/**
	 * 客服回复
	 * @param id
	 * @param customerReply
	 * @return
	 */
	int updateCustomerReplyById(@Param("id")String id, @Param("customerReply")String customerReply);

	/**
	 * 根据id修改回复状态
	 * @param id
	 * @return
	 */
	int deleteStatusById(String id);
}
