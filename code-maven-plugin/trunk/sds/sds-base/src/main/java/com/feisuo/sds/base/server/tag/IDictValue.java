package com.feisuo.sds.base.server.tag;

public interface IDictValue extends Comparable<IDictValue> {
	String getKey();

	String getValue();

	String getDictCode();

	Integer getOrderNo();
}
