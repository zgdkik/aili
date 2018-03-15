package org.hbhk.aili.mybatis.server.handler;

public interface ICommonValueHandler {

	Object getId();

	String getCompCode();

	String getModifier();

	String getCreater();
	
	Long getRecordVersion();
	
	Integer getIsDelete();

}
