package org.limewire.cef;

/**
 * Cef cookie 功能调用参数类
 */
public class CefCookie {

	private String cookieurl;
	private String cookiename;
	private String cookievalue;
	private String cookiedomain;
	private String cookiepath;
	public String getCookieurl() {
		return cookieurl;
	}
	public void setCookieurl(String cookieurl) {
		this.cookieurl = cookieurl;
	}
	public String getCookiename() {
		return cookiename;
	}
	public void setCookiename(String cookiename) {
		this.cookiename = cookiename;
	}
	public String getCookievalue() {
		return cookievalue;
	}
	public void setCookievalue(String cookievalue) {
		this.cookievalue = cookievalue;
	}
	public String getCookiedomain() {
		return cookiedomain;
	}
	public void setCookiedomain(String cookiedomain) {
		this.cookiedomain = cookiedomain;
	}
	public String getCookiepath() {
		return cookiepath;
	}
	public void setCookiepath(String cookiepath) {
		this.cookiepath = cookiepath;
	}
	
}