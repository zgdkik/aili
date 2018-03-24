package com.yimidida.ows.home.share.vo;

import java.util.List;

public class CODBack {
	private String error;
	private List<COD> dataList;
	private boolean success;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	public List<COD> getDataList() {
		return dataList;
	}
	public void setDataList(List<COD> dataList) {
		this.dataList = dataList;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

}
