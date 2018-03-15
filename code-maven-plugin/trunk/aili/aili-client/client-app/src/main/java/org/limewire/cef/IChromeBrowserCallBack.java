package org.limewire.cef;

/**
 * 调用chrome browser 回调java 方法的接口
 *
 */
public interface IChromeBrowserCallBack {

	public boolean doCallBack(String[] params) throws Exception;
}