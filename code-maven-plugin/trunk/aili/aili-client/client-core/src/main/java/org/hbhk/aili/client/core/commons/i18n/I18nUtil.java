package org.hbhk.aili.client.core.commons.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 *国际化资源加载工具类
 */
public final class I18nUtil {
	// 日志对象
	private static final Log LOG = LogFactory.getLog(I18nUtil.class);
	
	private I18nUtil(){
		
	}
	
	/**
	 * 通过输入流读取国际化资源文件信息
	 * loadResource
	 * @param inStream
	 * @return
	 * @return Map<String,String>
	 * @since:0.6
	 */
	public static Map<String, String> loadResource(InputStream inStream) {
		Map<String,String> src = new ConcurrentHashMap<String, String>();
		
		//如果统一规范通过 new InputStreamReader(inStream,"UTF-8"),指定字符集;
		
		PropertyResourceBundle bundle = null;
		try {
			Reader reader = new InputStreamReader(inStream,"UTF-8");
			bundle = new PropertyResourceBundle(reader);
			Enumeration<String> keys = bundle.getKeys();
			while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                src.put(key, bundle.getObject(key).toString());
            }
			return src;
		} catch(IOException e){
			e.printStackTrace();
		} 
		
		return null;
		
	}
}
