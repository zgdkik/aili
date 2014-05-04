package org.hbhk.module.framework.server.context;             

import java.util.UUID;

/**
 * @Classname:RequestContext
 * @Description:请求的上下文信息
 * 
 */
public final class RequestContext {

    private static ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>(){
        @Override
        protected RequestContext initialValue(){
            return new RequestContext();
        }
    };
	
	//远程调用请求的方法名称和url
	private String remoteReqMethod;
	
	private String remoteReqURL;
	//请求的模块名称
	private String moduleName;
	
	private String requestId;
	
	private String ip;
	
	public String getRequestId() {
		return requestId;
	}

	public String getModuleName() {
		return moduleName;
	}

	private RequestContext(){
	    this.requestId = UUID.randomUUID().toString();
	}
	
	public String getRemoteRequestMethod() {
		return this.remoteReqMethod;
	}

	public String getRemoteRequestURL() {
		return this.remoteReqURL;
	}
	
	public String getIp() {
        return this.ip;
    }

	/**
	 * 
	 * @return
	 */
	public static RequestContext getCurrentContext() {
		return context.get();

	}

	/**
	 * 
	 * @param remoteReqMethod
	 * @param remoteReqURL
	 */
	public static void setCurrentContext(String remoteReqMethod,String remoteReqURL) {
	    setCurrentContext(remoteReqMethod,remoteReqURL,null);
	}
	public static void setCurrentContext(String remoteReqMethod,String remoteReqURL,String ip) {
	    RequestContext requestContext = getCurrentContext();
        requestContext.remoteReqMethod = remoteReqMethod;
        requestContext.remoteReqURL = remoteReqURL;
        requestContext.ip = ip;
	}
	/**
	 * 
	 * @param moduleName
	 */
	public static void setCurrentContext(String moduleName) {
		RequestContext requestContext = getCurrentContext();
		requestContext.moduleName = moduleName;
	}
	
	/**
	 * 清楚ThreadLocal
	 * remove
	 * @return void
	 * @since:
	 */
	public static void remove(){	
	    context.remove();
	}
	
}
