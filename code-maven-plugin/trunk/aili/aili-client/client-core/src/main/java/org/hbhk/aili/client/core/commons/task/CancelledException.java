package org.hbhk.aili.client.core.commons.task;

/**
 * 取消执行异常
 */
public class CancelledException extends Exception {
	// 任务取消异常版本
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <p>Title: CancelledException</p>
	 * <p>Description: 原始异常</p>
	 */
	public CancelledException() {
		super();
	}

	/**
	 * 
	 * <p>Title: CancelledException</p>
	 * <p>Description: 带信息异常</p>
	 * @param message 异常信息
	 */
	public CancelledException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: CancelledException</p>
	 * <p>Description: 必须抛出的异常</p>
	 * @param cause 异常超类
	 */
	public CancelledException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * <p>Title: CancelledException</p>
	 * <p>Description: 带信息必须抛出的异常</p>
	 * @param message 异常信息
	 * @param cause 异常超类
	 */
	public CancelledException(String message, Throwable cause) {
		super(message, cause);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: fillInStackTrace</p>
	 * <p>Description: 堆栈跟踪</p>
	 * @return
	 * @see java.lang.Throwable#fillInStackTrace()
	 */
	public Throwable fillInStackTrace() {
		return null; // 不需要栈信息
	}
}