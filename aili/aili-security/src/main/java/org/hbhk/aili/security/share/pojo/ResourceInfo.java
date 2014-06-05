package org.hbhk.aili.security.share.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ResourceInfo implements java.io.Serializable {

	private static final long serialVersionUID = 9125685922301392808L;
	// 节点 id
	private String id;

	private String code;
	// 显示文本
	private String text;
	// 是否展开
	private boolean expanded;
	// 菜单类型
	private String type;

	private String active;

	private String parent_code;
	// 显示 顺序
	private int priority;
	// 需要 打开的url
	private String url = "getMenu.ctrl";
	// 显示效果 样式
	private String classes = "meun";
	// 子节点
	private List<ResourceInfo> children;
	// 是否有子节点
	private boolean hasChildren;
	// 备注
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getParent_code() {
		return parent_code;
	}

	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public List<ResourceInfo> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceInfo> children) {
		this.children = children;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}