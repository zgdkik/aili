package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.INoticeDao;
import com.yimidida.ows.home.server.service.INoticeService;
import com.yimidida.ows.home.share.entity.Notice;

/**
 * 新闻动态-实现层
 * @author zhangm
 *
 */
@Service
public class NoticeService implements INoticeService{
	
	@Autowired 
	private INoticeDao noticeDao;
	
	//分页查询
	public Pagination<Notice> getAllNotice(Map<String, Object> param,Page page) {
		return noticeDao.getPagination(param, page, null);
	}
	
	//根据id回显新闻动态的修改数据回显
	@Override
	public List<Notice> getNoticeById(String noticeId) {
		@SuppressWarnings("unchecked")
		List<Notice> noticeList = (List<Notice>) noticeDao.getNoticeById(noticeId);
		return noticeList;
	}

	@Override
	public int insert(Notice t) {
		// TODO Auto-generated method stub
		return noticeDao.insert(t);
	}


	@Override
	public int update(Notice t) {
		// TODO Auto-generated method stub
		return noticeDao.update(t);
	}


	@Override
	public Notice getById(String id) {
		// TODO Auto-generated method stub
		return noticeDao.getById(id);
	}


	@Override
	public List<Notice> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Notice> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}


	//删除
	@Override
	public int deleteById(String noticeId) {
		return noticeDao.deleteById(noticeId);
	}


	@Override
	public int updateStatusById(String id, int status) {
		return noticeDao.updateStatusById(id, 2);
	}


	@Override
	public Pagination<Notice> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return noticeDao.getPagination(params, page, sorts);
	}

	@Override
	public List<Notice> getNewNoticeList() {
		return noticeDao.getNewNoticeList();
	}


	/* 
	 * 分页查询
	 */
	@Override
	public Pagination<Notice> getAllNotice(Map<String, Object> param, Page page, Sort... sorts) {
		Pagination<Notice> notice = noticeDao.getAllNotice(param,page,sorts);
		return notice;
	}
}
