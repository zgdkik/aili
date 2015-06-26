package org.hbhk.aili.core.server.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 集群token生成器
 * 
 * @author 089115
 * 
 */
public interface IClusterTokenGenerator {

	String encodeToken(HttpServletRequest request);

	Object decodeToken(String token);

}
