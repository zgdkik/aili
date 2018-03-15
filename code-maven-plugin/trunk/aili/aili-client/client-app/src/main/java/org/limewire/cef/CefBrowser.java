package org.limewire.cef;

import java.awt.Canvas;

/**
 * CEF browser 调用接口类
 * @author niujian
 *
 */
public abstract class CefBrowser extends Canvas {
	

	private static final long serialVersionUID = 4681497178131370287L;
	
	/**
	 * Go back.
	 */
    public abstract void goBack();
    
    /**
     * Go forward.
     */
	public abstract void goForward();
	
	/**
	 * reload.
	 */
    public abstract void reload();
    
	/**
	 * stop.
	 */
    public abstract void stop();
	
	/**
	 * show dev tools.
	 */
    public abstract void showDevTools();
    
	/**
	 * Returns the window handle for this browser.
	 */
	public abstract long getWindowHandle();
	
	/**
	 * Returns true if the window is a popup window.
	 */
	public abstract boolean isPopup();
	
	/**
	 * Returns the main (top-level) frame for the browser window.
	 */
	public abstract CefFrame getMainFrame();
	
	/**
	 * Returns the focused frame for the browser window.
	 */
	public abstract CefFrame getFocusedFrame();
	
	public abstract void loadByUrl(String url );
	
	public abstract void setForseToNPaint(boolean forse);
	
	public abstract void setCefCookie(CefCookie cefCookie);
	public abstract void setAcceptLanguage(String acceptLanguage);

	public abstract void setCefV8Handler(CefV8Handler cefV8Handler);
}