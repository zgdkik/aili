package org.hbhk.aili.esb.server.common.log.intercepter;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

/**
 * 
 * 记录审计日志
 * @author qiancheng
 * @date 2013-4-16 下午5:21:44
 */
public class AuditLogFeature extends AbstractFeature{
	private  AuditLogInIntercepter in ;
	private  AuditLogOutIntercepter out;
    @Override
    protected void initializeProvider(InterceptorProvider provider, Bus bus) {
        if(in == null ||out ==null){
        	throw new  ESBRunTimeException("auditIntercepter null");
        }
    	provider.getInInterceptors().add(in);
        provider.getInFaultInterceptors().add(in);
        provider.getOutInterceptors().add(out);
        provider.getOutFaultInterceptors().add(out);
    }
	public AuditLogInIntercepter getIn() {
		return in;
	}
	public void setIn(AuditLogInIntercepter in) {
		this.in = in;
	}
	public AuditLogOutIntercepter getOut() {
		return out;
	}
	public void setOut(AuditLogOutIntercepter out) {
		this.out = out;
	}
    
    
}
