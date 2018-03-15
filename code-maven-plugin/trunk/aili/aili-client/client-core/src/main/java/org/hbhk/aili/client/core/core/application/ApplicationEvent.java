package org.hbhk.aili.client.core.core.application;

/**
 * 
 *应用程序事件源包装了IApplication对象。
 */
public class ApplicationEvent {

	private IApplication source;
	
	public ApplicationEvent(IApplication application){
		this.source = application;
	}
	public IApplication getSource() {
		return source;
	}
	
	public void setSource(IApplication source) {
		this.source = source;
	}
}
