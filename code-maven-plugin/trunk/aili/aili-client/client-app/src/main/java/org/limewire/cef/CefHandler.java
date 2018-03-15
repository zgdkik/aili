package org.limewire.cef;

/**
 * cef 调用处理类
 */
public interface CefHandler {
	/**
	 * Handle creation of a new browser window.
	 * @param browser The browser generating the event.
	 */
	public void handleAfterCreated(CefBrowser browser);
	
	/**
	 * Handle address changes.
	 * @param browser The browser generating the event.
	 * @param url The new address.
	 */
	public void handleAddressChange(CefBrowser browser, CefFrame frame, String url);
	
	/**
	 * Handle title changes.
	 * @param browser The browser generating the event.
	 * @param title The new title.
	 */
	public void handleTitleChange(CefBrowser browser, String title);
	
	/**
	 * Event called for adding values to a frame's JavaScript 'window' object.
	 * @param browser The browser generating the event.
	 * @param object The 'window' object.
	 */
	public void handleJSBinding(CefBrowser browser, CefFrame frame, CefV8Value object);
}