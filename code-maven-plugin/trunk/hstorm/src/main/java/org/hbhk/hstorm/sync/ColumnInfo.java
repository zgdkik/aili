package org.hbhk.hstorm.sync;

import java.io.Serializable;

public class ColumnInfo implements Serializable {

	/**   
	 */
	private static final long serialVersionUID = 3363576849858930314L;

	private String col;

	private String type;

	private int len;

	public ColumnInfo(String col, String type, int len) {
		super();
		this.col = col;
		this.type = type;
		this.len = len;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	@Override
	public String toString() {
		return "ColumnInfo [col=" + col + ", type=" + type + ", len=" + len
				+ "]";
	}
	

}
