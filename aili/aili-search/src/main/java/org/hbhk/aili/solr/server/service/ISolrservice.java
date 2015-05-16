package org.hbhk.aili.solr.server.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.SolrResult;

public interface ISolrservice<T extends SolrBase> {

	void addIndex(T t);

	void addIndex(List<T> list);

	
	void updateIndex(T t);

	void updateIndex(List<T> list);

	
	void delIndex(String id);
	
	void delIndex(List<String> ids);
	
	void delAllIndex();
	

	List<T> queryList(String keyword,Map<String, String> fq, Class<T> cls);
	
	SolrResult<T> queryList(String keyword, Map<String, String> fq,Class<T> cls,Integer limit,String... facets);
	
	
	SolrResult<T> queryList(String keyword,Map<String, String> fq, String sort, Class<T> cls,Integer limit,String... facets);
	
	List<T> queryList(String keyword,Map<String, String> fq, String sort, Class<T> cls);
	
	
	Pagination<T> queryListWithPage(String keyword, Map<String, String> fq,String sort,
			Integer start, Integer size, Class<T> cls,Integer limit,String... facets);
	
	Pagination<T> queryListWithPage(String keyword,Map<String, String> fq, String sort,
			Integer start, Integer size, Class<T> cls);
	
	
	QueryResponse query(SolrParams params);
}
