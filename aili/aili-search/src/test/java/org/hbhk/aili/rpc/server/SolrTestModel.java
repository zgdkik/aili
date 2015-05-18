package org.hbhk.aili.rpc.server;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;
import org.hbhk.aili.solr.share.model.SolrBase;

public class SolrTestModel  extends SolrBase{

	private static final long serialVersionUID = 135338855694344305L;
	
	@Field
	private String title;
	@Field
	private String content;
	
	@Field("dynMap_*")
	private Map<String, String> dynMap;
	
	
	@Field("dynMapList_*")
	private Map<String, List<String>> dynMapList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getDynMap() {
		return dynMap;
	}

	public void setDynMap(Map<String, String> dynMap) {
		this.dynMap = dynMap;
	}

	public Map<String, List<String>> getDynMapList() {
		return dynMapList;
	}

	public void setDynMapList(Map<String, List<String>> dynMapList) {
		this.dynMapList = dynMapList;
	}
	
	
	
	

}
