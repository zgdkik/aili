package org.hbhk.code.util;

public enum TablePreReplacement {
	T_,TAB_;
	
	public static final String SPLIT_STRING = "_";
	
	public String replaceEmpty(String str) {
		return str.replaceFirst(this.name(), "");
	}
}
