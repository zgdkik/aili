/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils.jms
 * FILE    NAME: EsbJmsMessage.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.route.jms.server.common.utils.jms;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author HuangHua
 * @date 2013-4-16 下午2:46:36
 */
public class EsbLogMessage implements Serializable {
	private static final long serialVersionUID = -235027529761646655L;
	private Map<String, Object> header;
	private String body;

	public Map<String, Object> getHeader() {
		return header;
	}

	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
