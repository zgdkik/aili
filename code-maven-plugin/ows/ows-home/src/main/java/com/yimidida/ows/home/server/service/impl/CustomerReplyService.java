package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.ICustomerReplyDao;
import com.yimidida.ows.home.server.service.ICustomerReplyService;
import com.yimidida.ows.home.share.entity.CustomerReply;

/**
 * 投诉建议 业务层
 * @author zhangm
 *
 */
@Service
public class CustomerReplyService implements ICustomerReplyService{
	
	@Autowired
	private ICustomerReplyDao customerReplyDao;
	
	@Override
	public int insert(CustomerReply t) {
		int num=customerReplyDao.insert(t);
		return num;
	}

	@Override
	public int update(CustomerReply t) {
		// TODO Auto-generated method stub
		return customerReplyDao.update(t);
	}

	@Override
	public CustomerReply getById(String id) {
		// TODO Auto-generated method stub
		return customerReplyDao.getById(id);
	}

	@Override
	public List<CustomerReply> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerReply> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return customerReplyDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return customerReplyDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<CustomerReply> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return customerReplyDao.getPagination(params, page, sorts);
	}

	@Override
	public int updateCustomerReplyById(String id, String customerReply) {
		int flag = customerReplyDao.updateCustomerReplyById(id,customerReply);
		return flag;
	}

	//根据id更新状态
	@Override
	public int deleteStatusById(String id) {
		return customerReplyDao.deleteStatusById(id);
	}
}
