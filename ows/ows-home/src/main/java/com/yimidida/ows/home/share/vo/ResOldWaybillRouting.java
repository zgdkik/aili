package com.yimidida.ows.home.share.vo;

import java.util.List;

public class ResOldWaybillRouting {
	
	private List<Routing> barList;

	public static class Routing {
		private String compName;
		private String contentValue;
		private String operTm;
		private String waybillNo;

		public String getCompName() {
			return compName;
		}

		public void setCompName(String compName) {
			this.compName = compName;
		}

		public String getContentValue() {
			return contentValue;
		}

		public void setContentValue(String contentValue) {
			this.contentValue = contentValue;
		}

		public String getOperTm() {
			return operTm;
		}

		public void setOperTm(String operTm) {
			this.operTm = operTm;
		}

		public String getWaybillNo() {
			return waybillNo;
		}

		public void setWaybillNo(String waybillNo) {
			this.waybillNo = waybillNo;
		}

	}

	public List<Routing> getBarList() {
		return barList;
	}

	public void setBarList(List<Routing> barList) {
		this.barList = barList;
	}
	
	
}
