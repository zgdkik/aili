package com.feisuo.sds.base.share.vo;

import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Sort;

public class QueryPageVo {
	public static final int DEFULT_START = 0;
	public static final int DEFULT_SIZE = 10;
	
	private Page page = new Page();
	/**
	 * 排序
	 */
	private Sort[] sorts;
	/**
	 * 查询参数
	 */
	private Map<String, Object> paraMap;
	
	public Sort[] getSorts() {
		return sorts;
	}
	public void setSorts(Sort[] sorts) {
		this.sorts = sorts;
	}
	public Map<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	
	public Page getPage(){
		return page;
	}
	
	public void setPage(Page page){
		this.page = page;
	}
	
}
