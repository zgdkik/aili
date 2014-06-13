package org.hbhk.aili.orm.server.aspect;

import org.hbhk.aili.orm.server.annotation.ColumnTranslator;

public class DummyColumnTranslator implements ColumnTranslator {

	public void setModelClass(Class<?> clazz) {
		//do nothing
	}

	public String toColumnName(String attribute) {
		//do nothing
		return null;
	}

}
