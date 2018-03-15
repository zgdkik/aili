package org.hbhk.aili.client.main.client.guice;

import org.hbhk.aili.client.core.component.dataaccess.GuiceModule;
import org.hbhk.aili.client.main.client.service.IMenuService;
import org.hbhk.aili.client.main.client.service.imp.MenuService;

import com.google.inject.Binder;

/**
 * 绑定
 * 
 */
public class BaseInfoModule extends GuiceModule {
	/**
	 * 配置
	 */
	@Override
	public void configure(Binder binder) {
		// 组织信息
		binder.bind(IMenuService.class).to(MenuService.class).asEagerSingleton();
	}

}