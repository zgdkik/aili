package org.hbhk.aili.i18n.server.spring;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hbhk.aili.i18n.server.spring.impl.DefaultResourceService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;

public class ResourceBundleMessageSourceExt extends ResourceBundleMessageSource implements InitializingBean {

	private IResourceService resourceService;

	private String[] basenames = new String[0];
	/**
	 * Map切分字符
	 */
	protected final String MAP_SPLIT_CODE = "|";

	protected final String DB_SPLIT_CODE = "_";

	private final Map<String, String> properties = new ConcurrentHashMap<String, String>();

	public ResourceBundleMessageSourceExt() {
	}

	public void reload() {
		properties.clear();
		properties.putAll(loadTexts());
	}

	protected Map<String, String> loadTexts() {
		Map<String, String> mapResource = new HashMap<String, String>();
		if (resourceService == null) {
			resourceService = new DefaultResourceService();
		}
		List<I18nResource> resources = resourceService.findAll(basenames);
		if(resources != null){
			for (I18nResource item : resources) {
				String code = item.getKey() + MAP_SPLIT_CODE + item.getLang();
				mapResource.put(code, item.getVal());
			}
		}
		return mapResource;
	}

	private String getText(String code, Locale locale) {
		String localeCode = locale.getLanguage() + DB_SPLIT_CODE
				+ locale.getCountry();
		String key = code + MAP_SPLIT_CODE + localeCode;
		String localeText = properties.get(key);
		String resourceText = code;

		if (localeText != null) {
			resourceText = localeText;
		} else {
			localeCode = Locale.ENGLISH.getLanguage();
			key = code + MAP_SPLIT_CODE + localeCode;
			localeText = properties.get(key);
			if (localeText != null) {
				resourceText = localeText;
			} else {
				try {
					if (getParentMessageSource() != null) {
						resourceText = getParentMessageSource().getMessage(
								code, null, locale);
					}
				} catch (Exception e) {
					logger.error("Cannot find message with code: " + code);
				}
			}
		}
		return resourceText;
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = getText(code, locale);
		MessageFormat result = createMessageFormat(msg, locale);
		return result;
	}

	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		String result = getText(code, locale);
		return result;
	}
	
	public void setBasename(String basename) {
		setBasenames(basename);
	}
	
	public void setBasenames(String... basenames) {
		if (basenames != null) {
			this.basenames = new String[basenames.length];
			for (int i = 0; i < basenames.length; i++) {
				String basename = basenames[i];
				Assert.hasText(basename, "Basename must not be empty");
				this.basenames[i] = basename.trim();
			}
		}
		else {
			this.basenames = new String[0];
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		reload();
	}

	public IResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	
}
