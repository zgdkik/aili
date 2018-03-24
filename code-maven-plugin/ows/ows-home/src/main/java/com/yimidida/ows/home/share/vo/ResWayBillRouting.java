package com.yimidida.ows.home.share.vo;

import java.util.ArrayList;
import java.util.List;

public class ResWayBillRouting {
	/* 运单号 */
	private String waybillNo;

	private List<WaybillTrackVo> trackList;

	public static class WaybillTrackVo {

		// 操作时间
		private String operateTime;

		// 跟踪信息
		private String trackInfo;

		public String getOperateTime() {
			return operateTime;
		}

		public void setOperateTime(String operateTime) {
			this.operateTime = operateTime;
		}

		public String getTrackInfo() {
			return trackInfo;
		}

		public void setTrackInfo(String trackInfo) {
			this.trackInfo = trackInfo;
		}

	}

	public List<WaybillTrackVo> getTrackList() {
		return trackList = trackList == null ? new ArrayList<ResWayBillRouting.WaybillTrackVo>() : trackList;
	}

	public void setTrackList(List<WaybillTrackVo> trackList) {
		this.trackList = trackList;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

}
