package org.hbhk.aili.i18n.server.spring;

import java.util.List;


public interface IResourceService {

	 List<I18nResource> findAll(String[] path);
}
