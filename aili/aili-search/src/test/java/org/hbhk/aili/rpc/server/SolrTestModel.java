package org.hbhk.aili.rpc.server;

import org.apache.solr.client.solrj.beans.Field;
import org.hbhk.aili.solr.share.model.SolrBase;

public class SolrTestModel  extends SolrBase{

	private static final long serialVersionUID = 135338855694344305L;
	
	@Field
	private String title;
	@Field
	private String content;

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
	
	
	
	

}
