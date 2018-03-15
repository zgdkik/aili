package org.hbhk.aili.i18n.server.spring;

import java.util.Locale;

import org.hbhk.aili.core.server.i18n.IMessageBundle;
import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;

public class MessageBundle implements IMessageBundle {

	@Override
	public String getMessage(String key, Object... args) {
		WebApplicationContext context = WebApplicationContextHolder
				.getWebApplicationContext();
		String msg = context.getMessage(key, args,
				LocaleContextHolder.getLocale());
		return msg;
	}

	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		WebApplicationContext context = WebApplicationContextHolder
				.getWebApplicationContext();
		String msg = context.getMessage(key, args, locale);
		return msg;
	}

}
