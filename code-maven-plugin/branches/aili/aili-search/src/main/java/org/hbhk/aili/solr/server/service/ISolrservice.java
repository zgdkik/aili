package org.hbhk.aili.solr.server.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.SolrResult;

public interface ISolrservice {

	/**
	 * 
	* @Description: 新增索引 
	* @author hebo 
	* @date 2015年5月16日 上午10:55:12 
	  @param t
	 */
	<T extends SolrBase> void addIndex(T t);

	<T extends SolrBase> void addIndex(List<T> list);

	/**
	 * 
	* @Description: 修改索引
	* @author hebo 
	* @date 2015年5月16日 上午10:55:12 
	  @param t
	 */	
	<T extends SolrBase> void updateIndex(T t);

	<T extends SolrBase> void updateIndex(List<T> list);

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
	<T extends SolrBase> List<T> queryList(String keyword,String fl,String[] highlightField, String sort, String... fqs) ;
	
	<T extends SolrBase> SolrResult<T> queryList(String keyword,String[] fqs,String fl,String[] highlightField, String sort,Integer limit,String... facets);
	
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
	
	<T extends SolrBase> List<T> queryList(String keyword, String fl,String[] highlightField, String sort,Integer start, Integer size,String... fqs);
	
	<T extends SolrBase> SolrResult<T> queryList(String keyword,String[] fqs,String fl,String[] highlightField, String sort,Integer start, Integer size, Integer limit,String... facets);
	
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
	<T extends SolrBase> Pagination<T> queryListWithPage(String keyword, String[] fqs,String fl,String[] highlightField, String sort,
			Integer start, Integer size,Integer limit,String... facets);
	
	<T extends SolrBase> Pagination<T> queryListWithPage(String keyword,String[] fqs, String fl,String[] highlightField, String sort,
			Integer start, Integer size);
	
	/**
	 * 
	* @Description: 自定义查询索引
	* @author hebo 
	* @date 2015年5月16日 上午10:57:20 
	  @param params
	  @return
	 */
	QueryResponse query(SolrParams params);
	/**
	 * 
	* @Description: 设置需要处理solr类名
	* @author hebo 
	* @date 2015年5月16日 上午11:29:03 
	  @param solrModelCls
	 */
	void setSolrModelName(String solrModelName);
	
	/**
	 * 
	* @Description: 设置solr链接
	* @author hebo 
	* @date 2015年5月16日 上午11:31:09 
	  @param solrServer
	 */
	void setSolrClient(SolrClient solrServer);
}
