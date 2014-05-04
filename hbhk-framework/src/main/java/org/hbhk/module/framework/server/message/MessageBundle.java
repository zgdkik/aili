package org.hbhk.module.framework.server.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import org.hbhk.module.framework.server.context.RequestContext;

public class MessageBundle implements IMessageBundle {

	private MessageCache messageCache;

	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		if (key == null) {
			return null;
		}
		String  moduleName = RequestContext.getCurrentContext().getModuleName();
		Properties properties = (Properties) messageCache.getI18nProperties(MessageCache.UUID).get(moduleName);
		if (locale == null) {
			// 没有传入locale的用服务器系统默认的locale
			locale = Locale.getDefault();
		}
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			}
		}
		// 没有国际化信息返回key
		return key;

	}

	public String getI18nKeys(String key) {
		return messageCache.getI18nKeys(key);
	}
	@Override
	public String getMessage(String key, Object... args) {

		return getMessage(null, key, args);
	}

	public void setMessageCache(MessageCache messageCache) {
		this.messageCache = messageCache;
	}

}
