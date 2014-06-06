package org.hbhk.aili.security.server.dao;

import java.util.List;

import org.hbhk.aili.security.share.pojo.ResourceInfo;

public interface IResourceDao {

	public ResourceInfo getResByCode(String code);

	public List<ResourceInfo> getResByPaCode(String pcode);

}
