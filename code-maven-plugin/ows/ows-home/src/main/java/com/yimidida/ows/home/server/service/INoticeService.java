package com.yimidida.ows.home.server.service;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.Html;
import com.yimidida.ows.home.share.entity.Notice;

/**
 * 新闻动态业务层
 * @author zhangm
 *
 */
public interface INoticeService extends IBaseService<Notice, String>  {

	/**
	 * 分页查询所有新闻动态内容
	 * @param page
	 * @param object 
	 * @return
	 */
	Pagination<Notice> getAllNotice(Map<String, Object> param,Page page, Sort... sorts);

	/**
	 * 修改时候的新闻回显
	 * @param noticeId
	 * @return
	 */
	List<Notice> getNoticeById(String noticeId);

	/**
	 * 查询最新的7条新闻
	 * @return 
	 */
	List<Notice> getNewNoticeList();

	
}
