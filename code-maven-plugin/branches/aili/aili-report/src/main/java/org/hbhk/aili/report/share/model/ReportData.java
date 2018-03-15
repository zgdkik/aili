package org.hbhk.aili.report.share.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReportData implements Serializable {

	private static final long serialVersionUID = 2366438945791777248L;
	
	
	private List<Object> datas;
	
	private Map<String, Object> params;

	public List<Object> getDatas() {
		return datas;
	}

	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
}
