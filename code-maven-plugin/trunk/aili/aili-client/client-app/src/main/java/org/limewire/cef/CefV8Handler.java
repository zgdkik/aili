package org.limewire.cef;

/**
 * CEF browser 的js回调java函数handler 接口
 *
 */
public interface CefV8Handler {
	/**
	 * Callback that will be called when the function is executed.
	 * @param name Name of the executed function.
	 * @param params Input and output parameters.
	 * @return True if execution succeeded.
	*/
	public boolean executeFunction(String name, CefV8FunctionParams params);
}