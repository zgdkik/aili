package org.hbhk.aili.solr.share.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public  class SolrResult<T> implements Serializable {
	
	private static final long serialVersionUID = 8478134894551547769L;

	private List<T> datas;
	
	private Map<String, Integer> facets;

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Map<String, Integer> getFacets() {
		return facets;
	}

	public void setFacets(Map<String, Integer> facets) {
		this.facets = facets;
	}
	
	

}
