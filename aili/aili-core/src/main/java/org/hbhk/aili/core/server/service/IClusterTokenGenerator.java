package org.hbhk.aili.core.server.service;


/**
 * 集群token生成器
 * @author 089115
 *
 */
public interface IClusterTokenGenerator {
	
	
	String encodeToken();
	
	Object  decodeToken(String token);
	

}
