package org.hbhk.aili.solr.server.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.SolrResult;

public interface ISolrservice<T extends SolrBase> {

	/**
	 * 
	* @Description: 新增索引 
	* @author hebo 
	* @date 2015年5月16日 上午10:55:12 
	  @param t
	 */
	void addIndex(T t);

	void addIndex(List<T> list);

	/**
	 * 
	* @Description: 修改索引
	* @author hebo 
	* @date 2015年5月16日 上午10:55:12 
	  @param t
	 */	
	void updateIndex(T t);

	void updateIndex(List<T> list);

	/**
	 * 
	* @Description: 删除索引
	* @author hebo 
	* @date 2015年5月16日 上午10:55:43 
	  @param id
	 */
	void delIndex(String id);
	
	void delIndex(List<String> ids);
	
	void delAllIndex();
	
	/**
	 * 
	* @Description:查询索引 
	* @author hebo 
	* @date 2015年5月16日 上午10:56:00 
	  @param keyword
	  @param fq
	  @param cls
	  @return
	 */
	List<T> queryList(String keyword,Map<String, String> fq, Class<T> cls);
	
	SolrResult<T> queryList(String keyword, Map<String, String> fq,Class<T> cls,Integer limit,String... facets);
	
	/**
	 * 
	* @Description: 查询指定索引位置的索引
	* @author hebo 
	* @date 2015年5月16日 上午10:56:17 
	  @param keyword
	  @param fq
	  @param sort
	  @param cls
	  @param limit
	  @param facets
	  @return
	 */
	SolrResult<T> queryList(String keyword,Map<String, String> fq, String sort, Class<T> cls,Integer limit,String... facets);
	
	List<T> queryList(String keyword,Map<String, String> fq, String sort, Class<T> cls);
	
	/**
	 * 
	* @Description: 查询带分页信息的索引
	* @author hebo 
	* @date 2015年5月16日 上午10:56:58 
	  @param keyword
	  @param fq
	  @param sort
	  @param start
	  @param size
	  @param cls
	  @param limit
	  @param facets
	  @return
	 */
	Pagination<T> queryListWithPage(String keyword, Map<String, String> fq,String sort,
			Integer start, Integer size, Class<T> cls,Integer limit,String... facets);
	
	Pagination<T> queryListWithPage(String keyword,Map<String, String> fq, String sort,
			Integer start, Integer size, Class<T> cls);
	
	/**
	 * 
	* @Description: 自定义查询索引
	* @author hebo 
	* @date 2015年5月16日 上午10:57:20 
	  @param params
	  @return
	 */
	QueryResponse query(SolrParams params);
}
