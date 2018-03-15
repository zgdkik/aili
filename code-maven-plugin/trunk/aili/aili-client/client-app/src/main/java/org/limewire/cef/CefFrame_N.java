package org.limewire.cef;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 旧版本CEF browser 调用类 调用cef frame对象
 */
public class CefFrame_N implements CefFrame {
	private static final Log log = LogFactory.getLog(CefFrame_N.class);
    private long N_CefHandle = 0;
    
    CefFrame_N() {}
    
    @Override
	protected void finalize() throws Throwable
    {
    	try {
        	N_Destroy();
        } catch (UnsatisfiedLinkError ule) {
        	log.error(ule);
        }
    	super.finalize();
    }
    
	@Override
	public void loadURL(String url) {
        try {
        	N_LoadURL(url);
        } catch (UnsatisfiedLinkError ule) {
        	log.error(ule);
        }
		
	}
    
    @Override
	public void executeJavaScript(String jsCode, String scriptUrl, int startLine) {
    	try {
        	N_ExecuteJavaScript(jsCode, scriptUrl, startLine);
        } catch (UnsatisfiedLinkError ule) {
        	log.error(ule);
        }
	}
    
    @Override
	public boolean isMain() {
    	try {
        	return N_IsMain();
        } catch (UnsatisfiedLinkError ule) {
        	log.error(ule);
        }
        return false;
	}

	@Override
	public boolean isFocused() {
		try {
        	return N_IsFocused();
        } catch (UnsatisfiedLinkError ule) {
        	log.error(ule);
        }
        return false;
	}

	private final native void N_Destroy();
    private final native void N_LoadURL(String address);
    private final native void N_ExecuteJavaScript(String jsCode, String scriptUrl, int startLine);
    private final native boolean N_IsMain();
    private final native boolean N_IsFocused();
}