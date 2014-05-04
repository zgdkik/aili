package org.hbhk.aili.security.share.pojo;


public class ResourceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 9125685922301392808L;
	private String id;
	//编码
	private String code;
	//名称
	private String name;
	//url
	private String url;
	//顺序
	private Integer priority;
	//类型
	private String type;
    //备注
	private String memo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
}