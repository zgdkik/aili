package org.limewire.cef;

/**
 * 调用CEF jni接口参考列子程序
 */
public class MainHandler implements CefHandler {
	private MainFrame main_frame;
	private long main_handle = 0;

	public MainHandler(MainFrame main_frame) {
    	this.main_frame = main_frame;
    }
	
	@Override
	public void handleAfterCreated(CefBrowser browser) {
		if(!browser.isPopup())
			main_handle = browser.getWindowHandle();
	}
	
	@Override
	public void handleAddressChange(CefBrowser browser, CefFrame frame, String url) {
		if(browser.getWindowHandle() == main_handle && frame.isMain())
			main_frame.setAddress(url);
	}

	@Override
	public void handleTitleChange(CefBrowser browser, String title) {
		long handle = browser.getWindowHandle();
		if(handle == main_handle)
			main_frame.setTitle(title);
	}

	@Override
	public void handleJSBinding(CefBrowser browser, CefFrame frame, CefV8Value object) {
		// Add a "window.test" object.
		//CefV8Value test_obj = CefContext.createV8Object(null);
		//object.setValue("GIS_Results", test_obj);
		
		// Add a "showMessage" function to the "window.test" object.
		//test_obj.setValue("returnResults",
		//CefContext.createV8Function("returnResults", new MainV8Handler(main_frame)));
		
		CefContext.createV8Function("returnResults1",  new MainV8Handler(main_frame));
	}
}