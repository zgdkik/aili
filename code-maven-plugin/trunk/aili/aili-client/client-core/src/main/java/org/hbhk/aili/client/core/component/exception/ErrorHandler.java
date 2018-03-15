package org.hbhk.aili.client.core.component.exception;

import org.hbhk.aili.client.core.commons.exception.IErrorHandler;
import org.hbhk.aili.client.core.commons.exception.NormalException;
import org.hbhk.aili.client.core.commons.exception.RethrownException;
import org.hbhk.aili.client.core.commons.exception.SevereException;
import org.hbhk.aili.client.core.commons.exception.TrivialException;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.core.application.IApplication;
/**
*   错误处理类。客户端异常管理组件的功能包括：
*    <li>客户端异常定义了三种级别：
*        1.严重异常
*        2.普通异常
*        3.可忽略异常；
*    <li>定义了异常初一机制，统一处理客户端异常：把异常转换成错误码，
*    国际化之后，显示在客户端；
*    <li>客户端发生异常时，都直接抛出，有异常处理机制处理后，显示给客户端
 */
public class ErrorHandler implements IErrorHandler, ErrorDialog.Listener {
	// 国际化对象
	protected static final II18n i18n = I18nManager.getI18n(ErrorHandler.class);
	
	// 应用程序对象
	private IApplication application;
	
	/**
	 * 
	 * <p>Title: ErrorHandler</p>
	 * <p>Description: 构造函数</p>
	 * @param application 应用程序对象
	 */
	public ErrorHandler(IApplication application) {
		this.application = application;
	}

	@Override
	public void handle(Throwable t) {
		try {
			if (t instanceof SevereException) {
				handleSevereException((SevereException)t);
			} else if (t instanceof NormalException) {
				handleNormalException((NormalException)t);
			} else if (t instanceof TrivialException) {
				handleTrivialException((TrivialException)t);
			} else if (t instanceof RuntimeException) {
				handleRuntimeException((RuntimeException)t);
			} else if (t instanceof Exception) {
				handleException((Exception)t);
			} else {
				handleError(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>Title: handleError</p>
	 * <p>Description: 错误处理</p>
	 * @param t
	 */
	private void handleError(Throwable t) {
		handleSevereException(new SevereException(t));
	}

	/**
	 * 
	 * <p>Title: handleException</p>
	 * <p>Description: 异常处理</p>
	 * @param e
	 */
	private void handleException(Exception e) {
		handleTrivialException(new TrivialException(e));
	}

	/**
	 * 
	 * <p>Title: handleRuntimeException</p>
	 * <p>Description: 运行时异常处理</p>
	 * @param e
	 */
	private void handleRuntimeException(RuntimeException e) {
		handleNormalException(new NormalException(e));
	}

	/**
	 * 
	 * <p>Title: handleTrivialException</p>
	 * <p>Description: 轻微异常处理</p>
	 * @param e
	 */
	private void handleTrivialException(TrivialException e) {
		ErrorDialog errorDialog = new ErrorDialog(null, application, i18n.get("TrivialError"), i18n.get("TrivialErrorCatched"),
				getRealError(e), false);
		errorDialog.addListener(this);
		errorDialog.setVisible(true);
	}

	/**
	 * 
	 * <p>Title: handleNormalException</p>
	 * <p>Description: 普通异常处理</p>
	 * @param e
	 */
	private void handleNormalException(NormalException e) {
		ErrorDialog errorDialog = new ErrorDialog(null, application, i18n.get("NormalError"), i18n.get("NormalErrorCatched"),
				getRealError(e), true, false);
		errorDialog.addListener(this);
		errorDialog.setVisible(true);
	}

	/**
	 * 
	 * <p>Title: handleSevereException</p>
	 * <p>Description: 严重异常处理</p>
	 * @param e
	 */
	private void handleSevereException(SevereException e) {
		ErrorDialog errorDialog = new ErrorDialog(null, application, i18n.get("SevereError"), i18n.get("SevereErrorCatched"),
				getRealError(e), true, true, true);
		errorDialog.addListener(this);
		errorDialog.setVisible(true);
	}
	
	
	/**
	 * 
	 * <p>Title: getRealError</p>
	 * <p>Description: 获取错误原因</p>
	 * @param t
	 * @return
	 */
	private Throwable getRealError(Throwable t) {
		if (t instanceof RethrownException) {
			return ((RethrownException)t).getCause();
		}
		
		return t;
	}

	@Override
	public void reportError(String errorInfo, String errorDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restartApplication() {
		application.restart();
	}

	@Override
	public void exitApplication() {
		application.exit();
	}
}