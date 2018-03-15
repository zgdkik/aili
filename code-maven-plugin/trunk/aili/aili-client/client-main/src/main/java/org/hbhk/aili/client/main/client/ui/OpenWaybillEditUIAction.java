package org.hbhk.aili.client.main.client.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.main.client.action.AbstractOpenUIAction;
import org.hbhk.aili.client.main.client.action.RepositoryCenter;

public class OpenWaybillEditUIAction extends AbstractOpenUIAction<TestUI> {

	private static final long serialVersionUID = -6309382600739332104L;

	private IApplication application;

	private EditorConfig editConfig;

	// 日志
	private static final Log log = LogFactory
			.getLog(OpenWaybillEditUIAction.class);

	// 国际化
	private static final II18n i18n = I18nManager
			.getI18n(OpenWaybillEditUIAction.class);

	public void setApplication(IApplication application) {
		this.application = application;
		RepositoryCenter.getInstance().register("application", application);
	}

	public void openUIAction() {
		openUIActionAndReturn();
	}

	public TestUI openUIActionAndReturn() {
		editConfig = new EditorConfig();
		editConfig.setTitle("测试菜单");
		editConfig.setPluginId("org.hbhk.aili.client.mainframe");
		IEditor editor = application.openEditorAndRetrun(editConfig,TestUI.class.getName());
		TestUI testUI = (TestUI) editor.getComponent();
		testUI.setEditor(editor);
		testUI.initUI();
		log.info(application.getWorkbench().getEditors().size());

		return testUI;
	}

	public IApplication getApplication() {
		return application;
	}

	public Class<TestUI> getOpenUIType() {
		return TestUI.class;
	}

	
}