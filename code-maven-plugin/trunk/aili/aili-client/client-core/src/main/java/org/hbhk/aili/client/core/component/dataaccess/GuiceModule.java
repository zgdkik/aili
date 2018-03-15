package org.hbhk.aili.client.core.component.dataaccess;

import com.google.inject.Module;

public abstract class GuiceModule implements Module{
	public GuiceModule(){
		GuiceContextFactroy.addModule(this);
	}
}
