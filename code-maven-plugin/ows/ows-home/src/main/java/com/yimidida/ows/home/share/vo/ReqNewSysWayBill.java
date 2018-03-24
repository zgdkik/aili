package com.yimidida.ows.home.share.vo;

public class ReqNewSysWayBill {
	private String[] waybillNos;
	private String trackScene;

	public String[] getWaybillNos() {
		return waybillNos;
	}

	public void setWaybillNos(String[] waybillNos) {
		this.waybillNos = waybillNos;
	}

	public String getTrackScene() {
		return trackScene;
	}

	public void setTrackScene(String trackScene) {
		this.trackScene = trackScene;
	}

	
	public ReqNewSysWayBill() {
		super();
	}

	public ReqNewSysWayBill(String[] waybillNos, String trackScene) {
		super();
		this.waybillNos = waybillNos;
		this.trackScene = trackScene;
	}
}
