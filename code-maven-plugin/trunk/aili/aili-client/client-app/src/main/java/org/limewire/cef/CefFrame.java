package org.limewire.cef;

/**
 * Cef frame 调用接口类
 *
 */
public interface CefFrame {
	/**
	 * Load the specified url.
	 */
	public void loadURL(String url);
	
	/**
	 * Execute JavaScript.
	 * @param jsCode The JavaScript code to execute.
	 * @param scriptUrl The URL where the script in question can be found, if any.
	 * @param startLine The base line number to use for error reporting.
	 */
	public void executeJavaScript(String jsCode, String scriptUrl, int startLine);
	
	/**
	 * Returns true if this is the main frame.
	 */
	public boolean isMain();
	
	/**
	 * Returns true if this is the focused frame.
	 */
	public boolean isFocused();
}