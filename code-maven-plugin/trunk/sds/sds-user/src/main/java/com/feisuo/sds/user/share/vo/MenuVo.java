package com.feisuo.sds.user.share.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.feisuo.sds.base.share.entity.IUserMenu;

public class MenuVo implements IUserMenu,Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;

	/** 菜单显示名称 */
	private String name;

	/** 入口URL，对于菜单组/分隔符忽略此数据 */
	private String url;

	/** 序号，仅对同级菜单排序用 */
	private Integer sortNo;

	/** 是否有子菜单[菜单组] */
	private Boolean isGroup = Boolean.FALSE;

	/** 权限ACL */
	private String acl;

	/** 父菜单项，如果为空说明是顶层菜单 */
	private String parentId;

	/** 子菜单 */
	private List<MenuVo> childList;

	public MenuVo() {
	}

	public MenuVo(String id, String name, String url, Integer sortNo,
			Boolean isGroup, String acl, String parentId, List<MenuVo> childList) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.sortNo = sortNo;
		this.isGroup = isGroup;
		this.acl = acl;
		this.parentId = parentId;
		this.childList = childList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<MenuVo> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuVo> childList) {
		this.childList = childList;
	}

	public String getAcl() {
		return acl;
	}

	public void setAcl(String acl) {
		this.acl = acl;
	}

	@Override
	public String toString() {
		return "menu [id=" + id + ", name=" + name + ", url=" + url
				+ ", sortNo=" + sortNo + ", isGroup=" + isGroup + ", acl="
				+ acl + ", parentId=" + parentId + ", childList=" + childList
				+ "]";
	}

	public void deleteChildNode(Set<String> urls) {
		List<MenuVo> childList = this.getChildList();
		if (childList == null || childList.isEmpty()) {
			return;
		}
		for (int i = 0; i < childList.size(); i++) {
			MenuVo child = childList.get(i);
			if (!urls.contains(child.getUrl())) {
				childList.remove(i);
			}
		}
		if(childList.size()==1){
			for (int i = 0; i < childList.size(); i++) {
				MenuVo child = childList.get(i);
				if (!urls.contains(child.getUrl())) {
					childList.remove(i);
				}
			}
		}
	}

	public void traverse(Set<String> urls) {
		if (StringUtils.isEmpty(id)) {
			return;
		}
		if (childList == null || childList.isEmpty()) {
			return;
		}
		for (int i = 0; i < childList.size(); i++) {
			MenuVo child = childList.get(i);
			deleteChildNode(urls);
			child.traverse(urls);
		}
	}

}
