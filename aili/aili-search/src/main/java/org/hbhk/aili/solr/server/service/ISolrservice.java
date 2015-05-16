package org.hbhk.aili.solr.server.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;

public interface ISolrservice<T extends SolrBase> {

	void addIndex(T t);

	void addIndex(List<T> list);

	
	void updateIndex(T t);

	void updateIndex(List<T> list);

	
	void delIndex(String id);
	
	void delIndex(List<String> ids);
	
	void delAllIndex();
	

	List<T> queryList(String query, Class<T> cls,String... facets);
	
	List<T> queryList(String query, Map<String, String> filters,Class<T> cls,String... facets);
	
	List<T> queryList(String query, String sort,int start, int size, Class<T> cls,String... facets);

	List<T> queryList(String query,Map<String, String> filters, String sort, Class<T> cls,String... facets);
	
	Pagination<T> queryListWithPage(String query, String sort,
			int start, int size, Class<T> cls,String... facets);
	
	Pagination<T> queryListWithPage(String query,Map<String, String> filters, String sort,
			int start, int size, Class<T> cls,String... facets);
	
	
	QueryResponse query(SolrParams params);
}
