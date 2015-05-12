package org.hbhk.aili.rpc.server.hessian.context;

import java.util.Map;

import javax.swing.JComponent;

public interface IApplication {
	/**
	 * 搴旂敤绋嬪簭鐩綍
	 */
	public static final String APP_HOME = "app_home";
	
	/**
	 * 鎻掍欢鐩綍
	 */
	public static final String APP_PLUGIN_HOME = "plugin_home";
	
	/**
	 * 鍚姩鐩綍
	 */
	public static final String APP_LAUNCHER_HOME = "app_launcher_home";
	
	/**
	 * 瀛樺偍鏌愪釜鍊煎埌搴旂敤瀵硅薄涓叿鏈変竴涓叡浜殑鍔熻兘
	 * setAttribute
	 * @param key
	 * @param value
	 * @return void
	 * @since:0.6
	 */
	void setAttribute(Object key, Object value);
	
	/**
	 * 浠庡簲鐢ㄥ璞′腑鑾峰彇鎸囧畾key鐨勫彉閲�
	 * getAttribute
	 * @param <T>
	 * @param key
	 * @return
	 * @return T
	 * @since:0.6
	 */
	<T> T getAttribute(Object key);
	
	/**
	 * 鑾峰彇搴旂敤瀵硅薄涓瓨鍌ㄧ殑鎵�湁灞炴�鍊硷紝浠ap瀵硅薄杩斿洖
	 * getAttributes
	 * @return
	 * @return Map<Object,Object>
	 * @since:0.6
	 */
	Map<Object, Object> getAttributes();
	
	/**
	 * 鑾峰彇搴旂敤涓荤洰褰曠殑瀛楃涓茶〃绀�
	 * getHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getHome();
	
	/**
	 * 閲嶅惎搴旂敤
	 * restart
	 * @return void
	 * @since:0.6
	 */
	void restart();
	
	/**
	 * 閫�嚭搴旂敤
	 * exit
	 * @return void
	 * @since:0.6
	 */
	void exit();
	
	/**
	 * 鍒ゆ柇褰撳墠搴旂敤鍚姩涔嬪墠鏄惁鍏佽鏈夎嚜鍔ㄨ繍琛岀殑绋嬪簭瀛樺湪
	 * isAutoRunsEnabled
	 * @return
	 * @return boolean 甯冨皵鍊硷紝true:鏄紝false:鍚�
	 * @since:0.6
	 */
	boolean isAutoRunsEnabled();
	
	/**
	 * 璁剧疆搴旂敤鍚姩涔嬪墠鏄惁鏈夎嚜鍔ㄨ繍琛屽姛鑳�
	 * setEnableAutoRuns
	 * @param enableAutoRuns 甯冨皵鍊硷紝true:鏄紝false:鍚�
	 * @return void
	 * @since:0.6
	 */
	void setEnableAutoRuns(boolean enableAutoRuns);
	

}

