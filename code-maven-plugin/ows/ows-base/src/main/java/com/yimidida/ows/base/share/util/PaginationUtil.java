package com.yimidida.ows.base.share.util;

import org.mybatis.spring.support.Pagination;

public class PaginationUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getNewPagination(Pagination pt){
		Pagination<T> pagination = new Pagination<>();
		pagination.setCount(pt.getCount());
		pagination.setPageNum(pt.getPageNum());
		pagination.setPageSize(pt.getPageSize());
		pagination.setSortStr(pt.getSortStr());
		pagination.setTotalPages(pt.getTotalPages());
		return (T) pagination;
	}
	
}
