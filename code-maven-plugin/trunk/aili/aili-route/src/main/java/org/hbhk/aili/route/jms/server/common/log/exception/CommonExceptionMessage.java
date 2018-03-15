package org.hbhk.aili.route.jms.server.common.log.exception;

import java.io.Serializable;

import org.hbhk.aili.route.jms.server.common.CommonExceptionInfo;
import org.hbhk.aili.route.jms.server.common.ESBHeader;

/**
 * 异常消息实体 异常消息的格式，请参见 消息规范说明书.
 * 
 * @author HuangHua
 */
public class CommonExceptionMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7775718953247519096L;
	
	/** The header. */
	private ESBHeader header;//消息头
	
	/** The body. */
	private CommonExceptionInfo body;//消息体

	/**
	 * Gets the header.
	 * 
	 * @return the header
	 */
	public ESBHeader getHeader() {
		return header;
	}

	/**
	 * Sets the header.
	 * 
	 * @param header
	 *            the new header
	 */
	public void setHeader(ESBHeader header) {
		this.header = header;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public CommonExceptionInfo getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 * 
	 * @param body
	 *            the new body
	 */
	public void setBody(CommonExceptionInfo body) {
		this.body = body;
	}
}
