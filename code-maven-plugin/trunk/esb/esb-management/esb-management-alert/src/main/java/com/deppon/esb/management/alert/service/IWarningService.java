package com.deppon.esb.management.alert.service;

import java.util.Map;
import java.util.Set;

public interface IWarningService {
	
	public Set<String> getExceptionWarningTargets(String serviceCode, String channel);
	
	public Map<Integer, Set<String>> getPerformanceWarningTargets(String serviceCode, String channel, Long timecost);

}
