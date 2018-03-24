package com.yimidida.ows.home.server.service;

import java.util.List;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.CustomerReply;

/**
 * 投诉建议接口
 * @author zhangm
 *
 */
public interface ICustomerReplyService extends IBaseService<CustomerReply, String>{

	/**
	 * 客户回复
	 * @param id
	 * @param customerReply
	 */
	int updateCustomerReplyById(String id, String customerReply);

	/**
	 * 根据id更新状态
	 * @param id
	 * @return
	 */
	int deleteStatusById(String id);

}
