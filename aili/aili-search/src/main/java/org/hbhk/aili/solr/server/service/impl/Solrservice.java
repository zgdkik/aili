package org.hbhk.aili.solr.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.SolrResult;
import org.hbhk.aili.solr.share.util.SolrUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class Solrservice implements ISolrservice {

	private static Log log = LogFactory.getLog(Solrservice.class);
	
	
	private String solrModelName;
	
	private Class<? extends SolrBase> solrModel;

	@Autowired(required = false)
	private SolrClient solrClient;
	
	public void rollback(Exception ex) {
		try {
			log.error("操作索引异常事务回滚:" + ex.getMessage(), ex);
			solrClient.rollback();
		} catch (SolrServerException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		}
	}


	@Override
	public <T extends SolrBase> void addIndex(T t) {
		try {
			log.debug("添加索引开始...");
			solrClient.addBean(t);
			solrClient.commit();
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
	public <T extends SolrBase>  void  addIndex(List<T> list) {
		try {
			log.debug("批量添加索引开始...");
			solrClient.addBeans(list);
			solrClient.commit();
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
	public <T extends SolrBase> void updateIndex(T t) {
		try {
			log.debug("修改索引开始...");
			solrClient.addBean(t);
			solrClient.commit();
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
	public <T extends SolrBase> void updateIndex(List<T> list) {
		try {
			log.debug("批量修改索引开始...");
			List<String> ids = new ArrayList<String>();
			for (SolrBase solr : list) {
				ids.add(solr.getId());
			}
			solrClient.deleteById(ids);
			solrClient.addBeans(list);
			solrClient.commit();
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
	public void delIndex(String id) {
		try {
			log.debug("删除索引出现开始...");
			solrClient.deleteById(id);
			solrClient.commit();
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
			solrClient.deleteById(ids);
			solrClient.commit();
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
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
			log.debug("删除所有索引结束...");
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
			QueryResponse response =  solrClient.query(params);
			log.debug("查询索引结束...");
			return response;
		} catch (Exception e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SolrBase> List<T> queryList(String keyword,String fl,String[] highlightField, String sort,String... fqs) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, fqs, highlightField,null, fl, null, null, null);
		List<T> list  = null;
		try {
			QueryResponse response = solrClient.query(params);
			list  = (List<T>) response.getBeans(solrModel);
			log.debug("查询索引结束...");
		} catch (Exception e) {
			log.error("索引查询异常", e);
			throw new RuntimeException(e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends SolrBase> SolrResult<T> queryList(String keyword,String[] fqs,String fl,String[] highlightField, String sort,
			Integer limit,String...facets) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, fqs, highlightField,null, fl, null, null, limit, facets);
		QueryResponse response = null;
		try {
			response = solrClient.query(params);
		} catch (Exception e) {
			log.error("索引查询异常", e);
			throw new RuntimeException(e);
		}
		List<T> list  = (List<T>) response.getBeans(solrModel);
		SolrResult<T> solrResult = new SolrResult<T>();
		solrResult.setDatas(list);
		//返回的facet列表
		List<FacetField> facetList = response.getFacetFields();
		log.debug("facet查询结果: "+facetList==null?null:facetList.size());
		if(facetList!=null && facetList.size()>0){
			Map<String, Integer> facetMap  = new HashMap<String, Integer>();
			for (FacetField facetField : facetList) {
				String name = facetField.getName();
				int count = facetField.getValueCount();
				facetMap.put(name, count);
			}
			solrResult.setFacets(facetMap);
		}
		log.debug("查询索引结束...");
		return solrResult;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SolrBase> SolrResult<T> queryList(String keyword, String[] fqs,String fl,String[] highlightField, 
			String sort,Integer start, Integer size,Integer limit, String... facets) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, null,highlightField, sort, null, null, null, null, facets);
		List<T> list = new ArrayList<T>();
		SolrResult<T>  solrResult = new SolrResult<T>();
		try {
			QueryResponse response = solrClient.query(params);
			list = (List<T>) response.getBeans(solrModel);
			solrResult.setDatas(list);
		} catch (Exception e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
		log.debug("查询索引借宿...");
		return solrResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  <T extends SolrBase>  List<T> queryList(String keyword,String fl,String[] highlightField, String sort,Integer start, Integer size,String... fqs) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, fqs, highlightField, sort, fl, start, size, null);
		List<T> list  = null;
		try {
			QueryResponse response = solrClient.query(params);
			list  = (List<T>) response.getBeans(solrModel);
			log.debug("查询索引结束...");
		} catch (Exception e) {
			log.error("索引查询异常", e);
			throw new RuntimeException(e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  <T extends SolrBase>  Pagination<T> queryListWithPage(String keyword,
			String[] fqs,String fl,String[] highlightField, String sort, Integer start, Integer size, Integer limit, String... facets) {
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, fqs,highlightField, sort, null, start, size, limit, facets);
		Pagination<T> pagination = new Pagination<T>();
		pagination.setStart(start);
		pagination.setSize(size);
		List<T> list = new ArrayList<T>();
		try {
			QueryResponse response = solrClient.query(params);
			list = (List<T>) response.getBeans(solrModel);
			pagination.setDatas(list);
		} catch (Exception e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
		log.debug("查询索引借宿...");
		return pagination;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  <T extends SolrBase>  Pagination<T> queryListWithPage(String keyword,
			String[] fqs,String fl,String[] highlightField, String sort, Integer start, Integer size) {
		
		log.debug("查询索引开始...");
		ModifiableSolrParams params = new ModifiableSolrParams();
		SolrUtil.addParams(params, keyword, fqs,highlightField, sort, null, start, size, null);
		Pagination<T> pagination = new Pagination<T>();
		pagination.setStart(start);
		pagination.setSize(size);
		List<T> list = new ArrayList<T>();
		try {
			QueryResponse response = solrClient.query(params);
			list = (List<T>) response.getBeans(solrModel);
			pagination.setDatas(list);
		} catch (Exception e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
		log.debug("查询索引借宿...");
		return pagination;
	}

	public String getSolrModelName() {
		return solrModelName;
	}

	@SuppressWarnings("unchecked")
	public void setSolrModelName(String solrModelName) {
		this.solrModelName = solrModelName;
		try {
			solrModel = (Class<? extends SolrBase>) Class.forName(solrModelName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}


	public SolrClient getSolrClient() {
		return solrClient;
	}

	public void setSolrClient(SolrClient solrClient) {
		this.solrClient = solrClient;
	}


	
}
