package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.DynamicSqlTemplate;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;

import com.yimidida.ows.home.share.entity.Html;
import com.yimidida.ows.home.share.entity.Notice;

/**
 * 新闻动态DAO层
 * @author zhangm
 *
 */
public interface INoticeDao extends IBaseDao<Notice, String> {

	/**
	 * 查询
	 * @param param
	 * @return
	 */
	List<Notice> getHtmlByMenuId(Map<String, Object> param);

	/**
	 * 根据id回显新闻动态-修改-数据
	 * @param noticeId
	 * @return
	 */
	Notice getNoticeById(String noticeId);
	
	
	Pagination<Notice> getAllNoticeByType(Map<String, Object> param,Page page);

	/**
	 * 首页- 查询最新的3条新闻
	 * @return 
	 */
	List<Notice> getNewNoticeList();
	
	/**
	 * 根据id删除
	 * @return
	 */
	int deleteByNoticeId(String noticeId);

	/**
	 * 分页查询
	 * @param param
	 * @param page
	 * @return
	 */
	Pagination<Notice> getAllNotice(Map<String, Object> param, Page page,Sort... sorts);


}
