package org.hbhk.aili.solr.share.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public abstract class SolrBase implements Serializable {

	private static final long serialVersionUID = 4217981116395588171L;
	
	@Field("id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
