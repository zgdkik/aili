package org.hbhk.aili.i18n.server.spring.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.FileLoadUtil;
import org.hbhk.aili.i18n.server.spring.I18nResource;
import org.hbhk.aili.i18n.server.spring.IResourceService;
import org.springframework.core.io.Resource;

public class DefaultResourceService implements IResourceService {

	private Log  log = LogFactory.getLog(getClass());
	@Override
	public List<I18nResource> findAll(String[] paths) {
		try {
			List<I18nResource> i18nResourceList = new ArrayList<I18nResource>();
			List<Resource> resources = new ArrayList<Resource>();
			for (int i = 0; i < paths.length; i++) {
				Resource[] res = FileLoadUtil
						.getResourcesForClasspathByPath(paths[i]);
				if(res!=null && res.length>0){
					resources.addAll(Arrays.asList(res));
				}
			}
			if(resources!=null && resources.size()>0){
				for (Resource res : resources) {
					if(res.isReadable()){
						String path = res.getURL().getPath();
						if(!path.endsWith(".properties")){
							continue;
						}
						String lang = path.substring(path.lastIndexOf("/")+1, path.indexOf(".properties"));
						lang = lang.substring(lang.indexOf("_")+1, lang.length());
						log.info("读取国际化文件:"+path);
						Properties  properties = new Properties();
						properties.load(res.getInputStream());
						Set<Object> keys = properties.keySet();
						if(keys!=null && keys.size()>0){
							for (Object key : keys) {
								String k = (String) key;
								String val = properties.getProperty(k);
								if(StringUtils.isEmpty(val)){
									val = "";
								}
								val = new String(val.getBytes(
										"ISO8859-1"), "UTF-8");
								I18nResource i18nResource = new I18nResource();
								i18nResource.setKey(k);
								i18nResource.setLang(lang);
								i18nResource.setVal(val);
								i18nResourceList.add(i18nResource);
							}
						}
					}
				}
			}
			return i18nResourceList;
		} catch (IOException e) {
			throw  new RuntimeException("加载国际化出错",e);
		}
	}

}
