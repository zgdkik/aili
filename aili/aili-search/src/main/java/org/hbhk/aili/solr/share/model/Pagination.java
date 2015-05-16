package org.hbhk.aili.solr.share.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 520741651051423364L;
	private List<T> datas;
	private Map<String, Integer> facets;
	private int totalCount;
	private int totalPages;
	private int start;
	private int size;

	public Pagination() {
	}

	public Pagination(List<T> datas, int totalCount) {
		this.datas = datas;
		this.totalCount = totalCount;
	}

	public Pagination(List<T> datas, int totalCount, int totalPages, int start,
			int size) {
		this.datas = datas;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
		this.start = start;
		this.size = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, Integer> getFacets() {
		return facets;
	}

	public void setFacets(Map<String, Integer> facets) {
		this.facets = facets;
	}

	
}
