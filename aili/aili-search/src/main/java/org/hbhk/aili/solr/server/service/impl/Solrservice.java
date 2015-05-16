package org.hbhk.aili.solr.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.SolrResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Solrservice implements ISolrservice<SolrBase> {

	private static Log log = LogFactory.getLog(Solrservice.class);

	@Autowired
	private SolrServer solrServer;

	@Override
	public void addIndex(SolrBase t) {
		try {
			log.debug("添加索引开始...");
			solrServer.addBean(t);
			solrServer.commit();
			log.debug("添加索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void addIndex(List<SolrBase> list) {
		try {
			log.debug("批量添加索引开始...");
			solrServer.addBeans(list);
			solrServer.commit();
			log.debug("批量添加索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateIndex(SolrBase t) {
		try {
			log.debug("修改索引开始...");
			solrServer.addBean(t);
			solrServer.commit();
			log.debug("修改索引结束...");
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateIndex(List<SolrBase> list) {
		try {
			log.debug("批量修改索引开始...");
			List<String> ids = new ArrayList<String>();
			for (SolrBase solr : list) {
				ids.add(solr.getId());
			}
			solrServer.deleteById(ids);
			solrServer.addBeans(list);
			solrServer.commit();
			log.debug("批量修改索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}
	@Override
	public QueryResponse query(SolrParams params) {
		try {
			log.debug("查询索引开始...");
			QueryResponse response =  solrServer.query(params);
			log.debug("查询索引结束...");
			return response;
		} catch (SolrServerException e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delIndex(String id) {
		try {
			log.debug("删除索引出现开始...");
			solrServer.deleteById(id);
			solrServer.commit();
			log.debug("删除索引出现结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delIndex(List<String> ids) {
		try {
			log.debug("批量删除索引出现开始...");
			solrServer.deleteById(ids);
			solrServer.commit();
			log.debug("批量删除索引出现结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delAllIndex() {
		try {
			log.debug("删除所有索引开始...");
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
			log.debug("删除所有索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}
	
	private void addFacets(ModifiableSolrParams params,Integer limit ,String... facets){
		if(limit!=null && facets!=null && facets.length>0){
			SolrQuery solrQuery = new SolrQuery();
			//设置facet=on
			solrQuery.setFacet(true);
			//设置需要facet的字段		
			solrQuery.addFacetField(facets);
			//限制facet返回的数量
			solrQuery.setFacetLimit(limit);
			log.debug("设置facet返回数量:"+limit+" facet字段:"+facets);
			params.add(solrQuery);
		}
	}
	


	@Override
	public List<SolrBase> queryList(String keyword, Map<String, String> fq,
			Class<SolrBase> cls) {
		
		return null;
	}
	
	@Override
	public SolrResult<SolrBase> queryList(String keyword, Map<String, String> fq,
			Class<SolrBase> cls,Integer limit,String...facets) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		
		ModifiableSolrParams params = new ModifiableSolrParams();
		addFacets(params,10, facets);
		params.add(solrQuery);
		QueryResponse response = null;
		try {
			response = solrServer.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		//返回的facet列表
		List<FacetField> facetList = response.getFacetFields();
		return null;
	}
	
	private void  addParams(ModifiableSolrParams params,String keyword,String fq, String sort,String fl, Integer start,
			Integer size,Integer limit, String... facets){
		if (StringUtils.isEmpty(keyword)) {
			keyword = "*:*";
		}
		params.set("q", keyword);
		if(start!=null){
			params.set("start", start);
		}
		if(StringUtils.isNotEmpty(fq)){
			params.set("fq", fq);
		}
		if(size!=null){
			params.set("rows", size);
		}
		if(size!=null){
			params.set("sort", sort);
		}
		if(StringUtils.isEmpty(fl)){
			fl ="*";
		}
		params.set("fl", fl);  
		
		addFacets(params, limit, facets);
		
		
	}
	

	public void rollback(Exception ex) {
		try {
			log.error("操作索引异常事务回滚:" + ex.getMessage(), ex);
			solrServer.rollback();
		} catch (SolrServerException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public SolrResult<SolrBase> queryList(String keyword, Map<String, String> fq,
			String sort, Class<SolrBase> cls, Integer limit, String... facets) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		addParams(params, keyword, null, sort, null, null, null, null, null);
		List<SolrBase> list = new ArrayList<SolrBase>();
		SolrResult<SolrBase>  solrResult = new SolrResult<SolrBase>();
		try {
			QueryResponse response = solrServer.query(params);
			list = response.getBeans(cls);
			solrResult.setDatas(list);
			log.debug("查询索引结束...");
		} catch (SolrServerException e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
		return solrResult;
	}

	@Override
	public List<SolrBase> queryList(String keyword, Map<String, String> fq,
			String sort, Class<SolrBase> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<SolrBase> queryListWithPage(String keyword,
			Map<String, String> fq, String sort, Integer start, Integer size,
			Class<SolrBase> cls, Integer limit, String... facets) {
//		ModifiableSolrParams params = new ModifiableSolrParams();
//		params.set("fq", fq);
//		params.set("facet", "on");
//		params.set("facet.field", "category", "brand");
//		params.set("facet.mincount", "1");
//		params.set("facet.limit", "15");
		//查询关键字
//		params.set("q","铁观音");
		 // 返回信息  * 为全部  这里是全部加上score，如果不加下面就不能使用score  
	   // params.set("fl", "*,score");  
	      
		
		ModifiableSolrParams params = new ModifiableSolrParams();
		if (keyword.equals("")) {
			keyword = "*:*";
		}
		//查询关键字
		params.set("q", keyword);
		params.set("start", start);
		params.set("rows", size);
		params.set("sort", sort);
		
		
		Pagination<SolrBase> pagination = new Pagination<SolrBase>();
		pagination.setStart(start);
		pagination.setSize(size);
		return pagination;
	}

	@Override
	public Pagination<SolrBase> queryListWithPage(String keyword,
			Map<String, String> fq, String sort, Integer start, Integer size,
			Class<SolrBase> cls) {
		return null;
	}

	
}
