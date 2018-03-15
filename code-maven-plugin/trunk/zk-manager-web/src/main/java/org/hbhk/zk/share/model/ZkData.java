package org.hbhk.zk.share.model;
import java.io.Serializable;
public class ZkData implements Serializable {
	private static final long serialVersionUID = 1470345539983404122L;
	
	private String path;
	
	private String data;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}

