package org.hbhk.module.framework.server.security;

public interface UrlMatcher {

	public  Object compile(String paramString);

	public  boolean pathMatchesUrl(Object paramObject,String paramString);

	public  String getUniversalMatchPattern();

	public  boolean requiresLowerCaseUrl();

}
