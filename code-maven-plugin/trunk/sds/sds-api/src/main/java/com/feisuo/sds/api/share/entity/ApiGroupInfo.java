package com.feisuo.sds.api.share.entity;

import java.io.Serializable;
import java.util.List;

public class ApiGroupInfo  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8615409839944911335L;

	private String  name ;
	
	private List<ApiInfo> apiList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ApiInfo> getApiList() {
		return apiList;
	}

	public void setApiList(List<ApiInfo> apiList) {
		this.apiList = apiList;
	}

	
}
