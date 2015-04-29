package org.hbhk.test.ds.impl;

import java.util.Properties;

import org.hbhk.aili.core.server.context.IPropertiesService;

public class DemoProrpertieService implements IPropertiesService {

	@Override
	public Properties getProperties() {
		Properties props = new Properties();
		props.put("name", "hbhk1");
		return props;
	}

}
