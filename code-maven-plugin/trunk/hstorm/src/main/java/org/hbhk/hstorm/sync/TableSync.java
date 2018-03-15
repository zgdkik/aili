package org.hbhk.hstorm.sync;

import java.io.Serializable;
import java.util.Date;

public class TableSync implements Serializable {

	private static final long serialVersionUID = -1021144162403752605L;

	private String srcTabkeSql;

	private String destTable;

	/**
	 * 1、全量 2、增量
	 */
	private String syncWay;

	private Date lastSyncDate;

	/**
	 * 分钟
	 */
	private Integer syncycle;

	public String getSrcTabkeSql() {
		return srcTabkeSql;
	}

	public void setSrcTabkeSql(String srcTabkeSql) {
		this.srcTabkeSql = srcTabkeSql;
	}

	public String getDestTable() {
		return destTable;
	}

	public void setDestTable(String destTable) {
		this.destTable = destTable;
	}

	public String getSyncWay() {
		return syncWay;
	}

	public void setSyncWay(String syncWay) {
		this.syncWay = syncWay;
	}

	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public Integer getSyncycle() {
		return syncycle;
	}

	public void setSyncycle(Integer syncycle) {
		this.syncycle = syncycle;
	}

}
