package org.hbhk.aili.mybatis.server.handler;

import java.util.UUID;

public class DefaultCommonValueHandler implements ICommonValueHandler {


	@Override
	public Object getId() {
		return  UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public String getCompCode() {
		return "hbhk";
	}

	@Override
	public String getModifier() {
		return "hbhk";
	}

	@Override
	public String getCreater() {
		return "hbhk";
	}

	@Override
	public Long getRecordVersion() {
		return 1L;
	}

	@Override
	public Integer getIsDelete() {
		return 1;
	}

}
