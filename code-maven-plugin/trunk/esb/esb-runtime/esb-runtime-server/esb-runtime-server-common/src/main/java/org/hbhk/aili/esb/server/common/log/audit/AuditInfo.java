package org.hbhk.aili.esb.server.common.log.audit;

import java.io.Serializable;

import org.hbhk.aili.esb.server.common.entity.ESBHeader;

public class AuditInfo  implements Serializable{
	private static final long serialVersionUID = 863053118248358807L;
	private String body;
	private ESBHeader header;
	public AuditInfo(){
		
	}
	public AuditInfo(ESBHeader header,String body){
		this.header=header;
		this.body=body;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ESBHeader getHeader() {
		return header;
	}
	public void setHeader(ESBHeader header) {
		this.header = header;
	}
	
}
