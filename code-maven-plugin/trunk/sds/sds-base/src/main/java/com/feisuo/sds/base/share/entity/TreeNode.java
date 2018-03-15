package com.feisuo.sds.base.share.entity;

import java.util.List;

/**
 * 树节点的实体对象
 */
@SuppressWarnings("rawtypes")
public class TreeNode<T, K extends TreeNode> {

	/**
	 * 树节点ID
	 */
	private String id;

	/**
	 * 树节点文本显示
	 */
	private String text;

	/**
	 * 是否叶子节点
	 */
	private Boolean leaf;

	/**
	 * 父节点ID
	 */
	private String parentId;

	/**
	 * 显示是否选择
	 */
	private Boolean checked;

	/**
	 * 节点对象数据
	 */
	private T entity;

	/**
	 * 孩子节点
	 */
	private List<K> children;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return leaf
	 */
	public Boolean getLeaf() {
		return leaf;
	}

	/**
	 * @param leaf
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * @return children
	 */
	public List<K> getChildren() {
		return children;
	}

	/**
	 * @param children
	 */
	public void setChildren(List<K> children) {
		this.children = children;
	}

}