package org.hbhk.aili.core.server.service;

import javax.servlet.http.HttpServletRequest;

import org.hbhk.aili.core.share.entity.Token;

/**
 * 集群token生成器
 * 
 * @author 089115
 * 
 */
public interface IClusterTokenGenerator {

	String encodeToken(HttpServletRequest request,Token obj);

	Token decodeToken(String token);

}
