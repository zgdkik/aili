package org.hbhk.hstorm.sync;

import java.util.List;

public class TabInfo {

	
	private  String  tabName;
	
	private String primaryKey;
	
	private String  primaryType;  
	
	private List<ColumnInfo> cols;
	
	

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public List<ColumnInfo> getCols() {
		return cols;
	}

	public void setCols(List<ColumnInfo> cols) {
		this.cols = cols;
	}

	@Override
	public String toString() {
		return "TabInfo [tabName=" + tabName + ", cols=" + cols + "]";
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	
	
} 
