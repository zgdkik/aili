package org.hbhk.aili.esb.server.common.exception;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

/**
 * 
 * TODO（描述类的职责）
 * @author davidcun
 * @date 2013-4-26 上午11:25:42
 */
public class LogSendException extends ESBRunTimeException {

	/**
	 * TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1970226819301801749L;

	public LogSendException() {
		super();
	}

	public LogSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogSendException(String string) {
		super(string);
	}

	public LogSendException(Throwable cause) {
		super(cause);
	}

}
